package servicedapartment.checkout;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import servicedapartment.SwitchScene;
import servicedapartment.database.DatabaseFactory;

public class CheckoutController {
	@FXML Button checkoutB;
	@FXML Button back;
	@FXML TextField roomNumField;
	@FXML TextField nameField;
	
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private SwitchScene newScene = new SwitchScene();
	
	public void handleCheckout(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		String roomNumber = roomNumField.getText().trim();
		String customerName = nameField.getText().trim();
		
		if(roomNumber.isEmpty() || customerName.isEmpty()) {
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Please input both room number and customer name.");
			alert.showAndWait();
			return;
		}
		
		int roomID = factory.getRoomID(roomNumber);
		int customerID = factory.getCustomerID(customerName);
		boolean matchOrNot = factory.findOrderIDandUpdateStatus(roomID, customerID);
	
		if(matchOrNot) {
			alert.setTitle("Checkout successful");
			alert.setHeaderText(null);
			alert.setContentText("This order has successfully checked out.");
			alert.showAndWait();
			newScene.switchScene(event, "HomeUI.fxml");
		}else {
			alert.setTitle("Checkout falied");
			alert.setHeaderText(null);
			alert.setContentText("Sorry, no orders match with these information.");
			alert.showAndWait();
			roomNumField.clear();
			nameField.clear();
		}
	}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
}
