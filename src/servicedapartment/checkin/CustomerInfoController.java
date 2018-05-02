package servicedapartment.checkin;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import servicedapartment.SwitchScene;

public class CustomerInfoController {
	@FXML TextField name;
	@FXML TextField phone;
	@FXML TextField email;
	@FXML TextField stay;
	@FXML TextField amount;
	@FXML DatePicker checkin;
	@FXML DatePicker checkout;
	@FXML Button next;
	@FXML Button cancel;
	private SwitchScene newScene = new SwitchScene();
	private String units;
	
	public void handleNext(ActionEvent event) throws IOException {
		if(Integer.parseInt(stay.getText()) >= 365) this.units = "years";
		else if (Integer.parseInt(stay.getText()) >= 30) this.units = "months";
		else if (Integer.parseInt(stay.getText()) >= 7) this.units = "weeks";
		else this.units = "days";
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("checkin/RoomandPaymentUI.fxml"));
		Parent view = loader.load();
		Scene scene = new Scene(view);
		
		RoomandPaymentController controller = loader.getController();
//		TypeInfo basicInfo = new TypeInfo(name.getText(), phone.getText(), email.getText(), Integer.parseInt(stay.getText()),
//										units, Integer.parseInt(amount.getText()), checkin.getValue(), checkout.getValue());
//		controller.initialize(basicInfo);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	public void handleCheckin() {
		if(!stay.getText().equals("")) checkout.setValue(checkin.getValue().plusDays(Integer.parseInt(stay.getText())));
		else stay.setText(String.valueOf(ChronoUnit.DAYS.between(checkin.getValue(), checkout.getValue())));
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
}
