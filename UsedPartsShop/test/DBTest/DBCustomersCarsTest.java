/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTest;

import BusinessLogic.AuthoriseInfo;
import BusinessLogic.Car.Car;
import BusinessLogic.Car.DBCustomersCars;
import BusinessLogic.City;
import BusinessLogic.Customer;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author HOME
 */
public class DBCustomersCarsTest {
    
    public DBCustomersCarsTest() {
    }
    
    @BeforeClass
    public void init() throws DBInteractionException, SQLException{
	ConnectionManager.init("jdbc:derby://localhost:1527/D:/Files/10sem/Software Arch/repo/upsDB_ver2.0_forTest", "client", "12345");
    }
    
    @AfterClass
    public  void fin() throws DBInteractionException, SQLException{
	Connection conn = ConnectionManager.getConnection();
	String  delQuery = "DELETE FROM APP.CUSTOMERS_CARS";
	PreparedStatement del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate(); 
	
	delQuery = "DELETE FROM APP.CAR";
	 del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.CUSTOMER";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.AUTHORISE_INFO";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
    }
    
    @Test
    public void addCarTest() throws DBInteractionException, SQLException{
	Car c = new Car(0, "Toyota", "Corolla", 1, 1);
	City city = City.get(1);
	AuthoriseInfo ai = new AuthoriseInfo(0, "qwer", "mail@mail.ru");
	Customer cust = new Customer(0, "nick", city , ai);
	cust.insert();
	cust.addCar(c);
	//DBCustomersCars.addCar(c, cust);
	
	Connection conn = ConnectionManager.getConnection();
	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.CUSTOMERS_CARS WHERE fk_car=? and fk_cust=?");
	pstmt.setLong(1, c.getId());
	pstmt.setLong(2, cust.getId());
	ResultSet rs = pstmt.executeQuery();
	if(!rs.next()){
	    assertTrue(false, "Car wasn't added to customer");
	}
    }
    
    @Test
    public void deleteCarTest() throws DBInteractionException, SQLException{
	Car c = new Car(0, "Toyota", "Corolla", 2, 1);
	City city = City.get(1);
	AuthoriseInfo ai = new AuthoriseInfo(0, "qwer2", "mail2@mail.ru");
	Customer cust = new Customer(0, "nick2", city , ai);
	cust.insert();
	cust.addCar(c);
	cust.removeCar(c);
	//DBCustomersCars.addCar(c, cust);
	DBCustomersCars.deleteCar(c, cust);    
	
	Connection conn = ConnectionManager.getConnection();
	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.CUSTOMERS_CARS WHERE fk_car=? and fk_cust=?");
	pstmt.setLong(1, c.getId());
	pstmt.setLong(2, cust.getId());
	ResultSet rs = pstmt.executeQuery();
	if(rs.next()){
	    assertTrue(false, " Customer's car wasn't deleted");
	}
    }
}
