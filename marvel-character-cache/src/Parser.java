import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Parser {
	Storage storage; 
	
	Parser(Storage s) {
		storage = s; 
	}
	
	public Character[] parseToCharacter(JSONObject obj) throws Exception {
		// if this is the first session request, set the attribution text 
		if (storage.attrText == null) {
			storage.attrText = extract("attributionText", obj); 
		}
	
		JSONArray results = getResults(obj); 
		
		// ensures that no more than the max number of entries will be in the cache at any given time 
		int entries = (results.size() > storage.CHARACTER_LIMIT) ? storage.CHARACTER_LIMIT : results.size(); 
		
		// ensures that there's enough room in the cache to add more entries
		boolean canAdd = true; 
		int limit = storage.CHARACTER_DELETION_RATE; 
		int tracker = 0; 
		do { 
			if (entries + storage.characters.size() > storage.CHARACTER_LIMIT) { 
				storage.expandCharacters(); 
			} else { 
				canAdd = false; 
			} 
			tracker++; 
			if (tracker >= limit) throw new Exception("There's probably an error because the refresh has gone on too long."); 
		} while (canAdd); 
		
		Character[] added = new Character[entries]; 
		
		for (int i = 0; i < entries; i++) {
			JSONObject storedResp = null; 
			
			JSONObject personal = (JSONObject)results.get(i); 
			long id = extract("id", personal); 
			String name = extract("name", personal); 
			String desc = extract("description", personal).equals("") ? "No description available." : extract("description", personal); 
			JSONObject img = extract("thumbnail", personal); 
			String path = extract("path", img) + "." + extract("extension", img); 
			storage.characterCount++; 
			
			// if there's only one object returned, store the json response in the character 
			storedResp = (results.size() == 1) ? obj : personal; 
			
			Character c = new Character(id, name, desc, path, storedResp); 
			storage.characters.put(storage.characterCount, c); 
			added[i] = c; 
		}
		return added; 
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
