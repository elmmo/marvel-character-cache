
public class BackendTest {

	public static void main(String[] args) {
		APIConnection a = new APIConnection(); 
		Storage s = new Storage(); 
		Parser p = new Parser(s); 
		
		RequestByEvent e = new RequestByEvent(a, p, s); 
		RequestByName n = new RequestByName(a, p, s); 
		
		Character[] arr = e.get("Age of Ultron"); 
		s.printArray(arr);
		
		Character spidy = n.get("Spider-Man")[0];
		
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
