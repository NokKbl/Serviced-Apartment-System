package servicedapartment.data;

import java.util.ResourceBundle;

/**
 * Read file properties.
 * @author Thanaphon Keawjam
 */
public class ReadFile {
	
	/**
	 * Read file admin.properties.
	 * @return Array of username and password.
	 */
	public static String[] readFile() {
		String[] admin = new String[2];
		ResourceBundle bundle = ResourceBundle.getBundle("admin");
		String username = bundle.getString("username");
		String password = bundle.getString("password");
		
		if(username == null || password == null) {
			username = "admin";
			password = "123456admin";
		}
		
		admin[0] = username;
		admin[1] = password;
		
		return admin;
	}

}
