package servicedapartment.dataandpreferences;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	TextField onebrrDaily;
	@FXML
	TextField onebrrWeekly;
	@FXML
	TextField onebrrMonthly;
	@FXML
	TextField twobrrDaily;
	@FXML
	TextField twobrrWeekly;
	@FXML
	TextField twobrrMonthly;
	@FXML
	TextField threebrrDaily;
	@FXML
	TextField threebrrWeekly;
	@FXML
	TextField threebrrMonthly;
	
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	
	@FXML
	public void initialize() {
		List<TypeInfo> list = factory.readDataFromRoomType();
		
		studioDaily.setText("" + list.get(0).getpDays());
		studioWeekly.setText("" + list.get(0).getpWeeks());
		studioMonthly.setText("" + list.get(0).getpMonths());
		
		onebrrDaily.setText("" + list.get(1).getpDays());
		onebrrWeekly.setText("" + list.get(1).getpWeeks());
		onebrrMonthly.setText("" + list.get(1).getpMonths());
		
		twobrrDaily.setText("" + list.get(2).getpDays());
		twobrrWeekly.setText("" + list.get(2).getpWeeks());
		twobrrMonthly.setText("" + list.get(2).getpMonths());
		
		threebrrDaily.setText("" + list.get(3).getpDays());
		threebrrWeekly.setText("" + list.get(3).getpWeeks());
		threebrrMonthly.setText("" + list.get(3).getpMonths());
	}
	
	public void handleSave(ActionEvent event) {}
	
	public void handleBack(ActionEvent event) throws IOException {
		newScene.switchScene(event, "dataandpreferences/AdminChoicesUI.fxml");
	}

}
