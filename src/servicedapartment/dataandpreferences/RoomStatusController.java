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
import servicedapartment.data.RoomInfo;
import servicedapartment.data.StatusTableRow;
import servicedapartment.data.SwitchScene;
import servicedapartment.database.DatabaseFactory;

/**
 * Control the components in RoomStatusUI.fxml file and use to show room status in each day.
 * @author Thanaphon Keawjam
 */
public class RoomStatusController {
	@FXML private Button backButton;
	@FXML private Label dateLabel;
	@FXML private TableView<StatusTableRow> table;
	@FXML private TableColumn<StatusTableRow, String> roomNumber;
	@FXML private TableColumn<StatusTableRow, String> roomStatus;
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private LocalDate date;
	
	/**
	 * Initialize components.
	 */
	@FXML
	public void initialize() {
		date = LocalDate.now();
		dateLabel.setText(date.toString());
		roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
		table.setItems(getRoomData());
	}
	
	/**
	 * Create and add data into ObservableList which is a list that allows listeners to track changes when they occur.
	 * @return an ObservableList of data that will be show in TableView.
	 */
	public ObservableList<StatusTableRow> getRoomData(){
		ObservableList<StatusTableRow> room = FXCollections.observableArrayList();
		List<RoomInfo> roomList = factory.readDataFromRoom();
		StatusTableRow tableRow = null;
		
		for(RoomInfo x : roomList) {
			String roomNumber = x.getRoomNumb();
			int id = factory.getRoomID(roomNumber);
			String status = factory.getRoomStatus(id);
			
			if(status.equals("Vacant") || status.equals("")) tableRow = new StatusTableRow(roomNumber, "Vacant");
			else tableRow = new StatusTableRow(roomNumber, "Occupied");
			
			room.add(tableRow);
		}
		return room;
	}
	
	/**
	 * Switch back to the AdminChoice scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/dataandpreferences/AdminChoicesUI.fxml");
	}

}
