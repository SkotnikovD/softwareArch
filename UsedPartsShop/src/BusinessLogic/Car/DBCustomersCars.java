/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Car;

import BusinessLogic.Customer;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCustomersCars {
    public static void addCar(Car c, Customer cust) throws DBInteractionException{
	try {
	    Connection conn = ConnectionManager.getConnection();
	    final String selQuery = "SELECT id FROM APP.CUSTOMERS_CARS WHERE fk_cust=? AND fk_car=?";
	    PreparedStatement sel_st = conn.prepareStatement(selQuery);
	    sel_st.setLong(1, cust.getId());
	    sel_st.setLong(2, c.getId());
	    sel_st.executeQuery();
	    if(sel_st.getResultSet().next()){
		throw new DBInteractionException("Customer already has this car");
	    }
	    else{
		final String insQuery = "INSERT INTO APP.CUSTOMERS_CARS(fk_cust, fk_car) VALUES(?,?)";
		PreparedStatement ins_st = conn.prepareStatement(insQuery);
		ins_st.setLong(1, cust.getId());
		ins_st.setLong(2,  c.getId());
		ins_st.executeUpdate();
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(DBCustomersCars.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't link customer with car");
	}
    }
    
    public static void deleteCar(Car c, Customer cust) throws DBInteractionException{
	try {
	    Connection conn = ConnectionManager.getConnection();
	    final String delQuery = "DELETE FROM APP.CUSTOMERS_CARS WHERE fk_cust=? AND  fk_car=?";
	    PreparedStatement del_st = conn.prepareStatement(delQuery);
	    del_st.setLong(1, cust.getId());
	    del_st.setLong(2,  c.getId());
	    del_st.executeUpdate();
	} catch (SQLException ex) {
	    Logger.getLogger(DBCustomersCars.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't delete customer's car: " + ex.getMessage());
	}
    }
}
