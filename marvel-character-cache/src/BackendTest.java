
public class BackendTest {

	public static void main(String[] args) {
		APIConnection a = new APIConnection(); 
		Storage s = new Storage(); 
		Parser p = new Parser(s); 
		RequestByName r = new RequestByName(a, "Spider Man");
		RequestByEvent e = new RequestByEvent(a, "Age of Ultron"); 
		
		r.storeCharacters(p, s);
		e.storeCharacters(p, s);
		
		System.out.println(s.characters);
	}

}
