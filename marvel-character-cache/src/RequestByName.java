import java.util.Map.Entry;
import org.json.simple.JSONObject;

/** 
 * Implements the methods enforced by Request to create requests by event name 
 *
 */
public class RequestByName extends Request {
	String name; 

	/** 
	 * Uses the existing super constructor to store the necessary components 
	 * @param api		the api connection to use 
	 * @param parser	the parser to use 
	 * @param storage	the storage to use 
	 */
	RequestByName(APIConnection api, Parser p, Storage s) {
		super(api, p, s);
	}
	
	/** 
	 * Gets the array of characters returned when querying -- will only return one entry 
	 * @param name		the name of the array to query 
	 * @return			the character that matches the name 
	 */
	public Character[] get(String name) {
		this.name = name; 
		int index = verify(name); 
		Character[] c = new Character[1];
		if (index == -1) {
			System.out.println("called from -1");
			try {
				c = parser.parseToCharacter(connect());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("called from 1");
			c[0] = storage.characters.get(index); 
		}
		return c; 
	}
	
	/** 
	 * Determines if the desired character is already in storage or not 
	 * @return	the index of the character in storage if the character is present, or -1 if not 
	 */
	public int verify(String query) { 
		String q = storage.strip(query); 
		for (Entry<Integer, Character> entry : storage.characters.entrySet()) {
			String expected = storage.strip(entry.getValue().getName()); 
			if (q.equals(expected)) return entry.getKey(); 
		}
		return -1; 
	}

	/** 
	 * Connects to the API by creating a request based on the event name 
	 * @return	the JSON response 
	 */
	public JSONObject connect() {
		String hash = generateHashKey(); 
		if (hash != null) {
			String q = name.replaceAll(" ", "-"); 
			StringBuilder sb = new StringBuilder(); 
			sb.append(baseURL); 
			sb.append(String.format("characters?name=%s&ts=%d&apikey=%s&hash=%s", q, api.getRequestCount(), PUBLIC_KEY, hash)); 
			return api.connect(sb.toString()); 
		} else {
			throw new RuntimeException("Connection terminated. Hash generation failed."); 
		}
	}
}
