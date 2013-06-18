package Service.ServerNumCars;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import org.json.JSONObject;

public class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String, Object> params = (Map<String, Object>) he.getAttribute("parameters");
        try (OutputStream ostream = he.getResponseBody()) {
            if (!params.containsKey("car_brand")) {
                String response = "Wrong parameters. Need parameter car_brand=<string>.";
                he.sendResponseHeaders(400, response.length());
                ostream.write(response.getBytes());
                return;
            }
	    if (!params.containsKey("car_model")) {
                String response = "Wrong parameters. Need parameter car_model=<string>.";
                he.sendResponseHeaders(400, response.length());
                ostream.write(response.getBytes());
                return;
            }
            
            final String carBrand = (String) params.get("car_brand");
	    final String carModel = (String) params.get("car_model");
            JSONObject object = JSONTendersNumMapper.getTenderNum(carBrand, carModel);
            final String response = object.toString();
            he.sendResponseHeaders(200, response.length());
            ostream.write(response.getBytes());
        }
    }
}
