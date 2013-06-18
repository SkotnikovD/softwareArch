/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author HOME
 */
public class City {
    private Long id;
    private String city;

    public City(Long id, String city) {
	this.id = id;
	this.city = city;
    }
     
    private static List<City> cityList = new LinkedList<>();
    
    public static List<City> getCityList() throws DBInteractionException{
	try{
	    if (cityList.isEmpty()){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.CITY");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
		    cityList.add(new City(rs.getLong("id"), rs.getString("city")));
		}
	    }
	    return cityList;
	}
	catch(SQLException e){
	    throw new DBInteractionException("Unable to receive city list");
	}
    }
    
    @Override
    public String toString(){
	return this.getCity();
    }
    
    public static City get (long city_id) throws DBInteractionException{
	if(cityList.isEmpty()){
	    getCityList();
	}
	for(City c: cityList){
	    if (c.id==city_id)
		return c;
	}
	throw new DBInteractionException("No city with Id = " + city_id);
    }

    public Long getId() {
	return id;
    }

    public String getCity() {
	return city;
    }
}
