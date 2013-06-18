package BusinessLogic.Car;

import BusinessLogic.Customer;
import BusinessLogic.Part.Converter;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Car {
    private long id;
    private String brand;
    private String model;
    private int formFactor;
    private int year;

    public Car(long id, String brand, String model, int formFactor, int year) {
	this.id=id;
	this.brand = brand;
	this.model = model;
	this.formFactor = formFactor;
	this.year = year;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Car other = (Car) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.brand, other.brand)) {
	    return false;
	}
	if (!Objects.equals(this.model, other.model)) {
	    return false;
	}
	if (this.formFactor != other.formFactor) {
	    return false;
	}
	if (this.year != other.year) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return  brand + " " + model + " " + year + "y" + " " + Converter.getStringFactor(this.formFactor);
    }
    

    public String getBrand() {
	return brand;
    }
    
    public String getModel() {
	return model;
    }

    public int getFormFactor() {
	return formFactor;
    }

    public int getYear() {
	return year;
    }
    
    public long getId(){
	return this.id;
    }
    
    private void setId(long id) {
	this.id = id;
    }
    
    //--------------------------------------------------
    //-------------DATABASE INTERFACE-------------------
    //--------------------------------------------------
    
    public static Car get (long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.CAR WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()){
		String modelRcv = DBCarModel.getModelById(rs.getLong("fk_car_model"));
		String brandRcv = DBCarModel.getBrandByModelId(rs.getLong("fk_car_model"));
		int ffRcv = rs.getInt("form_factor");
		return new Car(id, brandRcv, modelRcv, ffRcv, rs.getInt("car_year"));
	    }
	    else{
		throw new DBInteractionException("No car with such Id was found");
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't get authorise information: "+ se.getMessage());
	}
    }
    
    public void insert () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    long modelId = DBCarModel.getIdByModel(this.brand, this.model);
	    final String query = "SELECT id FROM APP.CAR WHERE fk_car_model =? AND car_year=? AND form_factor=?";
	    PreparedStatement st = conn.prepareStatement(query);
	    
	    st.setLong(1, modelId);
	    st.setInt(2, this.year);
	    st.setInt(3,this.formFactor);
	    ResultSet rs = st.executeQuery();
	    if(rs.next()){
		//В БД уже есть такая же машина. Не добавляем ничгео, просто считываем id этой машины
		this.id = rs.getLong("id");
	    }	
	    else{
		final String insQuery = "INSERT INTO APP.CAR(fk_car_model, car_year, form_factor) VALUES(?,?,?)";
		PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
		ins_st.setLong(1, modelId);
		ins_st.setInt(2, this.year);
		ins_st.setInt(3, this.formFactor);

		ins_st.executeUpdate();
		ResultSet rs_key = ins_st.getGeneratedKeys();
		if (rs_key.next()) {
		    this.setId(rs_key.getLong(1));
		} 
	    }
	}catch(SQLException sq){
	    throw new DBInteractionException("Can't insert car: " + sq.getMessage());
	}
    }
}
