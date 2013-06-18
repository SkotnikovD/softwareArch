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
import java.sql.Statement;
import java.util.Objects;

/**
 *
 * @author HOME
 */
public class AuthoriseInfo {
    long id;
    String pass;
    String email;

    public AuthoriseInfo(long id, String pass, String email) {
	this.id = id;
	this.pass = pass;
	this.email = email;
    }

    public void setPass(String pass) {
	this.pass = pass;
    }

    public void setEmail(String email) {
	this.email = email;
    }
    
    public String getPass() {
	return pass;
    }

    public String getEmail() {
	return email;
    }

    public long getId() {
	return id;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final AuthoriseInfo other = (AuthoriseInfo) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.pass, other.pass)) {
	    return false;
	}
	if (!Objects.equals(this.email, other.email)) {
	    return false;
	}
	return true;
    }
    
    
    //--------------------------------------------------
    //-------------DATABASE INTERFACE-------------------
    //--------------------------------------------------
    
    public void insert () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery2 = "SELECT email FROM APP.AUTHORISE_INFO WHERE email=?";
	    PreparedStatement ins_st2 = conn.prepareStatement(insQuery2);
	    ins_st2.setString(1, this.email);
	    ResultSet rs_key2 = ins_st2.executeQuery();
	    if (rs_key2.next()){
		throw new DBInteractionException("User with email " + this.email + "already exists");
	    }
	    
	    final String insQuery = "INSERT INTO APP.AUTHORISE_INFO(pass, email) VALUES(?,?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    ins_st.setString(1, this.pass);
	    ins_st.setString(2, this.email);

	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = (rs_key.getLong(1));
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't execute authorise information insertation: "+ se.getMessage());
	}
    }
    
    public void update () throws DBInteractionException{
	try{    
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "UPDATE APP.AUTHORISE_INFO SET " +
				     "pass=?, email=?" +
				     "where id=?";
	    PreparedStatement upd_st = conn.prepareStatement(insQuery);
	    upd_st.setString(1, this.pass);
	    upd_st.setString(2, this.email);
	    upd_st.setLong(3, this.id);
	    upd_st.executeUpdate();
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't execute update of authorise information: "+ se.getMessage());
	}
    }
    
    public static AuthoriseInfo get (long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.AUTHORISE_INFO WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return new AuthoriseInfo(rs.getLong("id"), rs.getString("pass"), rs.getString("email"));
	    }
	    else{
		throw new DBInteractionException("No authorise info with such Id was found");
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't get authorise information: "+ se.getMessage());
	}
    }
    
    public static long search (String pass, String email) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM APP.AUTHORISE_INFO WHERE pass=? AND email=?");
	    pstmt.setString(1, pass);
	    pstmt.setString(2, email);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return rs.getLong("id");
	    }
	    else{
		throw new DBInteractionException("No authorise info found");
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't get authorise information: "+ se.getMessage());
	}
    }
}
