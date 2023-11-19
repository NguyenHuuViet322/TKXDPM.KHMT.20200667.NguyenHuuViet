package Model;

public class Product {
	private int ID;
	private String name;
	private double price;
	private int remain;
	private int weight;
	private boolean canFastOrder;
	private int ammount;
	
	public Product(int iD, String name, double price, int remain, int weight, boolean canFastOrder) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
		this.remain = remain;
		this.weight = weight;
		this.canFastOrder = canFastOrder;
	}
	
	public boolean checkAvalability(int ammount) {
		if (ammount > remain) return false;
		else return true;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isCanFastOrder() {
		return canFastOrder;
	}

	public void setCanFastOrder(boolean canFastOrder) {
		this.canFastOrder = canFastOrder;
	}
	
}
