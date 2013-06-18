/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Part;

import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Part {
    long id;
    String partName;
    int category;
    private Boolean isUsers;

    public Part(long id, String partName, int category, Boolean isUsers) {
	this.partName = partName;
	this.category = category;
	this.isUsers = isUsers;
	this.id = id;
    }

    
    public String getPartName() {
	return partName;
    }
    
    private static List<Part> partsList = null;
    
    public static List<Part> getList() throws DBInteractionException{
	if (partsList!=null){
	    return partsList;
	} else{
	    try{
		partsList = new LinkedList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.PART");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		    partsList.add(new Part(rs.getLong("id"), rs.getString("part_name"), rs.getInt("category"), rs.getBoolean("is_user")));
		}
		return partsList;
	    } catch(SQLException ex){
		throw new DBInteractionException("Can't get part's list: " + ex.getMessage());
	    }
	}
    }
    
    public static List<Part> getListForCategory(int cat) throws DBInteractionException{
	List<Part> nl = new LinkedList <>();
	for(Part p: getList()){
	    if (p.category==cat){
		nl.add(p);
	    }
	}
	return nl;
    }

    @Override
    public String toString() {
	return partName;
    }
    
    

//    public String getCategory() {
//	switch(this.category){
//	    case 0: {
//		return("Other");
//	    }case 1: {
//		return("Interier");
//	    }case 2: {
//		return("WheelsAndRims");
//	    }case 3: {
//		return("airConditioner");
//	    }case 4: {
//		return("Body");
//	    }case 5: {
//		return("Suspension");
//	    }case 6: {
//		return("Steering");
//	    }case 7: {
//		return("Exhaust");
//	    }case 8: {
//		return("Coolant");
//	    }case 9: {
//		return("FuelSystem");
//	    }case 10: {
//		return("Coolant");
//	    }case 11: {
//		return("Breaks");
//	    }case 12: {
//		return("Transmission");
//	    }case 13: {
//		return("Changes");
//	    }case 14: {
//		return("Electro");
//	    }case 15: {
//		return("BearsAndSeals");
//	    }default: {
//		return ("Unknown category");
//	    }
//	}
//    }

    public Boolean isUsers() {
	return isUsers;
    }
    
    public static Part get(long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.PART WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return new Part(id, rs.getString("part_name"), rs.getInt("category"), rs.getBoolean("is_user"));
	    } else{
		throw new DBInteractionException("No part with id = " + id);
	    }
	}catch(SQLException e){
	    throw new DBInteractionException("Can't get part with id = " + id);
	}
    }

    public long getId() {
	return (this.id);
    }

    public long getCategory() {
	return this.category;
    }
}
