package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import Model.Cart;
import Model.Order;
import Model.Product;
import Model.ProductOrder;

public class DBHandler {
	private Connection conn;
	private Statement stmt;
	
	private static String DB_URL = "jdbc:sqlite:db/AIMS.db";
    
	public DBHandler() {
		super();
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DB_URL);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createOrder(Order order) {
		try {
			stmt.executeQuery("INSERT INTO ORDER ('DATE', 'SUBTOTAL', 'ARRIVALDATE', 'USERID', 'SHIPPINGINTRUCTION', 'STATUS', 'TransactionID') VALUES ("
					+ ""+order.getDate()+","+order.getSubtotal()+","+order.getArrivalDate()+","+order.getUserID()+","+order.getShippingInstruction()+","+order.getStatus()+","+order.getTransactionId()+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createCart(Cart cart) {
		try {
			stmt.executeUpdate("INSERT INTO Cart ('USERID', 'STATUS') VALUES ('"
					+ cart.getUserId()+"','"+cart.isStatus()+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public List<Product> getAllProduct() {
    	List<Product> tmp = new ArrayList<Product>();
    	
    	try {
			ResultSet rs = stmt.executeQuery("select * from Product");
			while(rs.next()) {
				tmp.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getInt(4),rs.getInt(5), rs.getInt(6)==1?true:false));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return tmp;
    }
    
    public Product getProductById(int id) {
    	try {
			ResultSet rs = stmt.executeQuery("select * from Product where id = " + id);
			if(rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getInt(4),rs.getInt(5), rs.getInt(6)==1?true:false);
			}
			else 
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public Order getActiveOrderByUserId(int userId) {
    	Order order = null;
    	try {
			ResultSet rs = stmt.executeQuery("select * from Order where userId = " + userId + " AND status = 1");
			if(rs.next()) {
				Order tmp  = new Order(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getDate(4), rs.getInt(5), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
				order = tmp;
			} else {
				long millis = System.currentTimeMillis();
		        java.sql.Date date = new java.sql.Date(millis);
				createOrder(new Order(date, 0, 1, 1));
				rs = stmt.executeQuery("select * from Order where userId = " + userId + " AND status = 1");
				if(rs.next()) {
					Order tmp  = new Order(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getDate(4), rs.getInt(5), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
					order = tmp;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;	
    }
    
    public Cart getActiveCartByUserId(int userId) {
    	Cart order = null;
    	try {
			ResultSet rs = stmt.executeQuery("select * from 'Cart' where userid = " + userId + " AND status = 'true'");
			if(rs.next()) {
				Cart tmp  = new Cart(rs.getInt(1), rs.getInt(2), rs.getBoolean(3));
				order = tmp;
			} else {
				long millis = System.currentTimeMillis();
		        java.sql.Date date = new java.sql.Date(millis);
				createCart(new Cart(0 ,1, true));
				rs = stmt.executeQuery("select * from Cart where userId = " + userId + " AND status = true");
				if(rs.next()) {
					Cart tmp  = new Cart(rs.getInt(1), rs.getInt(2), rs.getBoolean(3));
					order = tmp;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;	
    }
    
    public boolean addToCart(int idCart, int idProduct, int ammount) {
    	try {
			stmt.executeUpdate("INSERT INTO PRODUCTORDER ('ProductId', 'CartId', 'Ammount') VALUES ("
					+ ""+idProduct+","+idCart+","+ammount+")");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    }
    
    public boolean removeFromCart(int idCart, int idProduct) {
    	try {
			stmt.executeUpdate("UPDATE ProductOrder SET CartID = 0 AND OrderID=0 WHERE CartID = "
					+ ""+idCart+" AND ProductID = "+ idProduct +"");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    	
    
    
    public void emptyCart() {
    	Cart cart = getActiveCartByUserId(1);
    	try {
			stmt.executeUpdate("UPDATE Cart SET status = 0 WHERE ID =" + cart.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<ProductOrder> getProductOrderByIdCart(int idCart) {
    	List<ProductOrder> tmp = new ArrayList<ProductOrder>();
    	
    	try {
			ResultSet rs = stmt.executeQuery("select * from ProductOrder where cartId = " + idCart);
			while(rs.next()) {
				tmp.add(new ProductOrder(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return tmp;
    }
    
}
