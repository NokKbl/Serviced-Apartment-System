package servicedapartment.checkin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.SwitchScene;

/**
 * Control action of the components in CustomerInfoUI.fxml file and pass some information to next scene.
 * @author Kunyaruk Katebunlu
 */
public class CustomerInfoController {
	@FXML private TextField name;
	@FXML private TextField phone;
	@FXML private TextField email;
	@FXML private TextField stay;
	@FXML private TextField amount;
	@FXML private DatePicker checkin;
	@FXML private DatePicker checkout;
	@FXML private Button next;
	@FXML private Button cancel;
	private SwitchScene newScene = new SwitchScene();
	private String units;
	
	/**
	 * Initialize components.
	 */
	public void initialize() {
		checkin.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate td = LocalDate.now();
				setDisable(empty || date.compareTo(td) < 0);
			}
	    });
		
		checkout.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate td = LocalDate.now();
				setDisable(empty || date.compareTo(td) < 0);
			}
	    });
	}
	
	/**
	 * Analyze the unit of time that the customer will stay then switch scene and pass the information to RoomandPayment scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleNext(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Alert");

		if(Integer.parseInt(amount.getText()) < 1) {
			alert.setHeaderText("Impossible!");
			alert.setContentText("The customer need to have at least one person.");
			alert.showAndWait();
		} else if(name.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty() || stay.getText().isEmpty() ||
				amount.getText().isEmpty() || checkin.getValue().equals(null) || checkout.getValue().equals(null)) {
			alert.setHeaderText("Incomplete required information");
			alert.setContentText("Please input ALL required information before click 'Next'.");
			alert.showAndWait();
		}else {
			if(Integer.parseInt(stay.getText()) >= 365) this.units = "years";
			else if (Integer.parseInt(stay.getText()) >= 30) this.units = "months";
			else if (Integer.parseInt(stay.getText()) >= 7) this.units = "weeks";
			else this.units = "days";
		
			CustomerInfo customerInfo = new CustomerInfo(name.getText(), phone.getText(), email.getText());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/servicedapartment/checkin/RoomandPaymentUI.fxml"));
			Parent view = (Parent) loader.load();
			Scene scene = new Scene(view);
		
			RoomandPaymentController controller = loader.getController();
			controller.initialize(customerInfo, Integer.parseInt(stay.getText()), Integer.parseInt(amount.getText()), checkin.getValue(), checkout.getValue(), this.units);
		
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		}
	}
	
	/**
	 * Calculate for days stay if input day out and calculate day out if input days stay.
	 */
	public void handleDaysorDateOut(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Alert");
		
		if(!stay.getText().equals("") && checkout.getValue() != null) { checkout.setValue(null); }
		else if(stay.getText().equals("") && checkout.getValue() == null) {
			alert.setHeaderText("Incomplete required information");
			alert.setContentText("Please input number of days OR date out.");
			alert.showAndWait();
		}
		
		if(!stay.getText().equals("")) {
			if(Integer.parseInt(stay.getText()) > 0) {
				checkout.setValue(checkin.getValue().plusDays(Integer.parseInt(stay.getText())));
			}else {
				alert.setHeaderText("Impossible!");
				alert.setContentText("Please input valid amount of day.");
				alert.showAndWait();
				stay.clear();
			}
		}
		else {
			if(ChronoUnit.DAYS.between(checkin.getValue(), checkout.getValue()) > 0) {
				stay.setText(String.valueOf(ChronoUnit.DAYS.between(checkin.getValue(), checkout.getValue())));
			} else {
				alert.setHeaderText("Impossible!");
				alert.setContentText("Please input valid date out.");
				alert.showAndWait();
			}
		}
	}
	
	/**
	 * Switch back to the Home scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/home/HomeUI.fxml");
	}
	
}
