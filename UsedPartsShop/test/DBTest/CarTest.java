/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTest;

import BusinessLogic.Car.Car;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author HOME
 */
public class CarTest {
    
    public CarTest() {
    }
    @BeforeClass
    public void init() throws DBInteractionException, SQLException{
	ConnectionManager.init("jdbc:derby://localhost:1527/D:/Files/10sem/Software Arch/repo/upsDB_ver2.0_forTest", "client", "12345");
    }
    
    @AfterClass
    public  void fin() throws DBInteractionException, SQLException{
	Connection conn = ConnectionManager.getConnection();
	final String delQuery = "DELETE FROM APP.CAR";
	PreparedStatement del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
    }
    
    @Test
    public void insertTest() throws DBInteractionException, SQLException{
	Car c = new Car(0, "Toyota", "Corolla", 1, 1992);
	c.insert();
	Connection conn = ConnectionManager.getConnection();
	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.CAR WHERE id=?");
	pstmt.setLong(1, c.getId());
	ResultSet rs = pstmt.executeQuery();
	if(!rs.next()){
	    assertTrue(false, "No insertation was made!");
	}
    }
    
    @Test 
    public void getTest() throws DBInteractionException{
	Car c = new Car(0, "Toyota", "Corolla", 1, 1994);
	c.insert();
	Car c2 = Car.get(c.getId());
	assertEquals(c, c2);
    }
}
