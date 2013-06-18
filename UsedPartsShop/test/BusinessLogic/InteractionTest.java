///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package BusinessLogic;
//
//import BusinessLogic.Car.Car;
//import BusinessLogic.Car.CarBrand;
//import BusinessLogic.Car.CarModel;
//import BusinessLogic.Car.DBFormFactor;
//import BusinessLogic.Part.Category;
//import BusinessLogic.Part.Part;
//import BusinessLogic.Part.PartRequest;
//import BusinessLogic.Part.PartForSale;
//import DataBase.DBCustomer;
//import DataBase.DBSeller;
//import Exceptions.DBInteractionException;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
///**
// *
// * @author HOME
// */
public class InteractionTest {
//    
//    public InteractionTest() {
//    }
//    
//    @BeforeClass
//    public void init() throws DBInteractionException{
//	CustomerInfo ci1 = new CustomerInfo.Builder("Cust4", "SPb").setName("Dmitry").setSurname("Skotnikov").build();
//	AuthoriseInfo ai = new AuthoriseInfo("pass4", "mail5@mail.ru");
//	Customer c = new Customer(ci1, 5, ai );
//	DBCustomer.registerNewCustomer(c);
//	
//	SellerInfo si1 = new SellerInfo.Builder("Org3", "SPb", "8-903-777-77-77").setName("Dmitry").build();
//	AuthoriseInfo ais = new AuthoriseInfo("pass1", "mails3@mail.ru");
//	Seller s = new Seller(si1, 3, ais );
//	DBSeller.registerNewSeller(s);
//    }
//    
//    @Test
//    public void notificDeliverTest(){
//	
//	Seller s = DBSeller.getSellerById(3);
//	PartForSale ps = new PartForSale.Builder(new CarModel("Tempo", new CarBrand("Ford")), 1980, 1990).build();
//	s.addPartForSale(ps);
//	
//	Customer c = DBCustomer.getCustomerById(5);
//	System.out.println("Inter Notify()" + c.getId());
//	for (Customer cBuf : DBCustomer.getCustList())
//	    System.out.println(cBuf.getId());
//	Car car = new Car(new CarModel("Tempo", new CarBrand("Ford")),DBFormFactor.SEDAN, 1987);
//	PartRequest pr = new PartRequest(car, new Part("Fuiel filter", Category.Changes, false), "777777");
//	Tender ten = c.createTender(pr);
//	for(Notification n : s.getNotifications()){
//	    if(n.getTender().equals(ten))
//		return;
//	}
//	Assert.fail();
//    }
}
