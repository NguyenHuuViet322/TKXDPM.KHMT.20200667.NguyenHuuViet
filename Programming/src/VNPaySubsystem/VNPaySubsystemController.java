package VNPaySubsystem;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import Controller.ResultController;
import Exception.InternalServerErrorException;
import Exception.InvalidDataErrorException;
import Exception.NotEnoughBalanceException;
import Model.Order;
import Model.PaymentTransaction;
import Model.Response;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class VNPaySubsystemController implements Initializable, VNPayInterface{
	@FXML
	private WebView vnpay;
	private WebEngine engine;
	private Order order;
	private PaymentTransaction paymentTransaction;
	private VNPayBoundary vnPayBoundary = new VNPayBoundary();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		engine = vnpay.getEngine();
	}
	
	public void init(Order order) throws UnknownHostException {
		this.order = order;
		payOrder(order);
	}
	
	public void displayErrorView(String message) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/FXML/Result.fxml"));
		try {
			Parent root = fxmlLoader.load();
			ResultController resultController = fxmlLoader.getController();
			resultController.init(message, "red");
			Stage stage = (Stage)vnpay.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void displayResultView(String message) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/FXML/Result.fxml"));
		try {
			Parent root = fxmlLoader.load();
			ResultController resultController = fxmlLoader.getController();
			resultController.init(message, "Hoàn thành");
			resultController.finalizeOrder(order);
			Stage stage = (Stage)vnpay.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Override
	public PaymentTransaction payOrder(Order order)
			throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException {
		try {
			engine.load(vnPayBoundary.payOrderBuildUrl(order));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		engine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
			if(newState == Worker.State.SUCCEEDED) {
				System.out.println(engine.getLocation());
				if (engine.getLocation().contains("ketquatrave")) {
					Response response = new Response(engine.getLocation());
					vnpay.setVisible(false);
					System.out.println("Response caught!");
					response.HandleErrorCode(response.getResponseString());
					try {
						paymentTransaction = response.ProcessErrorCode();
						if (paymentTransaction.getResponseCode() == 0) {
							displayResultView("Giao dịch thành công");
						}
					} catch(Exception e) {
						e.printStackTrace();
						displayErrorView(e.getMessage());
					}
				}
			}
		});
		
		return paymentTransaction;
	}

	@Override
	public PaymentTransaction refund(Order order)
			throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException {
		// TODO Auto-generated method stub
		return null;
	}
}
