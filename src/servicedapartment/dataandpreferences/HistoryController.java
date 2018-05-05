package servicedapartment.dataandpreferences;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import servicedapartment.SwitchScene;

public class HistoryController {
	
	@FXML
	Button backButton;
	
	private SwitchScene newScene = new SwitchScene();
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}

}
