package BusinessLogic;

import BusinessLogic.Car.Car;
import BusinessLogic.Car.DBCustomersCars;
import BusinessLogic.Part.Part;
import BusinessLogic.Part.PartRequest;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bid {
    private long id;
    private int cost;
    private String additionalInf;
    private Seller seller=null;
    private Tender tender=null;

    public Bid(long id, int cost, String additionalInf, Seller s, Tender t) {
	this.id = id;
	this.cost = cost;
	this.additionalInf = additionalInf;
	this.seller = s;
	this.tender = t;
    }

    public String getAdditionalInf() {
	return additionalInf;
    }

    public int getCost() {
	return cost;
    }
    
    public Seller getSeller() throws DBInteractionException{
	if (this.seller==null){
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT fk_seller FROM APP.BID WHERE id=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
		    return Seller.get(rs.getLong("fk_seller"));
		}
		else{
		    throw new DBInteractionException("No Seller found for Bid with id " + id);
		}
	    } catch(SQLException e){
		throw new DBInteractionException("Cant get seller for bid: " + e.getMessage());
	    }
	}
	return this.seller;
    }

    @Override
    public String toString() {
	return "cost: " + cost + "р";
    }
    
    public Tender getTender() throws DBInteractionException{
	if( this.tender == null){
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT fk_tender FROM APP.BID WHERE id=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
		    return Tender.get(rs.getLong("fk_tender"));
		}
		else{
		    throw new DBInteractionException("No Tender found for Bid with id " + id);
		}
	    } catch(SQLException e){
		throw new DBInteractionException("Cant get seller for bid: " + e.getMessage());
	    }
	}
	return this.tender;
    }
    
    public static Bid get(long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.BID WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		String addInf = rs.getString("add_inf");
		int costRcv = rs.getInt("cost");
		return new Bid(id, costRcv, addInf, null, null);
	    }
	    else{
		throw new DBInteractionException("No bid found with such id " + id);
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Cant get bid: " + e.getMessage());
	}
    }

    public void insert() throws DBInteractionException {
	try{
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "INSERT INTO APP.BID(cost, add_inf, fk_tender, fk_seller) VALUES(?,?,?,?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    ins_st.setLong(1, this.cost);
	    ins_st.setString(2, this.additionalInf);
	    ins_st.setLong(3, this.getTender().getId());
	    ins_st.setLong(4, this.getSeller().getId());
	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = rs_key.getLong(1);
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Can't insert bid to database!");
	}
    }

    public void delete() throws DBInteractionException {
	try {
	    Connection conn = ConnectionManager.getConnection();
	    final String delQuery = "DELETE FROM APP.BID WHERE id=?";
	    PreparedStatement del_st = conn.prepareStatement(delQuery);
	    del_st.setLong(1, this.id);
	    del_st.executeUpdate();
	} catch (SQLException ex) {
	    Logger.getLogger(DBCustomersCars.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't delete bid:\n " + ex.getMessage());
	}
    }
    
}
