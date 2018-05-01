package servicedapartment;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SwitchScene {
	
	public void switchScene(ActionEvent event, String resourseFile) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource(resourseFile));
		Scene scene = new Scene(view);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
	}
	
}
