/** 
 * Tests the backend classes of the content cache 
 */
public class BackendTest {

	public static void main(String[] args) {
		APIConnection a = new APIConnection(); 
		Storage s = new Storage(); 
		Parser p = new Parser(s); 
		
		RequestByEvent event = new RequestByEvent(a, p, s); 
		RequestByName name = new RequestByName(a, p, s); 
		
		Character[] arr = event.get("Age of Ultron"); 
		s.printArray(arr);
		
		Character spidy = name.get("Spider-Man")[0];
		System.out.println(spidy);
		
//		RequestByName r = new RequestByName(a, p, s); 
//		System.out.println(s.characters.size());
//		System.out.println(r.get("Wolverine")[0]); 
//		System.out.println(r.get("Wolverine")[0]); 
//		System.out.println(s.characters.size());
//		
//		RequestByEvent e = new RequestByEvent(a, p, s);
//		
//		Character[] arr = e.get("Age of Ultron");
//		s.printArray(arr); 
//		Character[] newarr = e.get("Age of Ultron");
//		s.printArray(newarr); 
//		
//		s.printArray(e.get("Civil War"));
	}

}
