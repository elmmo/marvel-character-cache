import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.json.simple.JSONObject;

/** 
 * Abstract class used for submitting all calls to the API 
 */
public abstract class Request {
	String baseURL = "http://gateway.marvel.com/v1/public/";
	final String PUBLIC_KEY = "2c8392e496d45de55500e5dd1748ba60"; 
	final String PRIVATE_KEY = "6cdcd2e0e33d1ddcc1823e941ed5f2cd2512b7e0"; 
	APIConnection api; 
	Parser parser; 
	Storage storage; 
	
	/** 
	 * Stores an instance of the api, the parser, and storage 
	 * @param api		the api connection to use 
	 * @param parser	the parser to use 
	 * @param storage	the storage to use 
	 */
	Request(APIConnection api, Parser parser, Storage storage) {
		this.api = api; 
		this.parser = parser; 
		this.storage = storage; 
	}
	
	/** 
	 * Abstract method intended for getting responses based on the query 
	 * @param name	the query 
	 * @return		an array of character objects 
	 */
	public abstract Character[] get(String name); 
	
	/** 
	 * Checks whether the object is already stored or needs to be looked up 
	 * @param query	the information to look for
	 * @return		an index or indicator of if the object is present 
	 */
	public abstract int verify(String query); 
	
	/** 
	 * Connects to the API and returns the response 
	 * @return	the JSONObject containing the response 
	 */
	public abstract JSONObject connect(); 
	
	/** 
	 * Generates the hash that needs to be passed to the endpoint call 
	 * @return	a String with the resulting hash 
	 */
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
