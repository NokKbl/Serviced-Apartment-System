package servicedapartment.dataandpreferences;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import servicedapartment.SwitchScene;
import servicedapartment.data.TypeInfo;
import servicedapartment.database.DatabaseFactory;

public class EditRatesController {
	
	@FXML
	Button saveButton;
	@FXML
	Button backButton;
	@FXML
	TextField studioDaily;
	@FXML
	TextField studioWeekly;
	@FXML
	TextField studioMonthly;
	@FXML
	TextField onebrDaily;
	@FXML
	TextField onebrWeekly;
	@FXML
	TextField onebrMonthly;
	@FXML
	TextField twobrDaily;
	@FXML
	TextField twobrWeekly;
	@FXML
	TextField twobrMonthly;
	@FXML
	TextField threebrDaily;
	@FXML
	TextField threebrWeekly;
	@FXML
	TextField threebrMonthly;
	
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private List<TypeInfo> list;
	
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
	
	public void handleSave(ActionEvent event) {
		TypeInfo typeStd = new TypeInfo("Studio", 
							Integer.parseInt(studioDaily.getText()), 
							Integer.parseInt(studioWeekly.getText()), 
							Integer.parseInt(studioMonthly.getText()));
		
		TypeInfo typeOne = new TypeInfo("1-Bedroom", 
							Integer.parseInt(onebrDaily.getText()), 
							Integer.parseInt(onebrWeekly.getText()), 
							Integer.parseInt(onebrMonthly.getText()));
		
		TypeInfo typeTwo = new TypeInfo("2-Bedroom", 
							Integer.parseInt(twobrDaily.getText()), 
							Integer.parseInt(twobrWeekly.getText()), 
							Integer.parseInt(twobrMonthly.getText()));
		
		TypeInfo typeThree = new TypeInfo("3-Bedroom", 
							Integer.parseInt(threebrDaily.getText()), 
							Integer.parseInt(threebrWeekly.getText()), 
							Integer.parseInt(threebrMonthly.getText()));
		
		factory.updateDataToTypes(typeStd);
		factory.updateDataToTypes(typeOne);
		factory.updateDataToTypes(typeTwo);
		factory.updateDataToTypes(typeThree);
	}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}

}
