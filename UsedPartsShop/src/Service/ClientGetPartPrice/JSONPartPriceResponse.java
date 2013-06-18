package Service.ClientGetPartPrice;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HOME
 */
public class JSONPartPriceResponse {
    private final int price;
    
    public JSONPartPriceResponse(final JSONObject object) throws JSONException {
	this.price = object.getInt("part_price");
    }

    public int getPrice() {
	return price;
    }
}
