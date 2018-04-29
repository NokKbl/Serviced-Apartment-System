package servicedapartment;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
	@FXML
	Button checkin;
	@FXML
	Button checkout;
	@FXML
	Button admin;
	SwitchScene newScene = new SwitchScene();
	
	public void handleCheckIn(ActionEvent event) throws IOException {		
		newScene.switchScene(event, "checkin/CustomerInfoUI.fxml");
	}
	
	public void handleCheckOut(ActionEvent event) throws IOException {
		newScene.switchScene(event, "checkout/CheckoutUI.fxml");
	}
	
	public void handleAdmin(ActionEvent event) throws IOException {
		newScene.switchScene(event, "checkin/LoginUI.fxml");
	}
}
