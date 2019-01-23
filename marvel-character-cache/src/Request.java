import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public abstract class Request {
	String baseURL = "http://gateway.marvel.com/v1/public/characters?";
	final String PUBLIC_KEY = "2c8392e496d45de55500e5dd1748ba60"; 
	final String PRIVATE_KEY = "6cdcd2e0e33d1ddcc1823e941ed5f2cd2512b7e0"; 
	APIConnection api; 
	int requestId;
	String hash; 
	
	Request(APIConnection api) {
		this.api = api; 
		this.requestId = api.requestCount; 
		try {
			this.hash = generateHashKey(); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private String generateHashKey() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		String toConvert = requestId + PRIVATE_KEY + PUBLIC_KEY;
		md.update(toConvert.getBytes());
		byte[] digest = md.digest(); 
		return DatatypeConverter.printHexBinary(digest).toLowerCase(); 
	}
}
