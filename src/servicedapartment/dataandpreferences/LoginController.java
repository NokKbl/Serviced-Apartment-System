package servicedapartment.dataandpreferences;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import servicedapartment.data.SwitchScene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Control the components in LoginUI.fxml file and check required input to go to next scene.
 * @author Thanaphon Keawjam
 */
public class LoginController {
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button loginButton;
	@FXML private Button backButton;
	private SwitchScene newScene = new SwitchScene();
	private final String USERNAME = "admin";
	private final String PASSWORD = "123456admin";
	
	/**
	 * Check required input (username and password) and switch to Administer part if both input are correct.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleLogin(ActionEvent event) throws IOException {
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		if(username.equals(USERNAME) && password.equals(PASSWORD)) {
			newScene.switchScene(event, "/servicedapartment/dataandpreferences/AdminChoicesUI.fxml");
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Alert");
			alert.setHeaderText("Wrong username or password!");
			alert.setContentText("Username or password is incorrect. Please try again.");
			alert.showAndWait();
		}
	}
	
	/**
	 * Switch back to the Home scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/home/HomeUI.fxml");
	}

}
