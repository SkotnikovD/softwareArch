package BusinessLogic;

//client 12345
import BusinessLogic.Part.PartRequest;
import BusinessLogic.Car.Car;
import BusinessLogic.Car.DBCustomersCars;
import BusinessLogic.Part.PartForSale;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {
    private long id;
    private String nickname;
    private City city;
    private AuthoriseInfo ai;

    public Customer(long id, String nickname, City city, AuthoriseInfo ai) {
	this.id = id;
	this.nickname = nickname;
	this.city = city;
	this.ai = ai;
    }
       
    private List<Car> carsList = null;
    private List<Tender> tendersList = null;

    public AuthoriseInfo getAi() {
	return ai;
    }
    
    public List<Car> getCarsList() throws DBInteractionException{
	//Recieving car list
	if (this.carsList==null){
	    this.carsList=new LinkedList<>();
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT fk_car FROM APP.CUSTOMERS_CARS WHERE fk_cust=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		    this.carsList.add(Car.get(rs.getLong("fk_car")));
		}
	    }catch(SQLException se){
		throw new DBInteractionException("Can't get customer's car list: "+ se.getMessage());
	    }
	}
	return carsList;
    }
    
    public List<Tender> getTendersList() throws DBInteractionException{
	//Recieving tenders list
	if (this.tendersList==null){
	    this.tendersList=new LinkedList<>();
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.TENDER WHERE fk_customer=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		    this.tendersList.add(Tender.get(rs.getLong("id")));
		}
	    }catch(SQLException se){
		throw new DBInteractionException("Can't get customer's tenders list: "+ se.getMessage());
	    }
	}
	return tendersList;
    }

    public String getNickname() {
	return nickname;
    }

    public void setId(long id) {
	this.id = id;
    }
    
    public long getId() {
	return this.id;
    }
    
    public void removeCar(Car c) throws DBInteractionException{
	for(Car car : this.getCarsList()){
	    if(car == c){
		DBCustomersCars.deleteCar(car, this);
		this.getCarsList().remove(car);
		
		for(Tender t : this.getTendersForCar(c)){
		    t.delete();
		}
		return;
	    }
	}
	throw new DBInteractionException("No car was found to delete");
    }
    
    public void addCar(Car c) throws DBInteractionException{
	c.insert();
	DBCustomersCars.addCar(c, this);
	this.getCarsList().add(c);
    }
    
    public List<Tender> getTendersForCar(Car c) throws DBInteractionException{
	List<Tender> l= new LinkedList<>();
	for(Tender t : this.getTendersList()){
	    if(t.getPartRequest().getCar().equals(c)){
		l.add(t);
	    }
	}
	return l;
    }
    
    public void addTender (Tender t) throws DBInteractionException{
	t.insert();
	getTendersList().add(0,t);
	t.sendNotiAll();
    }
    
//    private void deliverNotifications(Tender t){
//	for(Seller s : DBSeller.getSellerList())
//	    for(PartForSale pfs : s.getSellParts()){
//		System.out.println("into FOR");
//		if (pfs.getModel().equals(t.getPartRequest().getCar().getModel())){
//		    System.out.println("1 if");
//		    if (t.getPartRequest().getCar().getYear()>=pfs.getYearSince() && t.getPartRequest().getCar().getYear()<=pfs.getYearUntil()){
//			System.out.println("2 if");
//			if((pfs.getCat()==null) || (t.getPartRequest().getPart().getCategory().equals(pfs.getCat()))){
//			    System.out.println("3 if");
//			    if(pfs.getFormFactor()==null || pfs.getFormFactor().equals(t.getPartRequest().getCar().getFormFacor())){
//				System.out.println("4 if");
//				if(pfs.getPart()==null || pfs.getPart().equals(t.getPartRequest().getPart())){
//				    System.out.println("5 if");
//				    s.notifySeller(new Notification(t));
//				}
//			    }
//			}
//		    }
//		}
//	    }
//    }
    
    public void removeTender (Tender t) throws DBInteractionException{
	this.getTendersList().remove(t);
	t.delete();
    }
    
    public static Customer get (long id) throws DBInteractionException{
	Customer cust=null;
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.CUSTOMER WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		Long idRcv = rs.getLong("id");
		String nicknameRcv = rs.getString("nickname");
		Long fkCityRcv = rs.getLong("fk_city");
		Long fkAuthInfoRcv = rs.getLong("fk_auth_inf");
		City cityRcv = City.get(fkCityRcv);
		AuthoriseInfo authInfoRcv= AuthoriseInfo.get(fkAuthInfoRcv);
		cust = new Customer(idRcv, nicknameRcv, cityRcv, authInfoRcv);
	    }
	    else{
		throw new DBInteractionException("No customer with such Id was found");
	    }
	    return cust;
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't get customer information: "+ se.getMessage());
	}
    }
	
    public static Customer getByAI (long auth_info_id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM APP.CUSTOMER WHERE fk_auth_inf=?");
	    pstmt.setLong(1, auth_info_id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return Customer.get(rs.getLong("id"));
	    }
	    else{
		return null;
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Error while authorising: "+ se.getMessage());
	}
    }
	//Recieving tender list
//	try{
//	    Connection conn = ConnectionManager.getConnection();
//	    PreparedStatement pstmt = conn.prepareStatement("SELECT fk_car FROM APP.CUSTOMERS_CARS WHERE fk_cust=?");
//	    pstmt.setLong(1, id);
//	    ResultSet rs = pstmt.executeQuery();
//	    while(rs.next()){
//		cust.carsList.add(Car.get(rs.getLong("fk_car")));
//	    }
//	}catch(SQLException se){
//	    throw new DBInteractionException("Can't get customer's car list: "+ se.getMessage());
//	}

    
    public void insert () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "INSERT INTO APP.CUSTOMER(nickname, fk_city, fk_auth_inf) VALUES(?,?,?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    ai.insert();
	    ins_st.setString(1, this.getNickname());
	    ins_st.setLong(2, this.city.getId());
	    ins_st.setLong(3,  this.ai.getId());

	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = rs_key.getLong(1);
	    }
	} catch(SQLException ex){
	     Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	    throw new DBInteractionException("Can't insert customer to database!");
	}
    }
}
