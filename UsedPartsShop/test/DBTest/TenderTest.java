/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTest;

import BusinessLogic.AuthoriseInfo;
import BusinessLogic.Car.Car;
import BusinessLogic.City;
import BusinessLogic.Customer;
import BusinessLogic.Part.Part;
import BusinessLogic.Part.PartForSale;
import BusinessLogic.Part.PartRequest;
import BusinessLogic.Seller;
import BusinessLogic.Tender;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author HOME
 */
public class TenderTest {
    
    public TenderTest() {
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
	
	delQuery = "DELETE FROM APP.NOTIFICATION";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.PART_FOR_SALE";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.TENDER";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.PART_REQUEST";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.CUSTOMER";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.SELLER";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.AUTHORISE_INFO";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.CAR";
	 del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
    }
    
    @Test
    public void sendNotiAllTest() throws DBInteractionException, SQLException{
	AuthoriseInfo ai = new AuthoriseInfo(0, "mypass", "my@mail.ru");
	List<City> cl = City.getCityList();
	Customer c = new Customer(0, "client1", cl.get(1), ai);
	c.insert();
	AuthoriseInfo ai2 = new AuthoriseInfo(0, "mypass2", "my2@mail.ru");
	Seller s = new Seller(0, "8-905-223-76-43", "mail@mail.ru", cl.get(1), "Seller Name", ai2);
	s.insert();
	
	Part pt = Part.getList().get(1);
	PartForSale pfs = new PartForSale(0, "Toyota", "Corolla", 1, 1990, 2000, 0, pt, s);
	pfs.insert();
	
	Car car = new Car(0, "Toyota", "Corolla", 1, 1995);
	car.insert();
	PartRequest pr = new PartRequest(0, car, pt, "12345qwerty", 1000);
	Tender t = new Tender(0, pr, c, null);
	t.insert();
	
	t.sendNotiAll();
	
	Connection conn = ConnectionManager.getConnection();
	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.NOTIFICATION WHERE fk_tender=? and fk_sell=?");
	pstmt.setLong(1, t.getId());
	pstmt.setLong(2, s.getId());
	ResultSet rs = pstmt.executeQuery();
	if(!rs.next()){
	    assertTrue(false, "No sutible notification was added");
	}
    }
}
