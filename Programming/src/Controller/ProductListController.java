package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Model.Product;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductListController implements Initializable {
	@FXML
	private GridPane productGrid;
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private Button cart;
	
	private DBHandler dbHandler;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dbHandler = new DBHandler();
		List<Product> productList = dbHandler.getAllProduct();
		int column = 0;
		int row = 1;
		
		try {
			for(int i = 0; i < productList.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/FXML/Product.fxml"));
				SplitPane box = fxmlLoader.load();
				ProductController productController = fxmlLoader.getController();
				productController.setData(productList.get(i));
				
				if (column == 3) {
					column = 0;
					row++;
				}
				productGrid.add(box, column, row);
				column++;
				productGrid.setMargin(box, new Insets(2));
			}
		} catch(IOException e) {
		}
		
		cart.setOnAction(new EventHandler<ActionEvent>() {
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
