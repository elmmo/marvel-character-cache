import static org.junit.Assert.*;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert; 

public class BackendTests {
	Character c; 
	Character[] arr;
	
	@Before
	public void setUp() {
		c = new Character(12931, "Spider Man", "Some description here", "someimage.jpg", new JSONObject());
		
		APIConnection a = new APIConnection();
		Storage s = new Storage();
		Parser p = new Parser(s);
		
		RequestByEvent event = new RequestByEvent(a, p, s); 
		RequestByName name = new RequestByName(a, p, s); 
				
		arr = event.get("Age of Ultron");
		//s.printArray(arr);
		
		Character spidy = name.get("Spider-Man")[0];
		//System.out.println(spidy);
		
	}

	@Test
	public void test() {
		Assert.assertEquals("Spider Man", c.getName());
		for(int i = 0; i<arr.length; i++) {
			//Assert.assertEquals("Iron Man", arr[i]);
		}		
		
	}

}
