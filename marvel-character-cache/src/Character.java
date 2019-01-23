import org.json.simple.JSONObject;

/** 
 * Models the character data to be stored
 */
public class Character {
	long id; 
	String name; 
	String description; 
	String thumbnail; 
	JSONObject response; 
	
	/** 
	 * Stores data received from the API 
	 * @param id	the id of the character that corresponds to the Marvel API 
	 * @param name	the name of the character
	 * @param desc	a description of the character 
	 * @param tn	a String representation of an URL image of the character 
	 * @param obj	the JSON response from the API about the character 
	 */
	Character(long id, String name, String desc, String tn, JSONObject obj) {
		this.id = id;
		this.name = name; 
		this.description = desc; 
		this.thumbnail = tn;
		this.response = obj; 
	}
	
	/** 
	 * Overrides the toString() method to provide data about the character 
	 */
	public String toString() {
		return String.format("%s\nID: %d\nDescription: %s\n", name.toUpperCase(), id, description); 
	}
	
	/** 
	 * @return	character id 
	 */
	public long getId() { return id; }
	
	/** 
	 * @return	character name 
	 */
	public String getName() { return name; }
	
	/** 
	 * @return	character description
	 */
	public String getDescription() { return description; }
	
	/** 
	 * @return	thumbnail image of the character 
	 */
	public String getThumbnail() { return thumbnail; } 
	
	/** 
	 * @return	the JSON response from the query 
	 */
	public JSONObject getResponse() { return response; }
	
	/** 
	 * Helper function for setting the response post-request 
	 * @param res	the object to set to the response 
	 */
	public void setResponse(JSONObject res) { response = res; }
}
