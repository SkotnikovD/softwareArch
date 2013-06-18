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

public class DBFormFactor {
    //SUV, CABRI, SEDAN, HATCH, COUP, TRUCK
    
    private static HashMap <String, Long> formFactorMap = new HashMap <>();
    private static void getMap() throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.FORM_FACTOR");
	    ResultSet rs = pstmt.executeQuery();
	    while(rs.next()){
		formFactorMap.put(rs.getString("form_factor"), rs.getLong("id"));
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Cant get list of formfactors: " + e.getMessage());
	}
    }
    
    public static long getId(String formFactor) throws DBInteractionException{
	if(formFactorMap.isEmpty())
	    getMap();
	Long id = formFactorMap.get(formFactor);
	if (id == null){
	    throw new DBInteractionException("Can't get id of such form factor");
	}
	return id;
    }
    
    public static String get(long id) throws DBInteractionException{
	if(formFactorMap.isEmpty()){
	    getMap();
	}
	for (String s : formFactorMap.keySet()){
	    if (id == formFactorMap.get(s))
		return s;
	    }
	throw new DBInteractionException("Can't get form factor with such id");
    }
}
