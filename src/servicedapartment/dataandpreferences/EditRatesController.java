package servicedapartment.dataandpreferences;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import servicedapartment.data.SwitchScene;
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
	private final int DAILYLENGTH = 4;
	private final int WEEKLYLENGTH = 5;
	private final int MONTHLYLENGTH = 6;
	
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
		String title = "";
		String header = "";
		String content = "";
		
		TypeInfo typeStd = null;
		TypeInfo typeOne = null;
		TypeInfo typeTwo = null;
		TypeInfo typeThree = null;
		
		int stdDailyLength = studioDaily.getLength();
		int stdWeeklyLength = studioWeekly.getLength();
		int stdMonthlyLength = studioMonthly.getLength();
		int onebrDailyLength = onebrDaily.getLength();
		int onebrWeeklyLength = onebrWeekly.getLength();
		int onebrMonthlyLength = onebrMonthly.getLength();
		int twobrDailyLength = twobrDaily.getLength();
		int twobrWeeklyLength = twobrWeekly.getLength();
		int twobrMonthlyLength = twobrMonthly.getLength();
		int threebrDailyLength = threebrDaily.getLength();
		int threebrWeeklyLength = threebrWeekly.getLength();
		int threebrMonthlyLength = threebrMonthly.getLength();
		
		if(stdDailyLength > DAILYLENGTH || onebrDailyLength > DAILYLENGTH || twobrDailyLength > DAILYLENGTH || threebrDailyLength > DAILYLENGTH) {
			title = "Warning Alert";
			header = "Invalid digits";
			content = "Please input only 1-4 digits.";
			showWarningAlert(title, header, content);
			return;
		}if(stdWeeklyLength > WEEKLYLENGTH || onebrWeeklyLength > WEEKLYLENGTH || twobrWeeklyLength > WEEKLYLENGTH || threebrWeeklyLength > WEEKLYLENGTH) {
			title = "Warning Alert";
			header = "Invalid digits";
			content = "Please input only 1-5 digits.";
			showWarningAlert(title, header, content);
			return;
		}if(stdMonthlyLength > MONTHLYLENGTH || onebrMonthlyLength > MONTHLYLENGTH || twobrMonthlyLength > MONTHLYLENGTH || threebrMonthlyLength > MONTHLYLENGTH) {
			title = "Warning Alert";
			header = "Invalid digits";
			content = "Please input only 1-6 digits.";
			showWarningAlert(title, header, content);
			return;
		}else {
		
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
			title = "Warning Alert";
			header = "Value type not match.";
			content = "Please input only number.";
			showWarningAlert(title, header, content);
		}
		
		
		factory.updateDataToTypes(typeStd);
		factory.updateDataToTypes(typeOne);
		factory.updateDataToTypes(typeTwo);
		factory.updateDataToTypes(typeThree);
		
		title = "Information Alert";
		header = "Update successful";
		content = "Rental rates has successfully updated.";
		showInformationAlert(title, header, content);
		
		}
	}
	
	/**
	 * Switch back to the AdminChoice scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/dataandpreferences/AdminChoicesUI.fxml");
	}


	/**
	 * Set title and content of Alert with WARNING content.
	 * @param title is a text that will be set as title of Alert.
	 * @param content is a text that will show as a warning content in Alert.
	 */
	public void showWarningAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	/**
	 * Set title and content of Alert with INFORMATION content.
	 * @param title is a text that will be set as title of Alert.
	 * @param content is a text that will show as an information content in Alert.
	 */
	public void showInformationAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
}
