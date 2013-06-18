
package Service.ServerNumCars;

import Exceptions.DBInteractionException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
	

public class JSONTendersNumMapper {
    public static JSONObject getTenderNum(final String carBrand, final String carModel) {
        try {
            Map<String, Integer> map = new HashMap<>(1);
            final int tendersNum = DBServiceMapper.getTendersNum(carBrand, carModel);
            map.put("current_tenders_num", tendersNum);
            return new JSONObject(map);
        } catch (DBInteractionException ex) {
            Map<String, String> map = new HashMap<>(1);
            map.put("error", ex.getMessage());
            return new JSONObject(map);
        }
    }
    
    private JSONTendersNumMapper() {}
}
