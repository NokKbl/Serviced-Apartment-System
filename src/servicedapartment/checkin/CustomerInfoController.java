package servicedapartment.checkin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import servicedapartment.SwitchScene;
import servicedapartment.customer.BasicInfo;

public class CustomerInfoController {
	@FXML TextField name;
	@FXML TextField phone;
	@FXML TextField email;
	@FXML TextField stay;
	@FXML ComboBox<String> units;
	@FXML TextField amount;
	@FXML DatePicker checkin;
	@FXML Button next;
	@FXML Button cancel;
	private SwitchScene newScene = new SwitchScene();
	
	public void initialize() {
		String[] unit = {"days", "weeks", "months", "years"};
		units.getItems().addAll(unit);
		units.getSelectionModel().select(0);
	}
	
	public void handleNext(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("checkin/RoomandPaymentUI.fxml"));
		Parent view = loader.load();
		Scene scene = new Scene(view);
		
		RoomandPaymentController controller = loader.getController();
		BasicInfo basicInfo = new BasicInfo(name.getText(), phone.getText(), email.getText(), Integer.parseInt(stay.getText()),
										units.getValue(), Integer.parseInt(amount.getText()), checkin.getValue());
		controller.initialize(basicInfo);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
}
