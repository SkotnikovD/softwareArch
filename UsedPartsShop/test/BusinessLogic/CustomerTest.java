///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package BusinessLogic;
//
//import BusinessLogic.Car.Car;
//import BusinessLogic.Car.DBFormFactor;
//import BusinessLogic.Part.Part;
//import BusinessLogic.Part.PartRequest;
//import Exceptions.DBInteractionException;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//
//
public class CustomerTest {
//    
    public CustomerTest() {
    }
//    
//    @BeforeClass
//    public void init(){
//	try{
//	    System.out.println("Cust init()");
//	    CustomerInfo ci1 = new CustomerInfo.Builder("Cust1", "SPb").setName("Dmitry").setSurname("Skotnikov").build();
//	    AuthoriseInfo ai = new AuthoriseInfo("pass1", "mail1@mail.ru");
//	    Customer c = new Customer(ci1, 1, ai );
//	    DBCustomer.registerNewCustomer(c);
//
//	    CustomerInfo ci2 = new CustomerInfo.Builder("Cust2", "SPb").setName("Vladimir").setSurname("Putin").build();
//	    AuthoriseInfo ai2 = new AuthoriseInfo("pass2", "mail2@mail.ru");
//	    Customer c2 = new Customer(ci2, 2, ai2 );
//	    DBCustomer.registerNewCustomer(c2);
//	}
//	catch(Exception ex){}
//    }
//    
//    @AfterClass
//    public void tearDown(){
//    }
//    
//    @Test (expectedExceptions=DBInteractionException.class)
//    public void RegisterWithEmailThatAlreadyExists() throws DBInteractionException {
//	System.out.println("Cust regEmail()");
//	    CustomerInfo ci1 = new CustomerInfo.Builder("Cust3", "SPb").setName("Dmitry3").setSurname("Skotnikov3").build();
//	    AuthoriseInfo ai = new AuthoriseInfo("pass3", "mail2@mail.ru");
//	    Customer c = new Customer(ci1, 3, ai );
//	    DBCustomer.registerNewCustomer(c);
//    }
//    
    @Test
    void TestGetCustomerById(){
//	System.out.println("Cust testId()");
//	Customer c = DBCustomer.getCustomerById(1);
//	Assert.assertEquals(c.getId(),1);
    }
//    
//    
//    @Test
//    public void OpenNewTenders(){
//	System.out.println("Cust openNew()");
//	Customer c = DBCustomer.getCustomerById(1);
//	Car car = new Car(new CarModel("Tempo", new CarBrand("Ford")),DBFormFactor.SEDAN, 1987);
//	PartRequest p = new PartRequest(car, new Part("Fuiel filter", Category.Changes, false), "777777");
//	Tender ten = c.createTender(p);
//	for(Tender t : c.getListAcitveTenders()){
//	    if (ten == t) return;
//	}
//	Assert.fail();
//    }
//    
//    @Test
//    public void CloseTender(){
//	System.out.println("Cust closeT()");
//	Customer c = DBCustomer.getCustomerById(1);
//	Car car = new Car(new CarModel("Corolla", new CarBrand("Totota")),DBFormFactor.SEDAN, 2006);
//	PartRequest p = new PartRequest(car, new Part("Rare window", Category.Body, false), "666666");
//	Tender ten = c.createTender(p);
//	c.closeTender(ten);
//	for(Tender t : c.getListAcitveTenders()){
//	    if (t==ten) Assert.fail();
//	}
//	for(Tender t : c.getListClosedTenders()){
//	    if (t==ten) return;
//	}
//	Assert.fail();
    }
//    
//}
