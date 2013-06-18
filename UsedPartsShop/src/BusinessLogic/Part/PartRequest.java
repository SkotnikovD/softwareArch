package BusinessLogic.Part;

import BusinessLogic.Car.Car;
import BusinessLogic.Car.DBCustomersCars;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartRequest {
    private long id;
    private Car car;
    private Part part;
    private String serialNo;
    private int shop_cost;

    public PartRequest(long id, Car car, Part part, String serialNo, int shop_cost) {
	this.id = id;
	this.car = car;
	this.part = part;
	this.serialNo = serialNo;
	this.shop_cost = shop_cost;
    }

    @Override
    public String toString() {
	return part.toString();
    }

    public int getShop_cost() {
	return shop_cost;
    }
    
    public Car getCar() {
	return car;
    }

    public Part getPart() {
	return part;
    }

    public String getSerialNo() {
	return serialNo;
    }
    
    public static PartRequest get(long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.PART_REQUEST WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		Long fkPart = rs.getLong("fk_part");
		String serialNum = rs.getString("serial_num");
		Car carRcv = Car.get(rs.getLong("fk_car"));
		Part partRcv = Part.get(rs.getLong("fk_part"));
		int shopCostRcv = rs.getInt("shop_price");
		return new PartRequest(id, carRcv, partRcv, serialNum, shopCostRcv);
	    }
	    else{
		throw new DBInteractionException("No part request with such id\n " + id);
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Cant get part request: " + e.getMessage());
	}
    }
    
    public void insert () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "INSERT INTO APP.PART_REQUEST(fk_car, fk_part, serial_num, shop_price) VALUES(?,?,?,?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    
	    ins_st.setLong(1, this.car.getId());
	    ins_st.setLong(2, this.part.getId());
	    ins_st.setString(3, this.serialNo);
	    ins_st.setInt(4,  this.shop_cost);
	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = rs_key.getLong(1);
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Can't insert part request to database!");
	}
    }

    public long getId() {
	return this.id;
    }

    public void delete() throws DBInteractionException {
	try {
	    Connection conn = ConnectionManager.getConnection();
	    final String delQuery = "DELETE FROM APP.PART_REQUEST WHERE id=?";
	    PreparedStatement del_st = conn.prepareStatement(delQuery);
	    del_st.setLong(1, this.getId());
	    del_st.executeUpdate();
	} catch (SQLException ex) {
	    Logger.getLogger(DBCustomersCars.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't delete part request :\n " + ex.getMessage());
	}
    }
}
