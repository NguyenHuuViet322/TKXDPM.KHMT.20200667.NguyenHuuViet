package Controller;

import Model.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartItemController {

    @FXML
    private Label ammount;

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label remain;

    @FXML
    private Button removeFromCart;
    
    private CartController cartController;
    
    public void setData(Product product, int ammountNum, CartController cartController) {
    	name.setText("  " + product.getName());
    	price.setText("  " + String.valueOf(product.getPrice()));
    	remain.setText("  " + String.valueOf(product.getRemain()));
    	this.ammount.setText("  " + String.valueOf(ammountNum));
    	String imgUrl = "/"+product.getID()+".jpg";
		Image image = new Image(getClass().getResourceAsStream(imgUrl));
		img.setImage(image);
		double w = 0;
        double h = 0;

        double ratioX = img.getFitWidth() / image.getWidth();
        double ratioY = img.getFitHeight() / image.getHeight();

        double reducCoeff = 0;
        if(ratioX >= ratioY) {
            reducCoeff = ratioY;
        } else {
            reducCoeff = ratioX;
        }

        w = image.getWidth() * reducCoeff;
        h = image.getHeight() * reducCoeff;

        img.setX((img.getFitWidth() - w) / 2);
        img.setY((img.getFitHeight() - h) / 2);
        this.cartController = cartController;
        removeFromCart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				cartController.removeFromCart(product.getID());
			}
        	
        });
    }
    
    public void setData2(Product product, int ammountNum) {
    	name.setText("  " + product.getName());
    	price.setText("  " + String.valueOf(product.getPrice()));
    	remain.setText("  " + String.valueOf(product.getRemain()));
    	removeFromCart.setVisible(false);
    	this.ammount.setText("  " + String.valueOf(ammountNum));
    	String imgUrl = "/"+product.getID()+".jpg";
		Image image = new Image(getClass().getResourceAsStream(imgUrl));
		img.setImage(image);
		double w = 0;
        double h = 0;

        double ratioX = img.getFitWidth() / image.getWidth();
        double ratioY = img.getFitHeight() / image.getHeight();

        double reducCoeff = 0;
        if(ratioX >= ratioY) {
            reducCoeff = ratioY;
        } else {
            reducCoeff = ratioX;
        }

        w = image.getWidth() * reducCoeff;
        h = image.getHeight() * reducCoeff;

        img.setX((img.getFitWidth() - w) / 2);
        img.setY((img.getFitHeight() - h) / 2);
        
    }

}
