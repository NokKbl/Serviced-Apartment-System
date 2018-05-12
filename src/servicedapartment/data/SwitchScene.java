package servicedapartment.data;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * SwitchScene class is a class that use for switch scene from one scene to another scene.
 * @author Kunyaruk Katebunlu
 */
public class SwitchScene {
	
	/**
	 * Switch the scene from one to another by put the fxml file's location.
	 * @param resourseFile is a String which tells a location of an fxml file that will switch to.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void switchScene(ActionEvent event, String resourseFile) throws IOException {
		Parent view = FXMLLoader.load(getClass().getResource(resourseFile));
		Scene scene = new Scene(view);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
	}
	
}
