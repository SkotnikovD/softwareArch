package BusinessLogic;

import BusinessLogic.Car.Car;
import BusinessLogic.Car.DBCarBrand;
import BusinessLogic.Car.DBCarModel;
import BusinessLogic.Car.DBCustomersCars;
import BusinessLogic.Car.DBFormFactor;
import BusinessLogic.Part.Part;
import BusinessLogic.Part.PartRequest;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tender {
    
    private long id;
    private PartRequest partRequest;
    private Customer customer;
    private Date createDate;
    private String addInf;
    private List<Bid> bidsList = null;

    public Tender(long id,PartRequest part, Customer customer, Date date) {
	this.id = id;
	this.partRequest = part;
	this.customer = customer;
	this.createDate = date;
    }

    @Override
    public String toString() {
	return partRequest.toString();
    }
    
    
    public void addBid(Bid bid) throws DBInteractionException{
	this.getBidsList().add(bid);
    }
    
    public static Tender get(long id) throws DBInteractionException {
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.TENDER WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		PartRequest partReqRcv = PartRequest.get(rs.getLong("fk_part_request"));
		Customer custRcv = Customer.get(rs.getLong("fk_customer"));
		Date dateRcv = new Date(rs.getTimestamp("create_date").getTime());
		return new Tender(id, partReqRcv, custRcv, dateRcv);
	    }
	    else{
		throw new DBInteractionException("No tender with such id " + id);
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Cant get tender: " + e.getMessage());
	}
    }
    
    public List<Bid> getBidsList() throws DBInteractionException{
	if (this.bidsList==null){
	    this.bidsList=new LinkedList<>();
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM APP.BID WHERE fk_tender=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		    this.bidsList.add(Bid.get(rs.getLong("id")));
		}
	    }catch(SQLException e){
		throw new DBInteractionException("Cant get bids list: " + e.getMessage());
	    }
	}
	return bidsList;
    }
    
    public void insert() throws DBInteractionException{
	try{
	    this.getPartRequest().insert();
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "INSERT INTO APP.TENDER(fk_part_request, fk_customer)"+"VALUES(?,?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    //?this.partRequest.insert();
	    ins_st.setLong(1, this.partRequest.getId());
	    ins_st.setLong(2, this.customer.getId());
	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = (rs_key.getLong(1));
	    }
	}catch(SQLException sq){
	    throw new DBInteractionException("Can't insert tender: " + sq.getMessage());
	}
    }
    
    public void sendNotiAll () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * "
		    + "FROM APP.PART_FOR_SALE "
//		    + "JOIN APP.PART ON APP.PART.id = APP.PART_FOR_SALE.fk_part "
//		    + "JOIN APP.SELLER ON APP.SELLER.id=APP.PART_FOR_SALE.fk_seller "
		    + "WHERE APP.PART_FOR_SALE.fk_car_model=? AND "
		    + "APP.PART_FOR_SALE.yearsince <= ? AND "
		    + "APP.PART_FOR_SALE.yearuntil >= ?");
	    pstmt.setLong(1, DBCarModel.getIdByModel(this.getPartRequest().getCar().getBrand(), this.getPartRequest().getCar().getModel()));
	    pstmt.setInt(2, this.partRequest.getCar().getYear());
	    pstmt.setInt(3, this.partRequest.getCar().getYear());
	    final String insQuery = "INSERT INTO APP.NOTIFICATION(fk_tender, fk_sell) VALUES(?,?)";
	    ResultSet rs = pstmt.executeQuery();
	    while(rs!=null && rs.next()){
		long cat = rs.getInt("category");
		long fkpart = rs.getInt("fk_part");
		int ff = rs.getInt("form_factor");
		long fkSeller = rs.getLong("fk_seller");
		if(ff==0 || this.partRequest.getCar().getFormFactor()==ff){
		    if(cat==0 || cat==this.partRequest.getPart().getCategory()){
			if(fkpart==0|| fkpart==this.partRequest.getPart().getId()){
			    PreparedStatement ins_st = conn.prepareStatement(insQuery);
			    ins_st.setLong(1, this.id);
			    ins_st.setLong(2, fkSeller);
			    ins_st.executeUpdate();
			}
		    }
		}
	    }
	}catch(SQLException e){
	    throw new DBInteractionException("Can't insert notification to database!\n" + e.getMessage());
	}
    }
    
    public PartRequest getPartRequest() {
	return partRequest;
    }

    public Date getCreateDate() {
	return createDate;
    }

    public long getId() {
	return this.id;
    }

    public void delete() throws DBInteractionException {
	try {
	    Connection conn = ConnectionManager.getConnection();
	    final String delQuery = "DELETE FROM APP.TENDER WHERE id=?";
	    PreparedStatement del_st = conn.prepareStatement(delQuery);
	    del_st.setLong(1, this.getId());
	    del_st.executeUpdate();
	    
	    this.getPartRequest().delete();
	} catch (SQLException ex) {
	    Logger.getLogger(DBCustomersCars.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't delete tender:\n " + ex.getMessage());
	}
    }
    
}
