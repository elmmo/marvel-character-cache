
public class BackendTest {

	public static void main(String[] args) {
		APIConnection a = new APIConnection(); 
		Storage s = new Storage(); 
		Parser p = new Parser(s); 
		
		p.parseJson(a.request("Spider-Man"));
		
	}

}
