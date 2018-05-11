package servicedapartment.dataandpreferences;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import servicedapartment.SwitchScene;

/**
 * Control action of the components in AdminChoicesUI.fxml file.
 * @author Thanaphon Keawjam
 */
public class AdminChoicesController {
	@FXML private Button editButton;
	@FXML private Button roomButton;
	@FXML private Button historyButton;
	@FXML private Button statusButton;
	@FXML private Button logoutButton;
	private SwitchScene newScene = new SwitchScene();
	
	/**
	 * When this method is called, it will switch to the EditRates scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleEdit(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/EditRatesUI.fxml");
	}
	
	/**
	 * When this method is called, it will switch to the EditRooms scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleEditRoom(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/EditRoomsUI.fxml");
	}
	
	/**
	 * When this method is called, it will switch to the History scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleHistory(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/HistoryUI.fxml");
	}
	
	/**
	 * When this method is called, it will switch to the RoomStatus scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleRoomStatus(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/RoomStatusUI.fxml");
	}
	
	/**
	 * When this method is called, it will switch to the Login scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleLogout(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/LoginUI.fxml");
	}
	
}
