import org.json.simple.JSONObject;
import org.json.simple.JSONArray; 

public class RequestByEvent extends Request {
	StringBuilder sb; 
	String name; 
	
	RequestByEvent(APIConnection api, String name) {
		super(api);
		this.name = name; 
	}
	
	public void storeCharacters(Parser p, Storage s) { 
		p.parseToCharacter(connectByEvent());
	}

	public JSONObject connectByEvent() {
		int id = getEventId(); 
		sb = new StringBuilder(); 
		sb.append(baseURL); 
		sb.append(String.format("characters?events=%d&ts=%d&apikey=%s&hash=%s", id, requestId, PUBLIC_KEY, hash)); 
		return api.connect(sb.toString()); 
	}
	
	private int getEventId() {
		String q = name.replaceAll(" ", "%20"); 
		sb = new StringBuilder(); 
		sb.append(baseURL); 
		sb.append(String.format("events?name=%s&ts=%d&apikey=%s&hash=%s", q, requestId, PUBLIC_KEY, hash)); 
		JSONObject event = api.connect(sb.toString()); 
		JSONArray results = Parser.getResults(event); 
		JSONObject personal = (JSONObject)results.get(0); 
		Long l = Parser.extract("id", personal); 
		return l.intValue(); 
	}
}
