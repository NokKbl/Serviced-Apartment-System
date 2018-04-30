package servicedapartment.checkin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import servicedapartment.SwitchScene;

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
		newScene.switchScene(event, "checkin/RoomandPaymentUI.fxml");
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
}
