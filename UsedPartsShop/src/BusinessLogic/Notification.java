package BusinessLogic;

import BusinessLogic.Car.DBCarModel;
import BusinessLogic.Car.DBCustomersCars;
import BusinessLogic.Part.Part;
import BusinessLogic.Part.PartForSale;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Notification {
    
    private long id;
    private Tender tender;
    private Date data;

    public Notification(long id, Tender tender, Date data) {
	this.id = id;
	this.tender = tender;
	this.data = data;
    }

    public Tender getTender() {
	return tender;
    }

    @Override
    public String toString() {
	return tender.getPartRequest() + " - " + tender.getPartRequest().getCar() + " (Shop price: " + tender.getPartRequest().getShop_cost() +"р)" ;
    }
    
    
    public static Notification get(long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.NOTIFICATION WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs!=null && rs.next()){
		Tender t;
		try{
		    t = Tender.get(rs.getLong("fk_tender"));
		}
		catch(DBInteractionException e){
		    //Тэндера уже не существует
		    new Notification(rs.getLong("id"), null , null).delete();
		    return null;
		}
		return new Notification(rs.getLong("id"), t , new Date(rs.getTimestamp("create_date").getTime()));
	    }
	    else{
		throw new DBInteractionException("No notification with such Id was found");
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't get notification information: "+ se.getMessage());
	}
    }
    
    public void insert () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "INSERT INTO APP.NOTIFIATION(fk_tender) VALUES(?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    ins_st.setLong(1, this.tender.getId());

	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = rs_key.getLong(1);
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Can't insert notification to database!");
	}
    }

    private void delete() throws DBInteractionException {
	try {
	    Connection conn = ConnectionManager.getConnection();
	    final String delQuery = "DELETE FROM APP.NOTIFICATION WHERE id=?";
	    PreparedStatement del_st = conn.prepareStatement(delQuery);
	    del_st.setLong(1, this.id);
	    del_st.executeUpdate();
	} catch (SQLException ex) {
	    Logger.getLogger(DBCustomersCars.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't delete notification:\n " + ex.getMessage());
	}
    }
}
