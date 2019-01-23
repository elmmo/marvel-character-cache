import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/** 
 * All requests are managed and faciliated by this class 
 */
public class APIConnection {
	BufferedReader bReader; 
	int requestCount; 
	
	/** 
	 * Instantiates the API connection and keeps track of the numbe of requests 
	 */
	APIConnection() {
		requestCount = 1; 
	}
	
	/** 
	 * Provides the base connection the API 
	 * @param url	the endpoint to hit on the API
	 * @return		a JSON object with the response
	 */
	protected JSONObject connect(String url) {
		StringBuilder sb = new StringBuilder(); 
		JSONParser parser = new JSONParser(); 
		try {
			URL reqURL = new URL(url); 
			HttpURLConnection con = (HttpURLConnection)reqURL.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			int status = con.getResponseCode();
			
			if (status == 200) {
				bReader = new BufferedReader(new InputStreamReader(con.getInputStream())); 
				String line; 
				while ((line = bReader.readLine()) != null) {
					sb.append(line); 
				}
				bReader.close(); 
			}
			con.disconnect(); 
			requestCount++; 
			return (JSONObject)parser.parse(sb.toString()); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null; 
	}
	
	/** 
	 * Gets the number of requests for the purposes of providing a timestamp 
	 * @return	the number of requests
	 */
	public int getRequestCount() { return requestCount; } 
}
