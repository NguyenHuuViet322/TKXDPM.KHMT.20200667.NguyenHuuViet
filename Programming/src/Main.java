import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage homeStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
		homeStage.setTitle("Home");
		homeStage.setScene(new Scene(root));
		homeStage.show();
	}

	public static void main(String[] args) {
	    launch(args);
	  }
}
