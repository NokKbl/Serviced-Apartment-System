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
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import servicedapartment.SwitchScene;
import servicedapartment.data.CustomerInfo;

/**
 * Control action of components in CustomerInfoUI.fxml file and 
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
	
	public void handleNext(ActionEvent event) throws IOException {
		if(Integer.parseInt(stay.getText()) >= 365) this.units = "years";
		else if (Integer.parseInt(stay.getText()) >= 30) this.units = "months";
		else if (Integer.parseInt(stay.getText()) >= 7) this.units = "weeks";
		else this.units = "days";
		
		CustomerInfo customerInfo = new CustomerInfo(name.getText(), phone.getText(), email.getText());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../checkin/RoomandPaymentUI.fxml"));
		Parent view = (Parent) loader.load();
		Scene scene = new Scene(view);
		
		RoomandPaymentController controller = loader.getController();
		controller.initialize(customerInfo, Integer.parseInt(stay.getText()), Integer.parseInt(amount.getText()), checkin.getValue(), checkout.getValue(), this.units);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	public void handleDaysorDateOut() {
		if(!stay.getText().equals("") && checkout.getValue() != null) { checkout.setValue(null); }
		else if(stay.getText().equals("") && checkout.getValue() == null) { checkout.setValue(LocalDate.now().plusDays(1)); }
		
		if(!stay.getText().equals("")) checkout.setValue(checkin.getValue().plusDays(Integer.parseInt(stay.getText())));
		else stay.setText(String.valueOf(ChronoUnit.DAYS.between(checkin.getValue(), checkout.getValue())));
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
}
