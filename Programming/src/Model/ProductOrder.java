package Model;

public class ProductOrder {
	private int ID;
	private int ProductID;
	private int OrderID;
	private int Ammount;
	
	public ProductOrder(int iD, int productID, int orderID, int ammount) {
		super();
		ID = iD;
		ProductID = productID;
		OrderID = orderID;
		Ammount = ammount;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getAmmount() {
		return Ammount;
	}
	public void setAmmount(int ammount) {
		Ammount = ammount;
	}
	
	
}
