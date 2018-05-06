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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import servicedapartment.SwitchScene;
import servicedapartment.data.TypeInfo;
import servicedapartment.database.DatabaseFactory;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.DateOverlap;
import servicedapartment.data.OrderInfo;
import servicedapartment.data.PaymentInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.data.TableRow;
import servicedapartment.data.TransactionCash;

public class RoomandPaymentController {
	@FXML ComboBox<String> roomTypes;
	@FXML TableView<TableRow> table;
	@FXML TableColumn<TableRow, String> roomNumb;
	@FXML TableColumn<TableRow, String> roomStatus;
	@FXML Button next;
	@FXML Button cancel;
	@FXML TextField roomRate;
	@FXML TextField totall;
	@FXML TextField paid;
	@FXML TextField trId;
	@FXML Label trLb;
	@FXML ComboBox<String> paymentTypes;
	@FXML DatePicker dPaid;
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private List<RoomInfo> roomsI = factory.readDataFromRoom();
	private List<TypeInfo> typeI = factory.readDataFromRoomType();
	private List<OrderInfo> orderI = factory.readDataFromOrder();
	private CustomerInfo customerInfo;
	private RoomInfo roomInfo;
	private TypeInfo typeInfo;
	private PaymentInfo paymentInfo;
	private LocalDate checkin, checkout;
	private String unit;
	private int stay, amount, total;
	
	public void initialize(CustomerInfo customerInfo, int stay, int amount, LocalDate checkin, LocalDate checkout, String unit) {
		this.customerInfo = customerInfo;
		this.stay = stay;
		this.amount = amount;
		this.checkin = checkin;
		this.checkout = checkout;
		this.unit = unit;
		trId.setVisible(false);
		trLb.setVisible(false);
		
		String[] types = {"Studio", "1-Bedroom", "2-Bedroom", "3-Bedroom"};
		roomTypes.getItems().addAll(types);
		roomTypes.setPromptText(" Select Room Type");
		
		String[] pmTypes = {"Cash", "Credit Cards", "e-Payment"};
		paymentTypes.getItems().addAll(pmTypes);
		paymentTypes.setPromptText(" Select Payment Type ");
		
		List<RoomInfo> typeR = new ArrayList<>();
		for (RoomInfo roomInfo : roomsI) {
			if(roomInfo.getRoomNumb().startsWith("1")) typeR.add(roomInfo);
		}
		addToTableView(typeR);
	}
	
	public void addToTableView(List<RoomInfo> roomList) {
		roomNumb.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
		table.setItems(getRoomData(roomList));
		totall.setText(String.valueOf(getandCalTotal()));
	}
	
	public boolean checkOverlapTime(int roomId) {
		DateOverlap ovl = new DateOverlap();
		List<String[]> useForCompare = new ArrayList<>();
				List<String[]> allDIO = factory.getDayIO(roomId);
				for (String[] strings : allDIO) {
					if(ovl.checkIsBefore(checkin.toString()+"T00:00:00Z", checkout.toString()+"T23:59:00Z", strings[0]+"T00:00:00Z", strings[1]+"T23:59:00Z")) continue;
					else useForCompare.add(strings);
				}
				
				for (String[] strings : useForCompare) {
					if(ovl.checkOverlap(checkin.toString()+"T00:00:00Z", checkout.toString()+"T23:59:00Z", strings[0]+"T00:00:00Z", strings[1]+"T23:59:00Z")) {
						return true;
					}else return false;
				}
		return false;
	}
	
	public List<TableRow> createTR(List<RoomInfo> roomList){
		
		//what if time isn't overlap?????
		
		List<TableRow> tbR = new ArrayList<>();
		String status = null;
		for (RoomInfo roomInfo : roomList) {
			//System.out.println(roomInfo.getCustomerId());
			if(checkOverlapTime(factory.getRoomID(roomInfo.getRoomNumb())) == false || orderI.isEmpty()) {
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
		if(stay >= 365) calStay = stay/365.0;
		else if (stay >= 30) calStay = stay/30.0;
		else if (stay >= 7) calStay = stay/7.0;
		else calStay = stay;
		System.out.println(calStay);
		double total;
		if(stayUnit.equalsIgnoreCase("years")) total = calStay * rate * 12;
		else  total = calStay * rate;
		roomRate.setText(String.valueOf(rate));
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
			for (RoomInfo roomInfo : roomsI) { if(roomInfo.getRoomNumb().startsWith("1")) typeR.add(roomInfo); }
		} else if(roomTypes.getValue().equalsIgnoreCase("1-Bedroom")) {
			for (RoomInfo roomInfo : roomsI) { if(roomInfo.getRoomNumb().startsWith("2")) typeR.add(roomInfo); }
		} else if(roomTypes.getValue().equalsIgnoreCase("2-Bedroom")) {
			for (RoomInfo roomInfo : roomsI) { if(roomInfo.getRoomNumb().startsWith("3")) typeR.add(roomInfo); }
		} else {
			for (RoomInfo roomInfo : roomsI) { if(roomInfo.getRoomNumb().startsWith("4")) typeR.add(roomInfo); }
		}
		addToTableView(typeR);
	}
	
	public void handlePaymentType() {
		TransactionCash cashID = new TransactionCash();
		
		if(paymentTypes.getValue().equalsIgnoreCase("Cash")) {
			trId.setVisible(true);
			trId.setEditable(false);
			trId.setText("ORD-" + cashID.createTransactionCash());
			trLb.setText("Cash Order ID:");
			trLb.setVisible(true);
		} else {
			trId.setVisible(true);
			trId.clear();
			trId.setEditable(true);
			trLb.setText("Transaction ID:");
			trLb.setVisible(true);
		}
	}
	
	public void handleNext(ActionEvent event) throws IOException {
		TableRow roomI = table.getSelectionModel().getSelectedItem();
		
		if(roomI.getRoomSt().equalsIgnoreCase("Vacant")) {
			for (RoomInfo roomInfo : roomsI) {
				if(roomI.getRoomNb().equalsIgnoreCase(roomInfo.getRoomNumb())) this.roomInfo = roomInfo;
			}
			this.total = getandCalTotal();
			this.paymentInfo = new PaymentInfo(dPaid.getValue(), Integer.parseInt(paid.getText()), paymentTypes.getValue(), trId.getText());
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../checkin/CheckinSummaryUI.fxml"));
			Parent view = loader.load();
			Scene scene = new Scene(view);
		
			CheckinSummaryController controller = loader.getController();
			controller.initialize(typeInfo, roomInfo, getCustomerInfo(), paymentInfo, getUnit(), this.total, getStay(), getAmount(), getCheckin(), getCheckout());
		
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} else {
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
