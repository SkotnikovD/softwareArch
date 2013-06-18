/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTest;

import BusinessLogic.AuthoriseInfo;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author HOME
 */
public class AuthoriseInfoTest {
    static long authInfId;

    public AuthoriseInfoTest() {
    }
    
    @BeforeClass
    public void init() throws DBInteractionException, SQLException{
	ConnectionManager.init("jdbc:derby://localhost:1527/D:/Files/10sem/Software Arch/repo/upsDB_ver2.0_forTest", "client", "12345");
	
	Connection conn = ConnectionManager.getConnection();
	final String insQuery = "INSERT INTO APP.AUTHORISE_INFO(pass, email) VALUES(?,?)";
	PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	ins_st.setString(1, "12345");
	ins_st.setString(2, "mail@mail.ru");
	ins_st.executeUpdate();
	ResultSet rs_key = ins_st.getGeneratedKeys();
	if (rs_key.next()) {
	    authInfId = rs_key.getLong(1);
	}
    }
    
    @AfterClass
    public  void fin() throws DBInteractionException, SQLException{
	Connection conn = ConnectionManager.getConnection();
	final String delQuery = "DELETE FROM APP.AUTHORISE_INFO";
	PreparedStatement del_st = conn.prepareStatement(delQuery);
	del_st.executeUpdate();
    }
    
    @Test
    public  void testInsert() throws DBInteractionException{
	AuthoriseInfo au = new AuthoriseInfo(0, "123457", "mail2@mail.ru");
	au.insert();
	AuthoriseInfo au2 = AuthoriseInfo.get(au.getId());
	assertEquals(au, au2);
    }
    
    @Test
    public void testGet() throws DBInteractionException{
	AuthoriseInfo ai = AuthoriseInfo.get(authInfId);
	assertEquals(ai.getId(), authInfId);
	assertEquals(ai.getEmail(), "mail@mail.ru");
	assertEquals(ai.getPass(), "12345");
    }
    @Test 
    public void testUpdate() throws DBInteractionException{
	AuthoriseInfo ai2 = new AuthoriseInfo(0, "qwerty", "mymail2@mail.ru");
	ai2.insert();
	ai2.setPass("newpass");
	ai2.setEmail("newmail@mail.ru");
	ai2.update();
	AuthoriseInfo ai3= AuthoriseInfo.get(ai2.getId());
	assertEquals(ai3.getId(), ai2.getId());
	assertEquals(ai3.getEmail(), "newmail@mail.ru");
	assertEquals(ai3.getPass(), "newpass");
    }
    
    @Test
    public void testSearch() throws DBInteractionException {
	long aiId = AuthoriseInfo.search("12345", "mail@mail.ru");
	assertEquals(aiId, authInfId);
    }
    
    @Test(expectedExceptions = DBInteractionException.class)
    public void testSameMail() throws DBInteractionException{
	AuthoriseInfo au = new AuthoriseInfo(0, "123457", "mail@mail.ru");
	au.insert();
    }
}
