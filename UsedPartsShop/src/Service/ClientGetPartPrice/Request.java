/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ClientGetPartPrice;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author HOME
 */
public class Request {
    private static final String requestString = "http://www.siteThatCanDoIt.com/?serialNum=";
    private final String serialNum;

    public Request(String serialNum) {
	this.serialNum = serialNum;
    }
    
    public URL  getUrl() throws MalformedURLException {
        return new URL(requestString + serialNum);
    }
}
