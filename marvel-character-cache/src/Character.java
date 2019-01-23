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
	
	public String toString() {
		return String.format("%s\nID: %d\nDescription: %s\n", name.toUpperCase(), id, description); 
	}
	
	public long getId() { return id; }
	
	public String getName() { return name; }
	
	public String getDescription() { return description; }
	
	public String getThumbnail() { return thumbnail; } 
	
	public JSONObject getResponse() { return response; }
	
	public void setResponse(JSONObject res) { response = res; }
}
