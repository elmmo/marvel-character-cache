import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter; 

public class Backend {
	final String PUBLIC_KEY = "**********"; 
	final String PRIVATE_KEY = "***************"; 
	String baseURL = "http://gateway.marvel.com/v1/public/characters?"; 
	int requestCount; 
	
	Backend() {
		requestCount = 1; 
		
		try {
			String req = constructURL("Spider-Man"); 
			makeRequest(req); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	private String generateHashKey() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		String toConvert = requestCount + PRIVATE_KEY + PUBLIC_KEY; 
		md.update(toConvert.getBytes());
		byte[] digest = md.digest(); 
		return DatatypeConverter.printHexBinary(digest).toLowerCase(); 
	}
	
	public void makeRequest(String url) {
		try {
			URL reqURL = new URL(url); 
			HttpURLConnection con = (HttpURLConnection)reqURL.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			int status = con.getResponseCode();
			System.out.println(status);
			System.out.println(con.getContent());
			con.disconnect(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public String constructURL(String name) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder(); 
		sb.append(baseURL);
		sb.append(String.format("name=%s&ts=%d&", name, requestCount));
		sb.append(String.format("apikey=%s&", PUBLIC_KEY));
		sb.append(String.format("hash=%s", generateHashKey()));
		requestCount++; 
		return sb.toString();
	}
}
