package Service.ServerNumCars;

import BusinessLogic.Car.DBCarModel;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBServiceMapper {
    public static int getTendersNum(String carBrand, String carModel) throws DBInteractionException{
	int cntr=0;
	try {
	    Connection conn = ConnectionManager.getConnection();
	    long model_id = DBCarModel.getIdByModel(carBrand, carModel);
	    if (model_id==-1){
		throw new DBInteractionException("Incorrect brand or model");
	    }
	    PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM APP.CAR WHERE fk_car_model=?");
	    pstmt.setLong(1,model_id);
	    ResultSet rs = pstmt.executeQuery();
	    while(rs.next()){
		PreparedStatement pstmt2 = conn.prepareStatement("SELECT COUNT(*) FROM APP.PART_REQUEST WHERE fk_car=?");
		pstmt2.setLong(1,rs.getLong("id"));
		ResultSet rs2 = pstmt2.executeQuery();
		rs2.next();
		cntr+=rs2.getInt(1);
	    }
	    return cntr;
	} catch (SQLException ex) {
	    Logger.getLogger(DBServiceMapper.class.getName()).log(Level.SEVERE, null, ex);
	    throw new DBInteractionException("Can't get statistic. Reason: " + ex.getMessage());
	}
    }
}
