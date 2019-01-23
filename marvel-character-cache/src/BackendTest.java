
public class BackendTest {

	public static void main(String[] args) {
		APIConnection a = new APIConnection(); 
		Storage s = new Storage(); 
		Parser p = new Parser(s); 
		RequestByName r = new RequestByName(a, p, s); 
		System.out.println(s.characters.size());
		System.out.println(r.get("Wolverine")[0]); 
		System.out.println(r.get("Wolverine")[0]); 
		System.out.println(s.characters.size());
		
		RequestByEvent e = new RequestByEvent(a, p, s);
		
		System.out.println(e.get("Age of Ultron"));
		System.out.println(e.get("Age of Ultron"));
		System.out.println(s.characters.size());
	}

}
