package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Exception.InvalidDataErrorException;
import Model.Cart;
import Model.Order;
import Model.ProductOrder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RushOrderController implements Initializable {

    @FXML
    private GridPane CartItemList;

    @FXML
    private DatePicker arrivaltime;

    @FXML
    private Button back;

    @FXML
    private Button confirmorder;
    
    private DBHandler dbHandler;
    private Cart cart;
    private Order order;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dbHandler = new DBHandler();
		List<ProductOrder> list = new ArrayList<ProductOrder>();
		list = dbHandler.getProductOrderByIdCart(dbHandler.getActiveCartByUserId(1).getID());
		
		int row = 1;
		try {
			for(int i = 0; i < list.size(); i++) {
				if (dbHandler.getProductById(list.get(i).getID()).isCanFastOrder()) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/FXML/CartItem.fxml"));
					Pane box = fxmlLoader.load();
					CartItemController cartItemController = fxmlLoader.getController();
					cartItemController.setData2(dbHandler.getProductById(list.get(i).getID()), list.get(i).getAmmount());
					CartItemList.add(box, 0, row);
					row++;
					CartItemList.setMargin(box, new Insets(2));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		confirmorder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (validateRushOrderInfo()) {
					LocalDate localDate = arrivaltime.getValue();
					Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
					Date date = Date.from(instant);
					
					order.setStatus(2);
					order.setArrivalDate(new java.sql.Date(date.getTime()));
					order.setShipping(calculateShippingFee(order));
					
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
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Thông báo");
					alert.setHeaderText("Thông báo");
					alert.setContentText("Thông tin nhập vào không hợp lệ");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {
					        System.out.println("Bấm OK.");
					    }
					});
					throw new InvalidDataErrorException();
				}
				
			}
        });
		
	}
	
	public void init(Cart cart, Order order) {
		this.cart = cart;
		this.order = order;
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/Place Order.fxml"));
				try {
					Parent root = fxmlLoader.load();
					PlaceOrderController placeOrderController = fxmlLoader.getController();
					placeOrderController.init(cart);
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
	
	public boolean validateRushOrderInfo() {
		if (arrivaltime.getValue() == null) return false;
		LocalDate localDate = arrivaltime.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		
		Date dateNow = new Date();  
		
		if (date.compareTo(dateNow) < 0) return false;
		return true;
	}
	
	public double calculateShippingFee(Order order) {
		double price=0; 
		int weight = dbHandler.getActiveCartByUserId(1).getHighestWeight();
		int numFastOrder = dbHandler.getActiveCartByUserId(1).getNumFastOrder();
		if (cart.getSubtotal() >= 10000000) {
			price =0;
		} else {
			if (order.getCity().compareTo("Hà nội") == 0) {
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
