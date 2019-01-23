import org.json.simple.JSONObject;

public class Character {
	long id; 
	String name; 
	String description; 
	String thumbnail; 
	JSONObject response; 
	
	Character(long id, String name, String desc, String tn, JSONObject obj) {
		this.id = id;
		this.name = name; 
		this.description = desc; 
		this.thumbnail = tn;
		this.response = obj; 
	}
}
