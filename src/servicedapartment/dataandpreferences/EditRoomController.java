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
import servicedapartment.database.Database;

public class EditRoomController {
	
	@FXML
	Button addButton;
	@FXML
	Button removeButton;
	@FXML
	Button backButton;
	@FXML
	ComboBox<String> roomBox;
	@FXML
	TextField textField;
	
	private Database factory = Database.getInstance();
	private SwitchScene newScene = new SwitchScene();
	private Room room = new Room();
	private String select;
	private String field;
	private RoomView view;
	private String title;
	private String content;
	
	@FXML
	public void initialize() {
		roomBox.getItems().addAll("Studio Room", "1-Bed Room", "2-Bed Room", "3-Bed Room");
		roomBox.setStyle("-fx-font: 18px \"Comic Sans MS\";");
		roomBox.getSelectionModel().select(0);
		
		view = new RoomView(room);
		room.addObserver(view);
		
		view.run();
	}

	public void handleAdd(ActionEvent event) {
		int typeId = 0;
		List<RoomInfo> data = factory.readDataFromRoom();
		select = roomBox.getValue();
		field = textField.getText().trim();
		
		for(RoomInfo x : data) {
			if(field.equals(x.getRoomNumb())) {
				title = "Warning";
				content = "This room number is already exists";
				showAlert(title, content);
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
			content = "Add this room number already";
			showAlert(title, content);
		}
	}
	
	public void handleRemove(ActionEvent event) {
		int typeId = 0;
		select = roomBox.getValue();
		field = textField.getText().trim();
		List<RoomInfo> data = new ArrayList<>();
		
		for(RoomInfo x : data) {
			if(!field.equals(x.getRoomNumb())) {
				title = "Warning";
				content = "No this room number";
				showAlert(title, content);
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
			content = "Remove this room number already";
			showAlert(title, content);
		}
		
	}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
		view.exit();
	}
	
	public void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		
		alert.showAndWait();
	}
}
