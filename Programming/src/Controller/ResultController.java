package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultController implements Initializable {

    @FXML
    private Button home;

    @FXML
    private Text resultText;
    
    private Order order;
    private DBHandler dbHandler;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/Home.fxml"));
				try {
					Parent root = fxmlLoader.load();
					ProductListController productListController = fxmlLoader.getController();
					Stage stage = (Stage)((Node)arg0.getSource()).getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		
	}
	
	public void init(String message, String type) {
		resultText.setText(message);
		if (type.compareTo("red") == 0) {
			resultText.setFill(new Color(0.2941, 0.7098, 0.2627, 1.0));
		}
	}
	
	public void finalizeOrder(Order order) {
		this.order = order;
		dbHandler = new DBHandler();
		order.setTransactionId(1);
		order.setUserID(1);
		order.setStatus(2);
		dbHandler.emptyCart();
		//dbHandler.createOrder(order);
	}

}
