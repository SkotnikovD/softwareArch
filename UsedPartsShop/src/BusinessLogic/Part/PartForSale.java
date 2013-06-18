package BusinessLogic.Part;

import BusinessLogic.Car.DBCarModel;
import BusinessLogic.Car.DBCustomersCars;
import BusinessLogic.Seller;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartForSale {
    private long id;
    private String brand;
    private String model;
    private int formFactor;
    private int yearSince;
    private int yearUntil;
    private int category;
    private Part part;
    private Seller seller=null;

    public PartForSale(long id, String brand, String model, int formFactor, int yearSince, int yearUntil, int category, Part part, Seller seller) {
	this.id = id;
	this.brand = brand;
	this.model = model;
	this.formFactor = formFactor;
	this.yearSince = yearSince;
	this.yearUntil = yearUntil;
	this.category = category;
	this.part = part;
	this.seller = seller;
    }

    @Override
    public String toString() {
	String toStr=brand + " " + model;
	if (formFactor !=0){
	    toStr+=" " + Converter.getStringFactor(formFactor);
	}
	toStr+= " " + yearSince + " - " + yearUntil;
	if(category != 0){
	    toStr+= " " + Converter.getStringCategory(category);
	}
	if(part!=null){
	    toStr+=" " + part;
	}
	return  toStr;
    }
    
    
    
    public static PartForSale get(long id) throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP.PART_FOR_SALE WHERE id=?");
	    pstmt.setLong(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if(rs!=null && rs.next()){
		long fkCarModel = rs.getLong("fk_car_model");
		int category = rs.getInt("category");
		int formFactor = rs.getInt("form_factor");
		long fkPart = rs.getLong("fk_part");
		long fkSeller = rs.getLong("fk_seller");
		int yearS = rs.getInt("yearsince");
		int yearU = rs.getInt("yearuntil");
		
		String model = DBCarModel.getModelById(fkCarModel);
		String brand = DBCarModel.getBrandByModelId(fkCarModel);
		
		Part part=null;
		if(fkPart!=0){
		    part = Part.get(fkPart);
		} 
		return new PartForSale(id, brand, model, 
			formFactor, yearS, yearU, 
			category, part, null);
	    }
	    else{
		throw new DBInteractionException("No part for sale with such Id was found");
	    }
	}
	catch(SQLException se){
	    throw new DBInteractionException("Can't get part's for sale information: "+ se.getMessage());
	}
    }
    
    public void insert () throws DBInteractionException{
	try{
	    Connection conn = ConnectionManager.getConnection();
	    final String insQuery = "INSERT INTO APP.PART_FOR_SALE(fk_car_model, fk_part, yearSince, yearUntil, form_factor, category, fk_seller) VALUES(?,?,?,?,?,?,?)";
	    PreparedStatement ins_st = conn.prepareStatement(insQuery, Statement.RETURN_GENERATED_KEYS);
	    ins_st.setLong(1, DBCarModel.getIdByModel(brand, model));
	    if(this.part!=null)
		ins_st.setLong(2, this.part.getId());
	    else
		ins_st.setNull(2,java.sql.Types.INTEGER);
	    ins_st.setInt(3, this.yearSince);
	    ins_st.setInt(4, this.yearUntil);
	    ins_st.setInt(5, this.formFactor);
	    ins_st.setInt(6, this.category);
	    ins_st.setLong(7, this.getSeller().getId());

	    ins_st.executeUpdate();
	    ResultSet rs_key = ins_st.getGeneratedKeys();
	    if (rs_key.next()) {
		this.id = rs_key.getLong(1);
	    }
	} catch(SQLException e){
	    throw new DBInteractionException("Can't insert part for sale to database!");
	}
    }
    
    public Seller getSeller () throws DBInteractionException{
	if(this.seller==null){
	    try{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT fk_seller FROM APP.PART_FOR_SALE WHERE id=?");
		pstmt.setLong(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		Long fkSeller;
		if(rs!=null && rs.next()){
		    fkSeller = rs.getLong("fk_seller");
		}else{
		    throw new DBInteractionException("No seller for part for sale was found");
		}
		this.seller = Seller.get(fkSeller);
	    }catch(SQLException se){
		    throw new DBInteractionException("Can't get seller for part for sale information: "+ se.getMessage());
	    }
	}
	return this.seller;
    }
    
    public void delete() throws DBInteractionException {
	try {
	    Connection conn = ConnectionManager.getConnection();
	    final String delQuery = "DELETE FROM APP.PART_FOR_SALE WHERE id=?";
	    PreparedStatement del_st = conn.prepareStatement(delQuery);
	    del_st.setLong(1, this.id);
	    del_st.executeUpdate();
	} catch (SQLException ex) {
	    Logger.getLogger(DBCustomersCars.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    	    throw new DBInteractionException("Can't delete part for sale:\n " + ex.getMessage());
	}
    }
    
//    public static class Builder {
//
//	private String model;
//	private Category cat;
//	private DBFormFactor formFactor;
//	private Part part;
//	private int yearSince;
//	private int yearUntil;
//	
//	public Builder(CarModel model, int yearSince, int yearUntil) {
//	    this.model = model;
//	    this.yearSince = yearSince;
//	    this.yearUntil = yearUntil;
//	}
//
//	public Builder setCat(Category cat) {
//	    this.cat = cat;
//	    return this;
//	}
//
//	public Builder setFormFactor(DBFormFactor formFactor) {
//	    this.formFactor = formFactor;
//	    return this;
//	}
//
//	public Builder setPart(Part part) {
//	    this.part = part;
//	    return this;
//	}
//	
//	public PartForSale build(){
//	    return new PartForSale(this);
//	}
//    }
//    public PartForSale(Builder builder) {
//	this.model = builder.model;
//	this.cat = builder.cat;
//	this.formFactor = builder.formFactor;
//	this.part = builder.part;
//	this.yearSince = builder.yearSince;
//	this.yearUntil = builder.yearUntil;
//    }

    

	
}
