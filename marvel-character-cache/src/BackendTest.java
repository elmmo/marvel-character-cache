
public class BackendTest {

	public static void main(String[] args) {
		APIConnection a = new APIConnection(); 
		Storage s = new Storage(); 
		Parser p = new Parser(s); 
		RequestByName r = new RequestByName(a); 
		
		p.parseJson(r.connectByName("Spider Man"));
		
	}

}
