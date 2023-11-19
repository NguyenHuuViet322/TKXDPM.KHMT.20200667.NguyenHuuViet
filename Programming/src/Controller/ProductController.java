package Controller;

import Model.Cart;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ProductController {
	
	@FXML
	private Label name;
	
	@FXML 
	private Label price;
	
	@FXML 
	private Label remain;
	
	@FXML
	private ComboBox combobox;
	
	@FXML
	private Button addToCart;
	
	@FXML 
	private ImageView img;
	
	public void setData(Product product) {
		String imgUrl = "/"+product.getID()+".jpg";
		Image image = new Image(getClass().getResourceAsStream(imgUrl));
		name.setText("  " + product.getName());
		price.setText(" "+String.valueOf(product.getPrice()));
		remain.setText(" "+String.valueOf(product.getRemain()));
		img.setImage(image);
		for(int i=1; i<=product.getRemain();i++) {
			combobox.getItems().add(i);
		}

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
        
        addToCart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				DBHandler handler = new DBHandler();
				if (combobox.getValue() == null) 
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Thông báo");
					alert.setHeaderText("Thông báo");
					alert.setContentText("Bạn chưa chọn số lượng");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {
					        System.out.println("Bấm OK.");
					    }
					});
				} else
				if (handler.addToCart(handler.getActiveCartByUserId(1).getID(), product.getID(), Integer.parseInt(combobox.getValue().toString()))) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Thông báo");
					alert.setHeaderText("Thông báo");
					alert.setContentText("Bạn đã thêm vào giỏ hàng thành công");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {
					        System.out.println("Bấm OK.");
					    }
					});
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Thông báo");
					alert.setHeaderText("Thông báo");
					alert.setContentText("Có lỗi xảy ra");
					alert.showAndWait().ifPresent(rs -> {
					    if (rs == ButtonType.OK) {
					        System.out.println("Bấm OK.");
					    }
					});
				}
			}
        	
        });
	}
}
