import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;

public abstract class Request {
	String baseURL = "http://gateway.marvel.com/v1/public/";
	final String PUBLIC_KEY = "2c8392e496d45de55500e5dd1748ba60"; 
	final String PRIVATE_KEY = "6cdcd2e0e33d1ddcc1823e941ed5f2cd2512b7e0"; 
	APIConnection api; 
	Parser parser; 
	Storage storage; 
	
	Request(APIConnection api, Parser parser, Storage storage) {
		this.api = api; 
		this.parser = parser; 
		this.storage = storage; 
	}
	
	public abstract Character[] get(String name); 
	
	public abstract int verify(String query); 
	
	public abstract JSONObject connect(); 
	
	protected String generateHashKey() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			String toConvert = api.getRequestCount() + PRIVATE_KEY + PUBLIC_KEY;
			md.update(toConvert.getBytes());
			byte[] digest = md.digest(); 
			return DatatypeConverter.printHexBinary(digest).toLowerCase(); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace(); 
		}
		return null; 
	}
}
