package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import Model.Cart;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CartController implements Initializable {

	@FXML
	private GridPane CartItemList;

	@FXML
	private Button back;

	@FXML
	private Button placeorder;

	@FXML
	private Label price;

	@FXML
	private Label sum;

	@FXML
	private Label vat;
	
	private DBHandler dbHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dbHandler = new DBHandler();
		List<ProductOrder> list = new ArrayList<ProductOrder>();
		list = dbHandler.getProductOrderByIdCart(dbHandler.getActiveCartByUserId(1).getID());
		double totalprice = 0;
		double totalvat;
		
		int row = 1;
		try {
			for(int i = 0; i < list.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/CartItem.fxml"));
				Pane box = fxmlLoader.load();
				CartItemController cartItemController = fxmlLoader.getController();
				cartItemController.setData(dbHandler.getProductById(list.get(i).getID()), list.get(i).getAmmount(),this);
				CartItemList.add(box, 0, row);
				row++;
				CartItemList.setMargin(box, new Insets(2));
				totalprice += dbHandler.getProductById(list.get(i).getID()).getPrice()*list.get(i).getAmmount();
			}
			
			totalvat = totalprice*0.1;
			price.setText(String.valueOf(totalprice));
			vat.setText(String.valueOf(totalvat));
			sum.setText(String.valueOf(totalprice + totalvat));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/Home.fxml"));
				try {
					Parent root = fxmlLoader.load();
					ProductListController productListController = fxmlLoader.getController();
					Stage stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		placeorder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				placeOrder(dbHandler.getActiveCartByUserId(1));
			}
		});
		
		
	}
	
	public void placeOrder(Cart cart) {
		if (cart.checkProductAvailability()) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/FXML/Place Order.fxml"));
			try {
				Parent root = fxmlLoader.load();
				PlaceOrderController placeOrderController = fxmlLoader.getController();
				cart.setSubtotal(Double.parseDouble(sum.getText()));
				placeOrderController.init(cart);
				Stage stage = (Stage)placeorder.getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else 
		{
			CartItemList.getChildren().clear();
			displayCartWithProductAvailability();
		}
	}
	
	public void displayCartWithProductAvailability() {
		List<ProductOrder> list = new ArrayList<ProductOrder>();
		list = dbHandler.getProductOrderByIdCart(dbHandler.getActiveCartByUserId(1).getID());
		double totalprice = 0;
		double totalvat;
		
		int row = 1;
		try {
			for(int i = 0; i < list.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/CartItem.fxml"));
				Pane box = fxmlLoader.load();
				CartItemController cartItemController = fxmlLoader.getController();
				cartItemController.setData(dbHandler.getProductById(list.get(i).getID()), list.get(i).getAmmount(), this);
				if (!dbHandler.getProductById(list.get(i).getID()).checkAvalability(list.get(i).getAmmount())) {
					box.setStyle("-fx-background-color: #F08080");
				}  else {
					box.setStyle("-fx-background-color: #ADD8E6");
				}
				CartItemList.add(box, 0, row);
				row++;
				CartItemList.setMargin(box, new Insets(2));
				totalprice += dbHandler.getProductById(list.get(i).getID()).getPrice()*list.get(i).getAmmount();
				
			}
			
			totalvat = totalprice*0.1;
			price.setText(String.valueOf(totalprice));
			vat.setText(String.valueOf(totalvat));
			sum.setText(String.valueOf(totalprice + totalvat));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateView() {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/FXML/Cart.fxml"));
		try {
			Parent root = fxmlLoader.load();
			CartController cartController = fxmlLoader.getController();
			Stage stage = (Stage)placeorder.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeFromCart(int productID) {
		if (dbHandler.removeFromCart(dbHandler.getActiveCartByUserId(1).getID(), productID)) 
			updateView();
	}

}
