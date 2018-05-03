package servicedapartment.dataandpreferences;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import servicedapartment.SwitchScene;

public class LoginController {
	
	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	@FXML
	Button loginButton;
	@FXML
	Button backButton;
	
	private SwitchScene newScene = new SwitchScene();
	
	private final String USERNAME = "admin";
	private final String PASSWORD = "123456admin";
	
	public void handleLogin(ActionEvent event) throws IOException {
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		if(username.equals(USERNAME) && password.equals(PASSWORD)) {
			newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Wrong username or password! Please try again");
			alert.showAndWait();
		}
	}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}

}
