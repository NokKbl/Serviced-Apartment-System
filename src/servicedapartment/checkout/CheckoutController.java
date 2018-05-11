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

/**
 * Control the action of the components in CheckoutUI.fxml file and change status of room in database if checkout successful.
 * @author Kunyaruk Katebunlu
 * @author Thanaphon Keawjam
 */
public class CheckoutController {
	@FXML private Button checkoutB;
	@FXML private Button back;
	@FXML private TextField roomNumField;
	@FXML private TextField nameField;
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private SwitchScene newScene = new SwitchScene();
	
	/**
	 * Get the required information to find the identical data in database and checkout.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCheckout(ActionEvent event) throws IOException {
		String roomNumber = roomNumField.getText().trim();
		String customerName = nameField.getText().trim();
		int roomID = factory.getRoomID(roomNumber);
		int customerID = factory.getCustomerID(customerName);
		boolean matchOrNot = factory.findOrderIDandUpdateStatus(roomID, customerID);
	
		if(roomNumber.isEmpty() || customerName.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Alert");
			alert.setHeaderText("Blank field");
			alert.setContentText("Please input both room number and customer name.");
			alert.showAndWait();
		} else if(matchOrNot) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Alert");
			alert.setHeaderText("Checkout successful");
			alert.setContentText("This order has successfully checked out.");
			alert.showAndWait();
			newScene.switchScene(event, "HomeUI.fxml");
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Alert");
			alert.setHeaderText("Checkout falied");
			alert.setContentText("Sorry, no orders match with these information.");
			alert.showAndWait();
			roomNumField.clear();
			nameField.clear();
		}
	}
	
	/**
	 * Switch back to the Home scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
}
