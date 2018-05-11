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

/**
 * Control the components action in RoomandPaymentUI.fxml file, calculate the total payment of an order,
 *  checking overlap time for the rooms, receive and pass values.
 * @author Kunyaruk Katebunlu
 */
public class RoomandPaymentController {
	@FXML private ComboBox<String> roomTypes;
	@FXML private TableView<TableRow> table;
	@FXML private TableColumn<TableRow, String> roomNumb;
	@FXML private TableColumn<TableRow, String> roomStatus;
	@FXML private Button next;
	@FXML private Button cancel;
	@FXML private TextField roomRate;
	@FXML private TextField totall;
	@FXML private TextField paid;
	@FXML private TextField trId;
	@FXML private Label trLb;
	@FXML private ComboBox<String> paymentTypes;
	@FXML private DatePicker dPaid;
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

	/**
	 * Initialize components and values.
	 * @param customerInfo is the information about the customer.
	 * @param stay is a number of days that the customer will stay.
	 * @param amount is a number of the customer.
	 * @param checkin is a check-in date.
	 * @param checkout is a checkout date.
	 * @param unit is the unit of time that the customer will stay (days/weeks/months/years).
	 */
	public void initialize(CustomerInfo customerInfo, int stay, int amount, LocalDate checkin, LocalDate checkout, String unit) {
		this.customerInfo = customerInfo;
		this.stay = stay;
		this.amount = amount;
		this.checkin = checkin;
		this.checkout = checkout;
		this.unit = unit;
		trId.setVisible(false);
		trLb.setVisible(false);

		String[] types = { "Studio", "1-Bedroom", "2-Bedroom", "3-Bedroom" };
		roomTypes.getItems().addAll(types);

		String[] pmTypes = { "Cash", "Credit Cards", "e-Payment" };
		paymentTypes.getItems().addAll(pmTypes);

		List<RoomInfo> typeR = new ArrayList<>();
		for (RoomInfo roomInfo : roomsI) {
			if (roomInfo.getRoomNumb().startsWith("1")) typeR.add(roomInfo);
		}
		addToTableView(typeR);
	}

	/**
	 * Add the data from a list of the rooms into TableView.
	 * @param roomList is a list of rooms that will be add into table.
	 */
	public void addToTableView(List<RoomInfo> roomList) {
		roomNumb.setCellValueFactory(new PropertyValueFactory<>("roomNb"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomSt"));
		table.setItems(getRoomData(roomList));
		totall.setText(String.valueOf(getandCalTotal()));
	}

	/**
	 * Check that the time is overlap or not to know the state of the room.
	 * @param roomId is an primary key id of the room that want to check.
	 * @return true if the time is overlap, false if it's not.
	 */
	public boolean checkOverlapTime(int roomId) {
		DateOverlap ovl = new DateOverlap();
		List<String[]> useForCompare = new ArrayList<>();
		List<String[]> allDIO = factory.getDayIO(roomId);
		
		for (String[] strings : allDIO) {
			if (ovl.checkIsBefore(checkin.toString() + "T00:00:00Z", checkout.toString() + "T23:59:00Z",
					strings[0] + "T00:00:00Z", strings[1] + "T23:59:00Z")) continue;
			else useForCompare.add(strings);
		}

		for (String[] strings : useForCompare) {
			if (ovl.checkOverlap(checkin.toString() + "T00:00:00Z", checkout.toString() + "T23:59:00Z",
					strings[0] + "T00:00:00Z", strings[1] + "T23:59:00Z") && strings[2].equalsIgnoreCase("Occupied")) { return true; }
			else return false;
		}
		return false;
	}

	/**
	 * Create a list of data that will be show in the TableView.
	 * @param roomList is a list of rooms that will be add into table.
	 * @return a list of data that will be show in the TableView.
	 */
	public List<TableRow> createTR(List<RoomInfo> roomList) {
		List<TableRow> tbR = new ArrayList<>();
		String status = null;

		for (RoomInfo roomInfo : roomList) {
			if (checkOverlapTime(factory.getRoomID(roomInfo.getRoomNumb())) == false || orderI.isEmpty()) {
				status = "Vacant";
				TableRow tb = new TableRow(roomInfo.getRoomNumb(), status);
				tbR.add(tb);
			} else {
				status = "Occupied";
				TableRow tb = new TableRow(roomInfo.getRoomNumb(), status);
				tbR.add(tb);
			}
		}
		return tbR;
	}

	/**
	 * Create and add a list of data into ObservableList which is a list that allows listeners to track changes when they occur. 
	 * @param roomList is a list of rooms that will be add into table.
	 * @return an ObservableList of data that will be show in TableView. 
	 */
	public ObservableList<TableRow> getRoomData(List<RoomInfo> roomList) {
		ObservableList<TableRow> room = FXCollections.observableArrayList();
		room.addAll(createTR(roomList));
		return room;
	}

	/**
	 * Calculate the total payment for this order.
	 * @param stay is a number of days that the customer will stay.
	 * @param rate is a rate of the room that the customer has been chosen.
	 * @param stayUnit is a unit of time that the customer will stay (days/weeks/months/years).
	 * @return total payment that customer should pay.
	 */
	public int calculateTotal(int stay, int rate, String stayUnit) {
		double calStay, total;
		
		if (stay >= 365) calStay = stay / 365.0;
		else if (stay >= 30) calStay = stay / 30.0;
		else if (stay >= 7) calStay = stay / 7.0;
		else calStay = stay;
		
		if (stayUnit.equalsIgnoreCase("years")) total = calStay * rate * 12;
		else total = calStay * rate;
		
		roomRate.setText(String.valueOf(rate));
		return (int) total;
	}

	/**
	 * Get a room rate and calculate the total payment.
	 * @return total payment that customer should pay.
	 */
	public int getandCalTotal() {
		int result = 0;
		for (TypeInfo typeInfo : typeI) {
			if (typeInfo.getRoomType().equalsIgnoreCase(roomTypes.getValue())) {
				this.typeInfo = typeInfo;
				if (unit.equalsIgnoreCase("days")) result = calculateTotal(stay, typeInfo.getpDays(), unit);
				else if (unit.equalsIgnoreCase("weeks")) result = calculateTotal(stay, typeInfo.getpWeeks(), unit);
				else result = calculateTotal(stay, typeInfo.getpMonths(), unit);
			}
		}
		return result;
	}

	/**
	 * Filter each room type and add those room's information into the TableView.
	 */
	public void handleRoomType(ActionEvent event) {
		List<RoomInfo> typeR = new ArrayList<>();

		if (roomTypes.getValue().equalsIgnoreCase("Studio")) {
			for (RoomInfo roomInfo : roomsI) {
				if (roomInfo.getRoomNumb().startsWith("1")) typeR.add(roomInfo);
			}
		} else if (roomTypes.getValue().equalsIgnoreCase("1-Bedroom")) {
			for (RoomInfo roomInfo : roomsI) {
				if (roomInfo.getRoomNumb().startsWith("2")) typeR.add(roomInfo);
			}
		} else if (roomTypes.getValue().equalsIgnoreCase("2-Bedroom")) {
			for (RoomInfo roomInfo : roomsI) {
				if (roomInfo.getRoomNumb().startsWith("3")) typeR.add(roomInfo);
			}
		} else {
			for (RoomInfo roomInfo : roomsI) {
				if (roomInfo.getRoomNumb().startsWith("4")) typeR.add(roomInfo);
			}
		}
		
		addToTableView(typeR);
	}

	/**
	 * Get payment type, set Label and TextField visible and availability.
	 */
	public void handlePaymentType(ActionEvent event) {
		TransactionCash cashID = new TransactionCash();

		if (paymentTypes.getValue().equalsIgnoreCase("Cash")) {
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

	/**
	 * Get data from TableView, check room state then switch to CheckinSummary scene and pass some values to the next scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleNext(ActionEvent event) throws IOException {
		try {
			TableRow roomI = table.getSelectionModel().getSelectedItem();

			if (roomI.getRoomSt().equalsIgnoreCase("Vacant")) {
				for (RoomInfo roomInfo : roomsI) {
					if (roomI.getRoomNb().equalsIgnoreCase(roomInfo.getRoomNumb()))
						this.roomInfo = roomInfo;
				}
				this.total = getandCalTotal();
				this.paymentInfo = new PaymentInfo(dPaid.getValue(), Integer.parseInt(paid.getText()),
						paymentTypes.getValue(), trId.getText());

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../checkin/CheckinSummaryUI.fxml"));
				Parent view = loader.load();
				Scene scene = new Scene(view);

				CheckinSummaryController controller = loader.getController();
				controller.initialize(typeInfo, roomInfo, customerInfo, paymentInfo, unit, this.total,
						stay, amount, checkin, checkout);

				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.show();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Room Unavailable");
				alert.setContentText("Room " + roomI.getRoomNb()
						+ " is on 'Occupied' state. Please choose another room with 'Vacant' state.");
				alert.showAndWait();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Switch back to the Home scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}

}
