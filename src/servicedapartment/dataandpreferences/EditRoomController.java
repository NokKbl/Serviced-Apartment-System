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
import servicedapartment.data.RoomInfo;
import servicedapartment.data.SwitchScene;
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
	private String select, field, title, content, header;
	private RoomView view;
	
	/**
	 * Initialize components.
	 */
	@FXML
	public void initialize() {
		roomBox.getItems().addAll("Studio Room", "1-Bedroom", "2-Bedroom", "3-Bedroom");
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
	public void showInformationAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	/**
	 * Set title and content of Alert with WARNING content.
	 * @param title is a text that will be set as title of Alert.
	 * @param content is a text that will show as a warning content in Alert.
	 */
	public void showWarningAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
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
				title = "Warning Alert";
				header = "Room already exists.";
				content = "Sorry, room number " + field + " already exists.";
				showWarningAlert(title, header, content);
				return;
			}
		}
		
		switch (select) {
		case "Studio Room": typeId = 1;
			break;
		case "1-Bedroom": typeId = 2;
			break;
		case "2-Bedroom": typeId = 3;
			break;
		case "3-Bedroom": typeId = 4;
			break;
		default: break;
		}
		
		if(!field.isEmpty()) {
			RoomInfo addRoom = new RoomInfo(field, typeId);
			factory.insertDataToRooms(addRoom);
			view.run();
			
			title = "Information Alert";
			header = "Successfully added";
			content = "Room number "+ field +" has been added successfully.";
			showWarningAlert(title, header, content);
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
				title = "Warning Alert";
				header = "Room doesn't exist.";
				content = "Room number" + field + " doesn't exist. Please input an exists room number.";
				showWarningAlert(title, header, content);
				return;
			}
		}
		
		switch(select) {
		case "Studio Room": typeId = 1;
			break;
		case "1-Bedroom": typeId = 2;
			break;
		case "2-Bedroom": typeId = 3;
			break;
		case "3-Bedroom": typeId = 4;
			break;
		default: break;
		}
		
		if(!field.isEmpty()) {
			RoomInfo delRoom = new RoomInfo(field, typeId);
			factory.deleteDataFromRoom(delRoom);
			view.run();
			title = "Information Alert";
			header = "Successfully removed";
			content = "Room number "+ field +" has been removed successfully.";
			showWarningAlert(title, header, content);
		}	
	}
	
	/**
	 * Switch back to the AdminChoice scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/dataandpreferences/AdminChoicesUI.fxml");
		view.exit();
	}
	
}
