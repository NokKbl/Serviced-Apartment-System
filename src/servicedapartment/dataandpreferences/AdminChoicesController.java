package servicedapartment.dataandpreferences;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import servicedapartment.SwitchScene;

public class AdminChoicesController {

	@FXML
	Button editButton;
	@FXML
	Button roomButton;
	@FXML
	Button historyButton;
	@FXML
	Button statusButton;
	@FXML
	Button logoutButton;
	
	private SwitchScene newScene = new SwitchScene();
	
	public void handleEdit(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/EditRatesUI.fxml");
	}
	
	public void handleEditRoom(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/EditRoomsUI.fxml");
	}
	
	public void handleHistory(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/HistoryUI.fxml");
	}
	
	public void handleRoomStatus(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/RoomStatusUI.fxml");
	}
	
	public void handleLogout(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/LoginUI.fxml");
	}
	
}
