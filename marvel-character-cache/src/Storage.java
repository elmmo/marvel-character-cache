import java.util.HashMap;
import org.json.simple.JSONObject;

public class Storage {
	protected String attrText; 
	protected HashMap<Integer, Character> characters; 
	protected int characterCount; 
	
	Storage() {
		attrText = null; 
		characters = new HashMap<Integer, Character>(); 
		characterCount = 0; 
	}
}
