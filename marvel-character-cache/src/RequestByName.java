import org.json.simple.JSONObject;

public class RequestByName extends Request {

	RequestByName(APIConnection api) {
		super(api);
	}

	public JSONObject connectByName(String name) {
		String q = name.replaceAll(" ", "-"); 
		StringBuilder sb = new StringBuilder(); 
		sb.append(baseURL); 
		sb.append(String.format("name=%s&ts=%d&apikey=%s&hash=%s", q, requestId, PUBLIC_KEY, hash)); 
		return api.connect(sb.toString()); 
	}
}
