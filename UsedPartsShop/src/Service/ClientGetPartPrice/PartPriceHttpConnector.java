/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ClientGetPartPrice;

import Exceptions.ServiceException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HOME
 */
public class PartPriceHttpConnector {
     public static JSONObject executeRequest(final Request request) throws ServiceException {
        try {
//            final URL url = request.getUrl();
//            final URLConnection connection = url.openConnection();
//            connection.connect();
//            final InputStream stream = connection.getInputStream();
	    FileReader f = new FileReader("D:\\Files\\10sem\\Software Arch\\repo\\UsedPartsShop\\src\\Service\\ClientGetPartPrice\\answer.json");
            return new JSONObject(streamToString(f));
        } catch (IOException | JSONException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    private static String streamToString(final Reader stream) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        try (final BufferedReader reader = new BufferedReader(stream)) {
            final String ls = System.getProperty("line.separator");
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
                line = reader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    private PartPriceHttpConnector() {}
}
