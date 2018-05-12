package servicedapartment.home;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import servicedapartment.data.SwitchScene;

/**
 * Control action of the components in HomeUI.fxml file.
 * @author Kunyaruk Katebunlu
 */
public class HomeController {
	@FXML private Button checkin;
	@FXML private Button checkout;
	@FXML private Button admin;
	private SwitchScene newScene = new SwitchScene();
	
	/**
	 * When this method is called, it will switch to Check-in part.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCheckIn(ActionEvent event) throws IOException {		
		newScene.switchScene(event, "/servicedapartment/checkin/CustomerInfoUI.fxml");
	}
	
	/**
	 * When this method is called, it will switch to Checkout part.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCheckOut(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/checkout/CheckoutUI.fxml");
	}
	
	/**
	 * When this method is called, it will switch to Administer part.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleAdmin(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/dataandpreferences/LoginUI.fxml");
	}
}
