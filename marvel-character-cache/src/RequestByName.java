import org.json.simple.JSONObject;

public class RequestByName extends Request {
	String name; 

	RequestByName(APIConnection api, String name) {
		super(api);
		this.name = name; 
	}
	
	public void storeCharacters(Parser p, Storage s) { 
		p.parseToCharacter(connectByName());
	}

	public JSONObject connectByName() {
		String q = name.replaceAll(" ", "-"); 
		StringBuilder sb = new StringBuilder(); 
		sb.append(baseURL); 
		sb.append(String.format("characters?name=%s&ts=%d&apikey=%s&hash=%s", q, requestId, PUBLIC_KEY, hash)); 
		return api.connect(sb.toString()); 
	}
}
