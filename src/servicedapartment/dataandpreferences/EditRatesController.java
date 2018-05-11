package servicedapartment.dataandpreferences;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import servicedapartment.SwitchScene;
import servicedapartment.data.TypeInfo;
import servicedapartment.database.DatabaseFactory;

/**
 * Control the components in EditRatesUI.fxml file and update new room rates into the database.
 * @author Thanaphon Keawjam
 */
public class EditRatesController {
	@FXML private Button saveButton;
	@FXML private Button backButton;
	@FXML private TextField studioDaily;
	@FXML private TextField studioWeekly;
	@FXML private TextField studioMonthly;
	@FXML private TextField onebrDaily;
	@FXML private TextField onebrWeekly;
	@FXML private TextField onebrMonthly;
	@FXML private TextField twobrDaily;
	@FXML private TextField twobrWeekly;
	@FXML private TextField twobrMonthly;
	@FXML private TextField threebrDaily;
	@FXML private TextField threebrWeekly;
	@FXML private TextField threebrMonthly;
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private List<TypeInfo> list;
	
	/**
	 * Initialize components.
	 */
	@FXML
	public void initialize() {
		list = factory.readDataFromRoomType();
		
		studioDaily.setText("" + list.get(0).getpDays());
		studioWeekly.setText("" + list.get(0).getpWeeks());
		studioMonthly.setText("" + list.get(0).getpMonths());
		
		onebrDaily.setText("" + list.get(1).getpDays());
		onebrWeekly.setText("" + list.get(1).getpWeeks());
		onebrMonthly.setText("" + list.get(1).getpMonths());
		
		twobrDaily.setText("" + list.get(2).getpDays());
		twobrWeekly.setText("" + list.get(2).getpWeeks());
		twobrMonthly.setText("" + list.get(2).getpMonths());
		
		threebrDaily.setText("" + list.get(3).getpDays());
		threebrWeekly.setText("" + list.get(3).getpWeeks());
		threebrMonthly.setText("" + list.get(3).getpMonths());
		
		list.clear();
	}
	
	/**
	 * Get new rates from TextField and update the rates into the database.
	 */
	public void handleSave(ActionEvent event) {
		TypeInfo typeStd = null;
		TypeInfo typeOne = null;
		TypeInfo typeTwo = null;
		TypeInfo typeThree = null;
		
		try {
			typeStd = new TypeInfo("Studio", 
							Integer.parseInt(studioDaily.getText()), 
							Integer.parseInt(studioWeekly.getText()), 
							Integer.parseInt(studioMonthly.getText()));
		
			typeOne = new TypeInfo("1-Bedroom", 
							Integer.parseInt(onebrDaily.getText()), 
							Integer.parseInt(onebrWeekly.getText()), 
							Integer.parseInt(onebrMonthly.getText()));
		
			typeTwo = new TypeInfo("2-Bedroom", 
							Integer.parseInt(twobrDaily.getText()), 
							Integer.parseInt(twobrWeekly.getText()), 
							Integer.parseInt(twobrMonthly.getText()));
		
			typeThree = new TypeInfo("3-Bedroom", 
							Integer.parseInt(threebrDaily.getText()), 
							Integer.parseInt(threebrWeekly.getText()), 
							Integer.parseInt(threebrMonthly.getText()));
		} catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Alert");
			alert.setHeaderText("Value type not match.");
			alert.setContentText("Please input only number.");
			alert.showAndWait();
		}
		
		factory.updateDataToTypes(typeStd);
		factory.updateDataToTypes(typeOne);
		factory.updateDataToTypes(typeTwo);
		factory.updateDataToTypes(typeThree);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Alert");
		alert.setHeaderText("Update successful");
		alert.setContentText("Rental rates has successfully updated.");
		alert.showAndWait();
	}
	
	/**
	 * Switch back to the AdminChoice scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}

}
