package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Order;
import VNPaySubsystem.VNPaySubsystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InvoiceController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField city;

    @FXML
    private Button confirmorder;

    @FXML
    private TextField name;

    @FXML
    private TextArea note;

    @FXML
    private TextField phone;

    @FXML
    private Label ship;

    @FXML
    private Label subtotal;

    @FXML
    private Label sum;
    
    @FXML
    private Button back;
    
    private Order order;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setText("Nguyen Huu Viet");
		phone.setText("0896124269");
	}
	
	public void init(Order order) {
		this.order = order;
		subtotal.setText(String.valueOf(order.getSubtotal()));
		ship.setText(String.valueOf(order.getShipping()));
		address.setText(order.getAddress());
		sum.setText(String.valueOf(order.getShipping() + order.getSubtotal()));
		city.setText(order.getCity());
		note.setText(order.getNote());
		
		confirmorder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/VNPay.fxml"));
				try {
					Parent root = fxmlLoader.load();
					VNPaySubsystemController vNPayViewController = fxmlLoader.getController();
					vNPayViewController.init(order);
					Stage stage = (Stage)confirmorder.getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}    	
        });
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/Cart.fxml"));
				try {
					Parent root = fxmlLoader.load();
					CartController cartController = fxmlLoader.getController();
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

}
