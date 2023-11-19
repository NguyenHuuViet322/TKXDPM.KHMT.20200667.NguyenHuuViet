package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Exception.InvalidDataErrorException;
import Model.Cart;
import Model.Order;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PlaceOrderController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private ComboBox city;


    @FXML
    private TextArea note;
    
    @FXML
    private RadioButton fastOrder;

    @FXML
    private RadioButton normalOrder;
    
    @FXML
    private Button confirmorder;
    
    @FXML
    private Button back;
    
    private Order order;
    private Cart cart;
    private DBHandler dbHandler;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dbHandler = new DBHandler();
		ToggleGroup group = new ToggleGroup();
		fastOrder.setToggleGroup(group);
		normalOrder.setToggleGroup(group);
		normalOrder.setSelected(true);
		order = new Order(dbHandler.getActiveCartByUserId(1));
		city.getItems().add("Hà Nội");
		city.getItems().add("Hải Phòng");
		city.getItems().add("Nghệ An");
		city.getItems().add("Quảng Ninh");
		city.getItems().add("Nam Định");
		
		confirmorder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				processingDeliveryInfo();
			}
        });
	}
	
	public void init(Cart cart) {
		this.cart = cart;
		
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
	
	public void processingDeliveryInfo() {
		if (validateDeliveryInfo()) {
			long millis = System.currentTimeMillis();
	        java.sql.Date date = new java.sql.Date(millis);
	        
			order.setShippingInstruction(note.getText());
			order.setDate(date);
			order.setStatus(1);
			order.setCity(city.getValue().toString());
			order.setShipping(calculateShippingFee(order));
			order.setSubtotal(cart.getSubtotal());
			order.setAddress(address.getText());
			order.setNote(note.getText().toString());
			
			if (fastOrder.isSelected()) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/RushOrderForm.fxml"));
				try {
					Parent root = fxmlLoader.load();
					RushOrderController rushOrderController = fxmlLoader.getController();
					rushOrderController.init(cart, order);
					Stage stage = (Stage)confirmorder.getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/Invoice.fxml"));
				try {
					Parent root = fxmlLoader.load();
					InvoiceController invoiceController = fxmlLoader.getController();
					invoiceController.init(order);
					Stage stage = (Stage)confirmorder.getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Thông báo");
			alert.setContentText("Thông tin nhập vào không hợp lệ. Vui lòng kiểm tra lại thông tin. (Các tỉnh thành ngoài Hà Nội không hỗ trợ Fast Order)");
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			        System.out.println("Bấm OK.");
			    }
			});
			throw new InvalidDataErrorException();
		}
	}
	
	public boolean validateDeliveryInfo() {
		if (address.getText().length()>30) return false;
		if (address.getText()==null) return false;
		if (note.getText() != null && note.getText().toString().length() > 50) return false;
		if (city.getValue()==null) return false;
		if (city.getValue().toString().compareTo("Hà Nội") == 0 && fastOrder.isSelected()) return false;
		return true;
	}
	
	public double calculateShippingFee(Order order) {
		double price=0; 
		int weight = dbHandler.getActiveCartByUserId(1).getHighestWeight();
		int numFastOrder = dbHandler.getActiveCartByUserId(1).getNumFastOrder();
		if (cart.getSubtotal() >= 10000000) {
			price =0;
		} else {
			if (city.getValue().toString() == "Hà nội") {
				if (weight <= 3) price = weight*22;
				else price = (66+(weight-3)/0.5*2.5);
			} else {
				if (weight <= 0.5) price = weight*30;
				else price = (90+(weight-0.5)/0.5*2.5);
			}
		}
		if (order.getStatus() == 2) {
			price += numFastOrder*10;
		}
		return price;
	}
}
