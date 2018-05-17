package servicedapartment.data;

import java.util.ResourceBundle;

/**
 * Read a properties file.
 * @author Thanaphon Keawjam
 * @author Kunyaruk Katebunlu
 */
public class ReadFile {
	
	/**
	 * Read file admin.properties.
	 * @return Array of username, password and database url.
	 */
	public static String[] readFile() {
		String[] admin = new String[3];
		ResourceBundle bundle = ResourceBundle.getBundle("admin");
		String username = bundle.getString("username");
		String password = bundle.getString("password");
		String url = bundle.getString("url");
		
		if(username == null || password == null || url == null) {
			username = "admin";
			password = "123456admin";
			url = "jdbc:sqlite:CustomerLog.db";
		}
		
		admin[0] = username;
		admin[1] = password;
		admin[2] = url;
		
		return admin;
	}

}
