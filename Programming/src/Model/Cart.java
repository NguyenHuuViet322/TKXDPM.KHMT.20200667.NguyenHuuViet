package Model;

import java.util.List;

import Controller.DBHandler;

public class Cart {
	private int ID;
	private int userId;
	private boolean status;
	private List<ProductOrder> list;
	private double subtotal;
	
	public Cart(int iD, int userId, boolean status) {
		super();
		ID = iD;
		this.userId = userId;
		this.status = status;
	}
	
	public void InitList() {
		DBHandler dbHandler = new DBHandler();
		list = dbHandler.getProductOrderByIdCart(ID);
	}
	
	public int getHighestWeight() {
		InitList();
		DBHandler dbHandler = new DBHandler();
		int max=0; int ammount=0;
		for(int i=0; i < list.size(); i++) {
			if (dbHandler.getProductById(list.get(i).getID()).getWeight() > max) {
				max = dbHandler.getProductById(list.get(i).getID()).getWeight();
				ammount = list.get(i).getAmmount();
			}
		}
		return max*ammount;
	}
	
	public int getNumFastOrder() {
		DBHandler dbHandler = new DBHandler();
		InitList();
		int num=0;
		for(int i=0; i < list.size(); i++) {
			if (dbHandler.getProductById(list.get(i).getID()).isCanFastOrder()) {
				num++;
			}
		}
		return num;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		userId = userId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public boolean checkProductAvailability() {
		DBHandler dbHandler = new DBHandler();
		List<ProductOrder> productList = dbHandler.getProductOrderByIdCart(ID);
		for(int i = 0; i < productList.size(); i++) {
			Product tmp = dbHandler.getProductById(productList.get(i).getID());
			if (!tmp.checkAvalability(productList.get(i).getAmmount())) {
				return false;
			}
		}
		return true;
	}
}
