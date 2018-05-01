package servicedapartment.checkin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import servicedapartment.SwitchScene;
import servicedapartment.customer.BasicInfo;
import servicedapartment.customer.RoomInfo;

public class RoomandPaymentController {
	@FXML ComboBox<String> roomTypes;
	@FXML TableView<RoomInfo> table;
	@FXML TableColumn<RoomInfo, String> roomNumb;
	@FXML TableColumn<RoomInfo, String> roomStatus;
	@FXML Label roomRates;
	@FXML RadioButton cash;
	@FXML RadioButton credit;
	@FXML Button next;
	@FXML Button cancel;
	private BasicInfo basicInfo;
	private SwitchScene newScene = new SwitchScene();
	
	public void initialize(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
		String[] types = {"Studio", "1-Bedroom", "2-Bedroom", "3-Bedroom"};
		roomTypes.getItems().addAll(types);
		roomTypes.getSelectionModel().select(0);
		
		if(basicInfo.getStayUnit().equalsIgnoreCase("days")) {
			//ตาราง1 อ่านเลขห้องจาก adminlog
			//ตาราง2 อ่านสถานะห้องจาก customerlog
		}
		else if(basicInfo.getStayUnit().equalsIgnoreCase("weeks")) {
			
		}
		else {
			
		}
	}
	
	public BasicInfo getBasicInfo() {
		return this.basicInfo;
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
		RoomInfo input = table.getSelectionModel().getSelectedItem(); //เอาข้อมูลจาก table
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("checkin/CheckinSummaryUI.fxml"));
		Parent view = loader.load();
		Scene scene = new Scene(view);
		
		CheckinSummaryController controller = loader.getController();
		RoomInfo roomInfo = new RoomInfo(input.getRoomNumb(), roomTypes.getValue(), input.getStatus(), input.getRoomRates(),
									calculateTotal(getBasicInfo().getStay(), input.getRoomRates(), getBasicInfo().getStayUnit()));
		controller.initialize(basicInfo, roomInfo);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
}
