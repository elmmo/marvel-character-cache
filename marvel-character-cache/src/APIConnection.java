import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class APIConnection {
	BufferedReader bReader; 
	int requestCount; 
	
	APIConnection() {
		requestCount = 1; 
	}
	
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
	
	public int getRequestCount() { return requestCount; } 
}
