import org.json.simple.JSONObject;
import java.util.Map.Entry;
import org.json.simple.JSONArray; 

public class RequestByEvent extends Request {
	StringBuilder sb; 
	String name; 
	
	RequestByEvent(APIConnection api, Parser p, Storage s) {
		super(api, p, s);
	}
	
	public Character[] get(String event) { 
		this.name = event; 
		Character[] c = null; 
		if (verify(name) == -1) {
			try {
				c = parser.parseToCharacter(connect()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			storage.events.put(name, c); 
		} else {
			c = storage.events.get(event); 
		}
		return c; 
	}
	
	public int verify(String query) { 
		String q = storage.strip(query); 
		for (Entry<String, Character[]> entry : storage.events.entrySet()) {
			String expected = storage.strip(entry.getKey()); 
			if (q.equals(expected)) return 1; 
		}
		return -1; 
	}

	public JSONObject connect() {
		int id = getEventId(); 
		String hash = generateHashKey(); 
		if (hash != null) {
			sb = new StringBuilder(); 
			sb.append(baseURL); 
			sb.append(String.format("characters?events=%d&ts=%d&apikey=%s&hash=%s", id, api.getRequestCount(), PUBLIC_KEY, hash)); 
			return api.connect(sb.toString()); 
		} else {
			throw new RuntimeException("Connection terminated. Hash generation failed."); 
		}
	}
	
	private int getEventId() {
		String q = name.replaceAll(" ", "%20"); 
		String hash = generateHashKey(); 
		if (hash != null) {
			sb = new StringBuilder(); 
			sb.append(baseURL); 
			sb.append(String.format("events?name=%s&ts=%d&apikey=%s&hash=%s", q, api.getRequestCount(), PUBLIC_KEY, hash)); 
			JSONObject event = api.connect(sb.toString()); 
			JSONArray results = Parser.getResults(event); 
			JSONObject personal = (JSONObject)results.get(0); 
			Long l = Parser.extract("id", personal); 
			return l.intValue(); 
		} else {
			throw new RuntimeException("Connection terminated. Hash generation failed."); 
		}
	}
}
