package servicedapartment.checkin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import servicedapartment.SwitchScene;
import servicedapartment.data.TypeInfo;
import servicedapartment.database.DatabaseFactory;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.data.TableRow;
import servicedapartment.roomstate.Room;

public class RoomandPaymentController {
	@FXML ComboBox<String> roomTypes;
	@FXML TableView<TableRow> table;
	@FXML TableColumn<TableRow, String> roomNumb;
	@FXML TableColumn<TableRow, String> roomStatus;
	@FXML Label roomRates;
	@FXML RadioButton cash;
	@FXML RadioButton credit;
	@FXML Button next;
	@FXML Button cancel;
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private CustomerInfo customerInfo;
	private RoomInfo roomInfo;
	private TypeInfo typeInfo;
	private int stay, amount, total;
	private LocalDate checkin, checkout;
	private String unit;
	private List<RoomInfo> baseInfo;// = factory.readDataFromRoom();
	private List<TypeInfo> typeI;// = factory.readDataFromRoomType();
	
	public void initialize(CustomerInfo customerInfo, int stay, int amount, LocalDate checkin, LocalDate checkout, String unit) {
		this.customerInfo = customerInfo;
		this.stay = stay;
		this.amount = amount;
		this.checkin = checkin;
		this.checkout = checkout;
		this.unit = unit;
		this.baseInfo = factory.readDataFromRoom();
		this.typeI = factory.readDataFromRoomType();
		
		String[] types = {"Studio", "1-Bedroom", "2-Bedroom", "3-Bedroom"};
		roomTypes.getItems().addAll(types);
		roomTypes.getSelectionModel().select(0);
		
		List<RoomInfo> typeR = new ArrayList<>();
		for (RoomInfo roomInfo : baseInfo) {
			if(roomInfo.getRoomNumb().startsWith("1")) typeR.add(roomInfo);
		}
		roomNumb.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
		table.setItems(getRoomData(typeR));
	}
	
	public List<TableRow> createTR(List<RoomInfo> roomList){
		List<TableRow> tbR = new ArrayList<>();
		String status = null;
		for (RoomInfo roomInfo : roomList) {
			//System.out.println(roomInfo.getCustomerId());
			if(roomInfo.getCustomerId() == 0) {
				status = "Vacant";
				TableRow tb = new TableRow(roomInfo.getRoomNumb(), status);
				tbR.add(tb);
				System.out.println(tb.getRoomSt());
			} else {
				status = "Occupied";
				TableRow tb = new TableRow(roomInfo.getRoomNumb(), status);
				tbR.add(tb);
				System.out.println(tb.getRoomSt());
			}
			
		}
		return tbR;
	}
	
	public ObservableList<TableRow> getRoomData(List<RoomInfo> roomList){
		ObservableList<TableRow> room = FXCollections.observableArrayList();
		room.addAll(createTR(roomList));
		return room;
	}

	public int calculateTotal(int stay, int rate, String stayUnit) {
		double calStay;
		if(stay >= 365) calStay = stay/365;
		else if (stay >= 30) calStay = stay/30;
		else if (stay >= 7) calStay = stay/7;
		else calStay = stay;
		System.out.println(calStay);
		double total;
		if(stayUnit.equalsIgnoreCase("years")) total = calStay * rate * 12;
		else  total = calStay * rate;
		return (int) total;
	}
	
	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}
	
	public int getStay() {
		return stay;
	}

	public int getAmount() {
		return amount;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public String getUnit() {
		return unit;
	}
	
	public int getandCalTotal() {
		int result = 0;
		for (TypeInfo typeInfo : typeI) {
			if(typeInfo.getRoomType().equalsIgnoreCase(roomTypes.getValue())) {
				this.typeInfo = typeInfo;
				if(getUnit().equalsIgnoreCase("days")) result = calculateTotal(getStay(), typeInfo.getpDays(), getUnit());
				else if(getUnit().equalsIgnoreCase("weeks")) result = calculateTotal(getStay(), typeInfo.getpWeeks(), getUnit());
				else result = calculateTotal(getStay(), typeInfo.getpMonths(), getUnit());
			}
		}
		return result;
	}
	
	public void handleType(ActionEvent event) {
		List<RoomInfo> typeR = new ArrayList<>();
		
		if(roomTypes.getValue().equalsIgnoreCase("Studio")) {
			for (RoomInfo roomInfo : baseInfo) {
				if(roomInfo.getRoomNumb().startsWith("1")) typeR.add(roomInfo);
				//System.out.println(roomInfo.getRoomNumb());
			}
			roomNumb.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
			roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
			table.setItems(getRoomData(typeR));
		} else if(roomTypes.getValue().equalsIgnoreCase("1-Bedroom")) {
			for (RoomInfo roomInfo : baseInfo) {
				if(roomInfo.getRoomNumb().startsWith("2")) typeR.add(roomInfo);
				//System.out.println(roomInfo.getRoomNumb());
			}
			roomNumb.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
			roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
			table.setItems(getRoomData(typeR));
		} else if(roomTypes.getValue().equalsIgnoreCase("2-Bedroom")) {
			for (RoomInfo roomInfo : baseInfo) {
				if(roomInfo.getRoomNumb().startsWith("3")) typeR.add(roomInfo);
				//System.out.println(roomInfo.getRoomNumb());
			}
			roomNumb.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
			roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
			table.setItems(getRoomData(typeR));
		} else {
			for (RoomInfo roomInfo : baseInfo) {
				if(roomInfo.getRoomNumb().startsWith("4")) typeR.add(roomInfo);
				//System.out.println(roomInfo.getRoomNumb());
			}
			roomNumb.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
			roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
			table.setItems(getRoomData(typeR));
		}
	}
	
	public void handleNext(ActionEvent event) throws IOException {
		TableRow roomI = table.getSelectionModel().getSelectedItem();
		
		if(roomI.getRoomSt().equalsIgnoreCase("Vacant")) {
			for (RoomInfo roomInfo : baseInfo) {
				if(roomI.getRoomNb().equalsIgnoreCase(roomInfo.getRoomNumb())) this.roomInfo = roomInfo;
			}
			this.total = getandCalTotal();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../checkin/CheckinSummaryUI.fxml"));
			Parent view = loader.load();
			Scene scene = new Scene(view);
		
			CheckinSummaryController controller = loader.getController();
			controller.initialize(typeInfo, roomInfo, getCustomerInfo(), getUnit(), this.total, getStay(), getAmount(), getCheckin(), getCheckout());
		
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} else {
			// Alert box
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Room Unavailable");
			alert.setContentText("Room " + roomI.getRoomNb() + " is on 'Occupied' state. Please choose another room with 'Vacant' state.");
			alert.showAndWait();
		}
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
}
