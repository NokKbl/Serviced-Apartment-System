package servicedapartment.checkin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import servicedapartment.SwitchScene;
import servicedapartment.data.TypeInfo;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.roomstate.Room;

public class RoomandPaymentController {
	@FXML ComboBox<String> roomTypes;
	@FXML TableView<Room> table;
	@FXML TableColumn<Room, String> roomNumb;
	@FXML TableColumn<Room, String> roomStatus;
	@FXML Label roomRates;
	@FXML RadioButton cash;
	@FXML RadioButton credit;
	@FXML Button next;
	@FXML Button cancel;
	private CustomerInfo customerInfo;
	private SwitchScene newScene = new SwitchScene();
	
	public void initialize(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
		String[] types = {"Studio", "1-Bedroom", "2-Bedroom", "3-Bedroom"};
		roomTypes.getItems().addAll(types);
		roomTypes.getSelectionModel().select(0);
		
//		if(basicInfo.getStayUnit().equalsIgnoreCase("days")) {
//			//ตาราง1 อ่านเลขห้องจาก adminlog
//			//ตาราง2 อ่านสถานะห้องจาก customerlog
//		} else if(basicInfo.getStayUnit().equalsIgnoreCase("weeks")) {
//			
//		} else {
////			if(basicInfo.getStayUnit().equalsIgnoreCase("months"))
////			else 
//			
//		}
	}
	
	public CustomerInfo getBasicInfo() {
		return this.customerInfo;
	}
	
	public int calculateTotal(int stay, String rate, String stayUnit) {
		int total = stay * Integer.parseInt(rate);
		if(stayUnit.equals("years")) total = total*12;
		return total;
	}
	
	public void handleType(ActionEvent event) {
		if(roomTypes.getValue().equalsIgnoreCase("Studio")) {
			//roomRates.setText("");
		} else if(roomTypes.getValue().equalsIgnoreCase("1-Bedroom")) {
			
		} else if(roomTypes.getValue().equalsIgnoreCase("2-Bedroom")) {
			
		} else {
			
		}
	}
	
	public void passInfo(ActionEvent event) throws IOException {
//		RoomInfo roomI = table.getSelectionModel().getSelectedItem();
//		
//		if(room.getState().toString().equalsIgnoreCase("Vacant")) {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(getClass().getResource("checkin/CheckinSummaryUI.fxml"));
//			Parent view = loader.load();
//			Scene scene = new Scene(view);
//		
//			CheckinSummaryController controller = loader.getController();
//			RoomInfo roomInfo = new RoomInfo(room.getRoomNo(), roomTypes.getValue(), room.getState(), roomRates.getText()));
//			controller.initialize(basicInfo, roomInfo, room);
//		
//			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//			window.setScene(scene);
//			window.show();
//		} else {
//			// Alert box
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Room Unavailable");
//			alert.setContentText("Room " + room.getRoomNo() + " is on 'Occupied' state. Please choose another room with 'Vacant' state.");
//			alert.showAndWait();
//		}
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
}
