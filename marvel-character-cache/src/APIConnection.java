import java.io.BufferedReader;
import java.util.HashMap; 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import javax.xml.bind.DatatypeConverter; 

public class APIConnection {
	BufferedReader bReader; 
	int requestCount; 
	
	APIConnection() {
		requestCount = 1; 
	}
	
	public JSONObject request(String name) {
		String q = name.replaceAll(" ", "-"); 
		try {
			RequestByName r = new RequestByName(this); 
			return connect(r.constructByName("Spider-Man")); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	private JSONObject connect(String url) {
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
			return (JSONObject)parser.parse(sb.toString()); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null; 
	}
}
