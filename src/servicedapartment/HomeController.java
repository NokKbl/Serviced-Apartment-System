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
	
	/**
	 * When this method is called, it will change the Scene to check in part.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCheckIn(ActionEvent event) throws IOException {		
		newScene.switchScene(event, "checkin/CustomerInfoUI.fxml");
	}
	
	/**
	 * When this method is called, it will change the Scene to checkout part.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCheckOut(ActionEvent event) throws IOException {
		newScene.switchScene(event, "checkout/CheckoutUI.fxml");
	}
	
	/**
	 * When this method is called, it will change the Scene to admin part.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleAdmin(ActionEvent event) throws IOException {
		newScene.switchScene(event, "checkin/LoginUI.fxml");
	}
}
