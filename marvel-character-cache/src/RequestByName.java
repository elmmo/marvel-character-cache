import java.security.NoSuchAlgorithmException;

public class RequestByName extends Request {

	RequestByName(APIConnection api) {
		super(api);
	}

	public String constructByName(String name) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder(); 
		sb.append(baseURL); 
		sb.append(String.format("name=%s&ts=%d&apikey=%s&hash=%s", name, requestId, PUBLIC_KEY, hash)); 
		return sb.toString();
	}
}
