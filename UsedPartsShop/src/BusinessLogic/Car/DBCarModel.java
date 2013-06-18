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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HOME
 */
public class DBCarModel {
    public static List<String> getModelList(String brand) throws  DBInteractionException{
	try{
	    long brandId = DBCarBrand.getIdByBrand(brand);
	    final String query = "SELECT model FROM APP.CAR_MODEL WHERE fk_car_brand=?";
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setLong(1, brandId);
	    ResultSet rs = pstmt.executeQuery();
	    List<String> modList = new LinkedList<>();
	    while(rs.next()){
		modList.add(rs.getString("model"));
	    }
	    return modList;
	} catch (SQLException ex) {
	    Logger.getLogger(DBCarModel.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't get car's model list: " + ex.getMessage());
	}
    }
    
    public static long getIdByModel(String brand, String model) throws DBInteractionException{
	try{
	    long brandId = DBCarBrand.getIdByBrand(brand);
	    final String query = "SELECT id FROM APP.CAR_MODEL WHERE fk_car_brand=? AND model=?";
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setLong(1, brandId);
	    pstmt.setString(2, model);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return rs.getLong("id");
	    }
	    else{
		return -1;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(DBCarModel.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't get model's id by model: " + ex.getMessage());
	}
    }
    
    public static String getModelById(long id) throws DBInteractionException{
	try{
	    final String query = "SELECT model FROM APP.CAR_MODEL WHERE id=?";
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return rs.getString("model");
	    }
	    else{
		throw new DBInteractionException("No model wint such id");
	    }
	} catch (SQLException e){
	    throw new DBInteractionException("Cant get model my id from database");
	}
    }
    
    public static String getBrandByModelId(long modelId) throws DBInteractionException{
	try{
	    final String query = "SELECT brand FROM APP.CAR_BRAND " +
				 "INNER JOIN APP.CAR_MODEL " +
				 "ON APP.CAR_MODEL.FK_CAR_BRAND=APP.CAR_BRAND.ID " +
				 "WHERE APP.CAR_MODEL.ID=? ";
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setLong(1, modelId);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		return rs.getString("brand");
	    }
	    else{
		throw new DBInteractionException("No brand for model wint such id");
	    }
	} catch (SQLException e){
	    throw new DBInteractionException("Cant get brand by model's id from database");
	}
    }
}
