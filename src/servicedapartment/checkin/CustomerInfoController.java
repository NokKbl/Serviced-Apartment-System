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
			alert.setHeaderText("Impossible amount!");
			alert.setContentText("The customer need to have at least one person.");
			alert.showAndWait();
		} else if(name.getText().trim().isEmpty() || phone.getText().trim().isEmpty() || email.getText().trim().isEmpty() || stay.getText().trim().isEmpty() ||
				amount.getText().trim().isEmpty() || checkin.getValue().equals(null) || checkout.getValue().equals(null)) {
			alert.setHeaderText("Incomplete required information");
			alert.setContentText("Please input ALL required information.");
			alert.showAndWait();
		} else if(!checkin.getValue().plusDays(Integer.parseInt(stay.getText())).isEqual(checkout.getValue()) || 
				ChronoUnit.DAYS.between(checkin.getValue(), checkout.getValue()) != Integer.parseInt(stay.getText())) {
			alert.setHeaderText("No. of days stay and checkout date are not match.");
			alert.setContentText("Please input No. of days stay or checkout date again.");
			alert.showAndWait();
			stay.clear();
			checkin.setValue(null);
			checkout.setValue(null);
		} else {
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
		
		if(!stay.getText().equals("") && checkout.getValue() != null) {
			alert.setHeaderText("Choose only one information to input.");
			alert.setContentText("Please input number of days stay OR date out.");
			alert.showAndWait();
		} else if(stay.getText().equals("") && checkout.getValue() == null) {
			alert.setHeaderText("Incomplete required information");
			alert.setContentText("Please input number of days stay or date out.");
			alert.showAndWait();
		}
		
		if(!stay.getText().equals("")) {
			if(Integer.parseInt(stay.getText()) > 0) {
				checkout.setValue(checkin.getValue().plusDays(Integer.parseInt(stay.getText())));
			}else {
				alert.setHeaderText("Impossible!");
				alert.setContentText("Please input possible amount of date.");
				alert.showAndWait();
				stay.clear();
			}
		}else {
			if(ChronoUnit.DAYS.between(checkin.getValue(), checkout.getValue()) > 0) {
				stay.setText(String.valueOf(ChronoUnit.DAYS.between(checkin.getValue(), checkout.getValue())));
			} else {
				alert.setHeaderText("Impossible!");
				alert.setContentText("Please input valid date out.");
				alert.showAndWait();
				checkout.setValue(null);
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
