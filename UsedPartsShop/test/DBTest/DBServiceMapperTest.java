/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTest;

import BusinessLogic.Car.Car;
import BusinessLogic.Part.Part;
import BusinessLogic.Part.PartRequest;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import Service.ServerNumCars.DBServiceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author HOME
 */
public class DBServiceMapperTest {
    @BeforeClass
    public void init() throws DBInteractionException, SQLException{
	ConnectionManager.init("jdbc:derby://localhost:1527/D:/Files/10sem/Software Arch/repo/upsDB_ver2.0_forTest", "client", "12345");
    }
    
    @AfterClass
    public  void fin() throws DBInteractionException, SQLException{
	Connection conn = ConnectionManager.getConnection();
	String delQuery = "DELETE FROM APP.PART_REQUEST";
	PreparedStatement  del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
	
	delQuery = "DELETE FROM APP.CAR";
	del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
    }
    
    @Test
    public void  getTenderNumTest() throws DBInteractionException{
	Car c = new Car(0, "Toyota", "Corolla", 1, 1995);
	c.insert();
	Part p = Part.getList().get(1);
	PartRequest pr = new PartRequest(0, c, p, "qwerty123", 12345);
	pr.insert();
	PartRequest pr2 = new PartRequest(0, c, p, "qwerty1234", 123456);
	pr2.insert();
	PartRequest pr3 = new PartRequest(0, c, p, "qwerty12345", 1234567);
	pr3.insert();
	Car c2 = new Car(0, "Toyota", "Crown", 1, 1997);
	c2.insert();
	Part p2 = Part.getList().get(2);
	PartRequest pr4 = new PartRequest(0, c2, p2, "qwerty12345ctg", 1237);
	pr4.insert();
	
	assertEquals(3, DBServiceMapper.getTendersNum("Toyota", "Corolla"));
    }
}
