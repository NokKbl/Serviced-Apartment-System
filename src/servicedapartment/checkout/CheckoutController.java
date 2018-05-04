package servicedapartment.checkout;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import servicedapartment.SwitchScene;

public class CheckoutController {
	@FXML Button checkoutB;
	@FXML Button back;
	private SwitchScene newScene = new SwitchScene();
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
}
