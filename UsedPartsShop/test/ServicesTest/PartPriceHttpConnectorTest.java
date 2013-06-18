package ServicesTest;

import Exceptions.ServiceException;
import Service.ClientGetPartPrice.JSONPartPriceResponse;
import Service.ClientGetPartPrice.PartPriceHttpConnector;
import Service.ClientGetPartPrice.Request;
import org.json.JSONException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class PartPriceHttpConnectorTest {
    
    @Test
    public void excuteRequestTest() throws ServiceException, JSONException{
	assertEquals(100500,new JSONPartPriceResponse(PartPriceHttpConnector.executeRequest(new Request("12345fghj"))).getPrice());
    }
    
}
