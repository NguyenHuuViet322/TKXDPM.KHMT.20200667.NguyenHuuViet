package Model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import Controller.ResultController;
import Exception.InternalServerErrorException;
import Exception.InvalidDataErrorException;
import Exception.NotEnoughBalanceException;
import VNPaySubsystem.VNPaySubsystemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Response {
	private String responseString;
	Map<String, String> responseValue;

	public Response(String responseString) {
		super();
		this.responseString = responseString;
		responseValue = new LinkedHashMap<String, String>();
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	
	
	
	public void HandleErrorCode(String responseString) {
		String tmp = responseString.substring(responseString.indexOf("ketquatrave?")+12);
		
		for(String keyValue : tmp.split("&")) {
		   String[] pairs = keyValue.split("=",2);
		   responseValue.put(pairs[0], pairs[1]);
		}
		System.out.print(responseValue.toString());
	}
	
	public PaymentTransaction ProcessErrorCode() {
		if (responseValue.get("vnp_ResponseCode").compareTo("24") == 0) {
			throw new InvalidDataErrorException();
		}
		else
		if (responseValue.get("vnp_ResponseCode").compareTo("51") == 0) {
			throw new NotEnoughBalanceException();
		}
		else
		if (responseValue.get("vnp_ResponseCode").compareTo("12") == 0) {
			throw new InvalidDataErrorException();
		}
		else
		if (responseValue.get("vnp_ResponseCode").compareTo("13") == 0) {
			throw new InvalidDataErrorException();
		}
		else
		if (responseValue.get("vnp_ResponseCode").compareTo("65") == 0) {
			throw new InternalServerErrorException();
		}
		else
		if (responseValue.get("vnp_ResponseCode").compareTo("75") == 0) {
			throw new InternalServerErrorException();
		}else
		if (responseValue.get("vnp_ResponseCode").compareTo("99") == 0) {
			throw new InternalServerErrorException();
		}else
		if (responseValue.get("vnp_ResponseCode").compareTo("79") == 0) {
			throw new InternalServerErrorException();
		}
		
		return new PaymentTransaction(Integer.parseInt(responseValue.get("vnp_TransactionNo")), responseValue.get("vnp_BankCode"),responseValue.get("vnp_TransactionNo"),1,
				responseValue.get("vnp_PayDate"),responseValue.get("vnp_OrderInfo"),Integer.parseInt(responseValue.get("vnp_ResponseCode")),
				Integer.parseInt(responseValue.get("vnp_TransactionStatus")),responseValue.get("vnp_TxnRef"),responseValue.get("vnp_SecureHash"),
				responseValue.get("vnp_SecureHash"));
		
		
	}
}
