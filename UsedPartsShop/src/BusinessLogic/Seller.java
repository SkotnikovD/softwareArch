/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Seller {
    private long id;
    
    private String phone;
    private String email;
    private City city;
    private String name;
    
    private AuthoriseInfo ai;
    
    private List<PartForSale> parts = null;
    private List<Notification> notifications = null;

    public Seller(long id, String phone, String email, City city, String name, AuthoriseInfo ai) {
	this.id = id;
	this.phone = phone;
	this.email = email;
	this.city = city;
	this.name = name;
	this.ai = ai;
    }
    
    public void insert () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "INSERT INTO APP.SELLER(fk_auth_info, phone, email, sell_name, city) VALUES(?,?,?,?,?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    ai.insert();
	    ins_st.setLong(1, this.ai.getId());
	    ins_st.setString(2, this.phone);
	    ins_st.setString(3,  this.email);
	    ins_st.setString(4,  this.name);
	    ins_st.setLong(5,  this.city.getId());

	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = rs_key.getLong(1);
	    }
	} catch(SQLException ex){
	    Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	    throw new DBInteractionException("Can't insert seller to database!");
	}
    }
    
    public static Seller get(long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.SELLER WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs!=null && rs.next()){
		String companyName = rs.getString("sell_name");
		Long fkAuthInfoRcv = rs.getLong("fk_auth_info");
		City cityRcv = City.get(rs.getLong("city"));
		String emailRcv = rs.getString("email");
		String phoneRcv = rs.getString("phone");
		AuthoriseInfo authInfoRcv= AuthoriseInfo.get(fkAuthInfoRcv);
		return new Seller(id,phoneRcv,emailRcv,cityRcv,companyName,authInfoRcv);
	    }
	    else{
		throw new DBInteractionException("No seller with such Id was found");
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't get seller information: "+ se.getMessage());
	}
    }
    
    public static Seller getByAI (long auth_info_id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM APP.SELLER WHERE fk_auth_info=?");
	    pstmt.setLong(1, auth_info_id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return Seller.get(rs.getLong("id"));
	    }
	    else{
		return null;
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Error while authorising: "+ se.getMessage());
	}
    }
    
    public void addPartForSale(PartForSale p) throws DBInteractionException{
	this.getParts().add(p);
    }
    
    public void deletePartForSale(PartForSale p) throws DBInteractionException{
	this.getParts().remove(p);
	p.delete();
    }
    
    public AuthoriseInfo getAi() {
	return ai;
    }

    public long getId() {
	return id;
    }

    public List<Notification> getNotifications() throws DBInteractionException {
	if (this.notifications==null){
	    this.notifications=new LinkedList<>();
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.NOTIFICATION WHERE fk_sell=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		    System.out.println("Noti added");
		    this.notifications.add(Notification.get(rs.getLong("id")));
		}
	    }catch(SQLException se){
		throw new DBInteractionException("Can't get seller's notification list: "+ se.getMessage());
	    }
	}
	return notifications;
    }

   public List<PartForSale> getParts() throws DBInteractionException {
	if (this.parts==null){
	    this.parts=new LinkedList<>();
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.PART_FOR_SALE WHERE fk_seller=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		    this.parts.add(PartForSale.get(rs.getLong("id")));
		}
	    }catch(SQLException se){
		throw new DBInteractionException("Can't get seller's parts list: "+ se.getMessage());
	    }
	}
	return parts;
    }

    public String getName() {
	return this.name;
    }

    public String getPhone() {
	return phone;
    }

    public String getEmail() {
	return email;
    }
    
    
    
    
   
}
