package servicedapartment.dataandpreferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import servicedapartment.SwitchScene;
import servicedapartment.data.RoomInfo;
import servicedapartment.database.DatabaseFactory;

/**
 * Control components in EditRoomsUI.fxml file and do add/remove rooms activities.
 * @author Thanaphon Keawjam
 */
public class EditRoomController {
	@FXML private Button addButton;
	@FXML private Button removeButton;
	@FXML private Button backButton;
	@FXML private ComboBox<String> roomBox;
	@FXML private TextField textField;
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private SwitchScene newScene = new SwitchScene();
	private Room room = new Room();
	private String select, field, title, content;
	private RoomView view;
	
	/**
	 * Initialize components.
	 */
	@FXML
	public void initialize() {
		roomBox.getItems().addAll("Studio Room", "1-Bed Room", "2-Bed Room", "3-Bed Room");
		roomBox.setStyle("-fx-font: 18px \"Comic Sans MS\";");
		roomBox.getSelectionModel().select(0);
		
		view = new RoomView(room);
		room.addObserver(view);
		view.run();
	}

	/**
	 * Set title and content of Alert with INFORMATION content.
	 * @param title is a text that will be set as title of Alert.
	 * @param content is a text that will show as an information content in Alert.
	 */
	public void showInformationAlert(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	/**
	 * Set title and content of Alert with WARNING content.
	 * @param title is a text that will be set as title of Alert.
	 * @param content is a text that will show as a warning content in Alert.
	 */
	public void showWarningAlert(String title, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	/**
	 * Add new room into the database if it doesn't exist.
	 */
	public void handleAdd(ActionEvent event) {
		List<RoomInfo> data = factory.readDataFromRoom();
		field = textField.getText().trim();
		select = roomBox.getValue();
		int typeId = 0;
		
		for(RoomInfo x : data) {
			if(field.equals(x.getRoomNumb())) {
				title = "Warning";
				content = "Sorry, room number " + field + " already exists.";
				showWarningAlert(title, content);
				return;
			}
		}
		
		switch (select) {
		case "Studio Room": typeId = 1;
			break;
		case "1-Bed Room": typeId = 2;
			break;
		case "2-Bed Room": typeId = 3;
			break;
		case "3-Bed Room": typeId = 4;
			break;
		default: break;
		}
		
		if(!field.isEmpty()) {
			RoomInfo addRoom = new RoomInfo(field, typeId);
			factory.insertDataToRooms(addRoom);
			view.run();
			
			title = "Success";
			content = "Room number "+ field +" added successfully.";
			showInformationAlert(title, content);
		}
	}
	
	/**
	 * Remove a room from the database if the room exist.
	 */
	public void handleRemove(ActionEvent event) {
		List<RoomInfo> data = new ArrayList<>();
		field = textField.getText().trim();
		select = roomBox.getValue();
		int typeId = 0;
		
		for(RoomInfo x : data) {
			if(!field.equals(x.getRoomNumb())) {
				title = "Warning";
				content = "Room number" + field + " doesn't exist. Please input an exists room number.";
				showWarningAlert(title, content);
				return;
			}
		}
		
		switch(select) {
		case "Studio Room": typeId = 1;
			break;
		case "1-Bed Room": typeId = 2;
			break;
		case "2-Bed Room": typeId = 3;
			break;
		case "3-Bed Room": typeId = 4;
			break;
		default: break;
		}
		
		if(!field.isEmpty()) {
			RoomInfo delRoom = new RoomInfo(field, typeId);
			factory.deleteDataFromRoom(delRoom);
			view.run();
			
			title = "Success";
			content = "Room number "+ field +" removed successfully.";
			showInformationAlert(title, content);
		}	
	}
	
	/**
	 * Switch back to the AdminChoice scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
		view.exit();
	}
	
}
