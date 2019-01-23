import java.util.Map.Entry;

import org.json.simple.JSONObject;

public class RequestByName extends Request {
	String name; 

	RequestByName(APIConnection api, Parser p, Storage s) {
		super(api, p, s);
	}
	
	public Character[] get(String name) {
		this.name = name; 
		int index = verify(name); 
		Character[] c = new Character[1]; 
		if (index == -1) {
			c = parser.parseToCharacter(connectByName());
		} else {
			c[0] = storage.characters.get(index); 
		}
		return c; 
	}
	
	public int verify(String query) { 
		String q = storage.strip(query); 
		for (Entry<Integer, Character> entry : storage.characters.entrySet()) {
			String expected = storage.strip(entry.getValue().getName()); 
			if (q.equals(expected)) return entry.getKey(); 
		}
		return -1; 
	}

	public JSONObject connectByName() {
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
