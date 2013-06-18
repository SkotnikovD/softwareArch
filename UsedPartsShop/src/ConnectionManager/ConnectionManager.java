package ConnectionManager;

import Exceptions.DBInteractionException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
//"jdbc:derby://localhost:1527/upsDB_ver2.0;user=client;password=12345"

public class ConnectionManager {
    static String url;
    public static void init(String url, String user, String pass){
	ConnectionManager.url = url+";"+"user="+user+";"+"password="+pass;
    }
    public static Connection getConnection() throws DBInteractionException{
	//return (DriverManager.getConnection("jdbc:derby:D:/Files/10sem/Software Arch/repo/upsDB_ver2.0/upsDB_ver2.0;user=client;password=12345"));
    	try{
	    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
	    return (DriverManager.getConnection(url));
	}
	catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
	    throw new DBInteractionException("Can't get connection to Database:\n" + ex.getMessage());
	}
    }

    public static void closeConnection(Connection conn) throws DBInteractionException {
	try{
	    conn.close();
	} catch(SQLException ex){
	    throw new DBInteractionException("Can't close connection to Database:\n" + ex.getMessage());
	}
    }
}
