package servicedapartment.dataandpreferences;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servicedapartment.SwitchScene;
import servicedapartment.data.RoomInfo;
import servicedapartment.data.TableRow;
import servicedapartment.database.DatabaseFactory;

public class RoomStatusController {
	
	@FXML
	Button backButton;
	@FXML
	Label dateLabel;
	@FXML
	TableView<TableRow> table;
	@FXML
	TableColumn<TableRow, String> roomNumber;
	@FXML
	TableColumn<TableRow, String> roomStatus;
	
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private LocalDate date;
	
	@FXML
	public void initialize() {
		date = LocalDate.now();
		dateLabel.setText(date.toString());
		
		roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
		table.setItems(getRoomData());
	}
	
	public ObservableList<TableRow> getRoomData(){
		ObservableList<TableRow> room = FXCollections.observableArrayList();
		List<RoomInfo> roomList = factory.readDataFromRoom();
		TableRow tableRow = null;
		String dIn = "";
		
		for(RoomInfo x : roomList) {
			String roomNumber = x.getRoomNumb();
			int id = factory.getRoomID(roomNumber);
			String status = factory.getRoomStatus(id);
			
			if(status.equals("Vacant") || status.equals("")) tableRow = new TableRow(roomNumber, "Vacant");
			else tableRow = new TableRow(roomNumber, "Occupied");
			
			room.add(tableRow);
		}
		
		return room;
	}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}

}
