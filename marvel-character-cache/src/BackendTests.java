import java.lang.reflect.Method;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

public class BackendTests {
	Backend b; 
	
	@Before 
	public void setUp() {
		b = new Backend(); 
	}
	
	@Test
	public void constructURLTest() {
		String expected = "***********"; 
		Backend b = new Backend(); 
		try {
			Class c = b.getClass(); 
			Method method = c.getDeclaredMethod("generateHashKey", null); 
			method.setAccessible(true);
			Object res = method.invoke(b, null); 
			Assert.assertEquals(expected, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
