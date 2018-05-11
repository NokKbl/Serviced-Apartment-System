package servicedapartment.dataandpreferences;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servicedapartment.SwitchScene;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.HistoryInfo;
import servicedapartment.data.OrderInfo;
import servicedapartment.database.DatabaseFactory;

/**
 * Control the components in HistoryUI.fxml file, read the history of orders and show in a table.
 * @author Thanaphon Keawjam
 */
public class HistoryController {
	@FXML private Button backButton;
	@FXML private TableView<HistoryInfo> table;
	@FXML private TableColumn<HistoryInfo, String> roomCol;
	@FXML private TableColumn<HistoryInfo, String> nameCol;
	@FXML private TableColumn<HistoryInfo, String> phoneCol;
	@FXML private TableColumn<HistoryInfo, String> emailCol;
	@FXML private TableColumn<HistoryInfo, Integer> stayCol;
	@FXML private TableColumn<HistoryInfo, Integer> personCol;
	@FXML private TableColumn<HistoryInfo, String> dInCol;
	@FXML private TableColumn<HistoryInfo, String> dOutCol;
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	
	/**
	 * Initialize components.
	 */
	@FXML
	public void initialize() {
		addDataToTable();
	}
	
	/**
	 * Add the history of customer order into TableView.
	 */
	public void addDataToTable() {
		roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		stayCol.setCellValueFactory(new PropertyValueFactory<>("stay"));
		personCol.setCellValueFactory(new PropertyValueFactory<>("personNumber"));
		dInCol.setCellValueFactory(new PropertyValueFactory<>("dIn"));
		dOutCol.setCellValueFactory(new PropertyValueFactory<>("dOut"));
		table.setItems(getData());
	}
	
	/**
	 * Create and add data into ObservableList which is a list that allows listeners to track changes when they occur.
	 * @return an ObservableList of data that will be show in TableView. 
	 */
	public ObservableList<HistoryInfo> getData(){
		ObservableList<HistoryInfo> data = FXCollections.observableArrayList();
		List<OrderInfo> orderList = factory.readDataFromOrder();
		int roomId = 0;
		int ctmId = 0;
		int stay = 0;
		int personNumber = 0;
		String dIn = "";
		String dOut = "";
		String room = "";
		String customerName = "";
		String phoneNumber = "";
		String email = "";
		HistoryInfo history;
		CustomerInfo ctmInfo;
		
		for(OrderInfo odInfo : orderList) {
			roomId = odInfo.getRoomId();
			ctmId = odInfo.getCustomerId();
			stay = odInfo.getDaysStay();
			personNumber = odInfo.getPeople();
			dIn = odInfo.getDayIn().toString();
			dOut = odInfo.getDayOut().toString();
			room = factory.getRoomNumber(roomId);
			ctmInfo = factory.getCustomerInfo(ctmId);
			customerName = ctmInfo.getName();
			phoneNumber = ctmInfo.getPhone();
			email = ctmInfo.getEmail();
			history = new HistoryInfo(room, customerName, phoneNumber, email, stay, personNumber, dIn, dOut);
			data.add(history);
		}
		return data;
	}
	
	/**
	 * Switch back to the AdminChoice scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}

}
