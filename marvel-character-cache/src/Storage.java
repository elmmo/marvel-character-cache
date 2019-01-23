import java.util.TreeSet; 
import java.util.HashMap;

public class Storage {
	protected String attrText; 
	protected HashMap<Integer, Character> characters; 
	protected HashMap<String, Character[]> events; 
	protected int characterCount; 
	final int CHARACTER_LIMIT = 20; 
	final int EVENT_LIMIT = 3; 
	final int CHARACTER_DELETION_RATE = 5; 
	final int EVENT_DELETION_RATE = 1; 
	
	Storage() {
		attrText = null; 
		characters = new HashMap<Integer, Character>(); 
		events = new HashMap<String, Character[]>(); 
		characterCount = 0; 
	}
	
	public String strip(String str) {
		str = str.replaceAll(" ", ""); 
		str = str.replaceAll("-", ""); 
		return str.toLowerCase(); 
	}
	
	public void expandCharacters() {
		// find the smallest values (the oldest entries) in the characters array 
		TreeSet<Integer> oldest = new TreeSet<Integer>(); 
		for (Integer key : characters.keySet()) {
			if (oldest.size() < CHARACTER_DELETION_RATE) {
				oldest.add(key);
			} else {
				if (key > oldest.last()) {
					oldest.remove(oldest.last()); 
					oldest.add(key); 
				}
			}
		}
		
		while (!oldest.isEmpty()) {
			characters.remove(oldest.first());
			oldest.remove(oldest.first()); 
		}
	}
	
	public void printArray(Character[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].toString());
		}
	}
	
	public void printAllCharacters() {
		for (int i = 1; i < characters.size(); i++) {
			System.out.println(characters.get(i).toString());
		}
	}
}
