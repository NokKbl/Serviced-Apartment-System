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
import servicedapartment.data.RoomInfo;
import servicedapartment.database.Database;

public class HistoryController {
	
	@FXML
	Button backButton;
	@FXML
	TableView<HistoryInfo> table;
	@FXML
	TableColumn<HistoryInfo, String> roomCol;
	@FXML
	TableColumn<HistoryInfo, String> nameCol;
	@FXML
	TableColumn<HistoryInfo, String> phoneCol;
	@FXML
	TableColumn<HistoryInfo, String> emailCol;
	@FXML
	TableColumn<HistoryInfo, Integer> stayCol;
	@FXML
	TableColumn<HistoryInfo, Integer> personCol;
	@FXML
	TableColumn<HistoryInfo, String> dInCol;
	@FXML
	TableColumn<HistoryInfo, String> dOutCol;
	
	private SwitchScene newScene = new SwitchScene();
	private Database factory = Database.getInstance();
	
	@FXML
	public void initialize() {
		addDataToTable();
	}
	
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
	
	public ObservableList<HistoryInfo> getData(){
		ObservableList<HistoryInfo> data = FXCollections.observableArrayList();
		List<OrderInfo> orderList = factory.readDataFromOrder();
		int rId = 0;
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
		CustomerInfo ctm;
		
		for(OrderInfo x : orderList) {
			rId = x.getRoomId();
			ctmId = x.getCustomerId();
			stay = x.getDaysStay();
			personNumber = x.getPeople();
			dIn = x.getDayIn().toString();
			dOut = x.getDayOut().toString();
			room = factory.getRoomNumber(rId);
			ctm = factory.getCustomerInfo(ctmId);
			customerName = ctm.getName();
			phoneNumber = ctm.getPhone();
			email = ctm.getEmail();
			
			history = new HistoryInfo(room, customerName, phoneNumber, email, stay, personNumber, dIn, dOut);
			
			data.add(history);
		}
		
		return data;
	}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}

}
