package BusinessLogic;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package BusinessLogic;
//
//import BusinessLogic.Car.CarBrand;
//import BusinessLogic.Car.CarModel;
//import BusinessLogic.Part.PartForSale;
//import DataBase.DBSeller;
//import Exceptions.DBInteractionException;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
///**
// *
// * @author HOME
// */
public class SellerTest {
//    
//    @BeforeClass
//    public void init() throws DBInteractionException{
//	System.out.println("Sell init()");
//	SellerInfo si1 = new SellerInfo.Builder("Org1", "SPb", "8-905-777-77-77").setName("Dmitry").build();
//	AuthoriseInfo ai = new AuthoriseInfo("pass1", "mails1@mail.ru");
//	Seller s = new Seller(si1, 1, ai );
//	DBSeller.registerNewSeller(s);
//	
//	SellerInfo si2 = new SellerInfo.Builder("Org2", "SPb", "8-906-777-77-77").setName("Sergey").build();
//	AuthoriseInfo ai2 = new AuthoriseInfo("pass2", "mails2@mail.ru");
//	Seller s2 = new Seller(si2, 2, ai2 );
//	DBSeller.registerNewSeller(s2);
//    }
//    
//    @Test
//    public void addPart() {
//	
////	SellerInfo si2 = new SellerInfo.Builder("Org2", "SPb", "8-906-777-77-77").setName("Sergey").build();
////	AuthoriseInfo ai2 = new AuthoriseInfo("pass2", "mails2@mail.ru");
////	Seller s2 = new Seller(si2, 2, ai2 );
////	DBSeller.registerNewSeller(s2);
//
//	System.out.println("Sell addPart()");
//	for (Seller cBuf : DBSeller.getSellerList()){
//	    System.out.println(cBuf.getId());
//	}
//	Seller s = DBSeller.getSellerById(1);
//	System.out.println(s);
//	PartForSale p = new PartForSale.Builder(new CarModel("Tempo", new CarBrand("Ford")), 1980, 1990).build();
//	s.addPartForSale(p);
//	for(PartForSale pfs : s.getSellParts()){
//	    if(pfs.equals(p))
//		return;
//	}
//	Assert.fail();
    }
//}
