package servicedapartment.dataandpreferences;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import servicedapartment.SwitchScene;

public class EditRoomController {
	
	@FXML
	Button addButton;
	@FXML
	Button removeButton;
	@FXML
	Button backButton;
	@FXML
	TextField stdField;
	@FXML
	TextField onebrField;
	@FXML
	TextField twobrField;
	@FXML
	TextField threebrField;
	
	private SwitchScene newScene = new SwitchScene();

	public void handleAdd(ActionEvent event) {
		String stdRoom = stdField.getText().trim();
		String oneBed = onebrField.getText().trim();
		String twoBed = twobrField.getText().trim();
		String threeBed = threebrField.getText().trim();
		
		
	}
	public void handleRemove(ActionEvent event) {}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}
}
