/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Car;

import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HOME
 */
public class DBCarBrand {
    
    private static HashMap <String, Long> carBrandMap = new HashMap <>();
    
    private static void getMap() throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.CAR_BRAND ORDER BY brand");
	    ResultSet rs = pstmt.executeQuery();
	    while(rs.next()){
		carBrandMap.put(rs.getString("brand"), rs.getLong("id"));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(DBCarBrand.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't get car's brand list: " + ex.getMessage());
	}
    }
    
    public static List<String> getBrandList() throws  DBInteractionException{
	List<String> carBrandList = new LinkedList<>();
	if (carBrandMap.isEmpty()){
	    getMap();
	}
	return new LinkedList<>(carBrandMap.keySet());
    }
    
    public static long getIdByBrand(String brand) throws  DBInteractionException{
	if (carBrandMap.isEmpty()){
	    getMap();
	}
	Long id = carBrandMap.get(brand);
	if (id == null)
	    return -1;
	else
	    return id;
    }
    
    public static String getBrandById(long id) throws  DBInteractionException{
	if (carBrandMap.isEmpty()){
	    getMap();
	}
	for (String b : carBrandMap.keySet()){
	    if( id == carBrandMap.get(b))
		return b;
	}
	return null;
    }
}
