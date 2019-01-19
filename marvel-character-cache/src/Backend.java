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

public class Backend {
	final String PUBLIC_KEY = "**********************"; 
	final String PRIVATE_KEY = "**************************"; 
	String baseURL = "http://gateway.marvel.com/v1/public/characters?"; 
	String attrText; 
	int requestCount; 
	BufferedReader bReader; 
	HashMap<Integer, String[]> characters; 
	String[] properties = {"id", "name", "description", "thumbnail"};
	
	Backend() {
		requestCount = 1; 
		request("Spider Man"); 
	}
	
	private String generateHashKey() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		String toConvert = requestCount + PRIVATE_KEY + PUBLIC_KEY; 
		md.update(toConvert.getBytes());
		byte[] digest = md.digest(); 
		return DatatypeConverter.printHexBinary(digest).toLowerCase(); 
	}
	
	private void request(String name) {
		String q = name.replaceAll(" ", "-"); 
		try {
			connect(constructURL(q)); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private void connect(String url) {
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
			JSONObject json = (JSONObject)parser.parse(sb.toString()); 
			parseJson(json); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	private String constructURL(String name) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder(); 
		sb.append(baseURL);
		sb.append(String.format("name=%s&ts=%d&", name, requestCount));
		sb.append(String.format("apikey=%s&", PUBLIC_KEY));
		sb.append(String.format("hash=%s", generateHashKey()));
		requestCount++; 
		return sb.toString();
	}
	
	private void parseJson(JSONObject obj) {
		attrText = extract("attributionText", obj); 
		
		JSONObject data = extract("data", obj); 
		JSONArray results = extract("results", data); 
		JSONObject personal = (JSONObject)results.get(0); 
		
		int i = 0; 
		// look at later 
		for (String prop : properties) {
			if (extract(prop, personal) instanceof String) {
				properties[i] = extract(prop, personal); 
			} else if (extract(prop, personal) instanceof Long) {
				properties[i] = Long.toString(extract(prop, personal)); 
			} else if (extract(prop, personal) instanceof Integer) {
				properties[i] = Integer.toString(extract(prop, personal)); 
			} else if (extract(prop, personal) instanceof JSONObject) {
				JSONObject img = extract(prop, personal); 
				String path = extract("path", img); 
				String extension = extract("extension", img); 
				properties[i] = path + "." + extension; 
			}
			i++; 
		}
	}
	
	private <T> T extract(String target, JSONObject obj) {
		T extracted = (T)obj.get(target); 
		return extracted; 
	}
}
