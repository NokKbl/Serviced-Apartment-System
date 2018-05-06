package servicedapartment.checkout;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import servicedapartment.SwitchScene;
import servicedapartment.database.Database;

public class CheckoutController {
	
	@FXML Button checkoutB;
	@FXML Button back;
	@FXML TextField roomNumField;
	@FXML TextField nameField;
	
	private Database factory = Database.getInstance();
	private SwitchScene newScene = new SwitchScene();
	
	public void handleCheckout(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		String roomNumber = roomNumField.getText().trim();
		String customerName = nameField.getText().trim();
		
		if(roomNumber.isEmpty() || customerName.isEmpty()) {
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Please input room number or customer name");
			alert.showAndWait();
			return;
		}
		
		int roomID = factory.getRoomID(roomNumber);
		int customerID = factory.getCustomerID(customerName);
		boolean matchOrNot = factory.findOrderID(roomID, customerID);
	
		if(matchOrNot) {
			alert.setTitle("Successful");
			alert.setHeaderText(null);
			alert.setContentText("Checkout successful");
			alert.showAndWait();
		}else {
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Checkout failed");
			alert.showAndWait();
		}
	}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
}
