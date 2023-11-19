package Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Controller.DBHandler;

public class Order {
	private int ID;
	private Date date;
	private double subtotal;
	private Date arrivalDate;
	private int userID;
	private String shippingInstruction;
	private int status;
	private int transactionId;
	private String city;
	private double shipping;
	private String address;
	private String note;
	private DBHandler dbHandler;

	public Order(int iD, Date date2, double subtotal, Date date3, int userID,
			String shippingInstruction, int status, int transactionId, String note) {
		super();
		dbHandler = new DBHandler();
		ID = iD;
		this.date = date2;
		this.subtotal = subtotal;
		this.arrivalDate = date3;
		this.userID = userID;
		this.shippingInstruction = shippingInstruction;
		this.status = status;
		this.transactionId = transactionId;
		this.note = note;
	}
	
	public Order(Date date2, double subtotal, int userID, int status) {
		super();
		this.date = date2;
		this.subtotal = subtotal;
		this.userID = userID;
		this.status = status;
	}

	public Order(Cart cart) {
		super();
		subtotal = cart.getSubtotal();
		userID = cart.getUserId();
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getShippingInstruction() {
		return shippingInstruction;
	}

	public void setShippingInstruction(String shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getShipping() {
		return shipping;
	}
	public void setShipping(double shipping) {
		this.shipping = shipping;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
