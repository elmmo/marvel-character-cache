import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Parser {
	Storage storage; 
	
	Parser(Storage s) {
		storage = s; 
	}
	
	public void parseToCharacter(JSONObject obj) {
		// if this is the first session request, set the attribution text 
		if (storage.attrText == null) {
			storage.attrText = extract("attributionText", obj); 
		}
	
		JSONArray results = getResults(obj); 
		
		for (int i = 0; i < results.size(); i++) {
			JSONObject storedResp = null; 
			
			JSONObject personal = (JSONObject)results.get(i); 
			long id = extract("id", personal); 
			String name = extract("name", personal); 
			String desc = extract("description", personal); 
			JSONObject img = extract("thumbnail", personal); 
			String path = extract("path", img) + "." + extract("extension", img); 
			storage.characterCount++; 
			
			// if there's only one object returned, store the json response in the character 
			if (results.size() == 1) storedResp = obj; 
			
			storage.characters.put(storage.characterCount, new Character(id, name, desc, path, storedResp)); 
		}
	}
	
	public static JSONArray getResults(JSONObject obj) {
		JSONObject data = extract("data", obj); 
		return extract("results", data); 
	}
	
	protected static <T> T extract(String target, JSONObject obj) {
		T extracted = (T)obj.get(target); 
		return extracted; 
	}
}
