import java.util.HashMap;
import java.util.Map.Entry;

public class Storage {
	protected String attrText; 
	protected HashMap<Integer, Character> characters; 
	protected HashMap<String, Character[]> events; 
	protected int characterCount; 
	
	Storage() {
		attrText = null; 
		characters = new HashMap<Integer, Character>(); 
		events = new HashMap<String, Character[]>(); 
		characterCount = 0; 
	}
	
	// delete by request after a given period of time 
	// get full response 
	
	public String strip(String str) {
		str = str.replaceAll(" ", ""); 
		str = str.replaceAll("-", ""); 
		return str.toLowerCase(); 
	}
}
