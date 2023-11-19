package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Controller.PlaceOrderController;
import Model.Order;

class TestCalculatePlaceOrder{

	
	
	
	public TestCalculatePlaceOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Test
	void test1() {
        Order order = new Order(1,null,1000000001 ,null ,1, " ",4,5,"");
        order.setCity("Hà Nội");
        PlaceOrderController placeorderController = new PlaceOrderController();
        
        assertEquals(0,placeorderController.calculateShippingFee(order));

	}
	@Test	
	void test2() {
	Order order = new Order(1,null,10001 ,null ,1, " ",4,5,"");
    order.setCity("Hà Nội");
    PlaceOrderController placeorderController = new PlaceOrderController();
        
    assertEquals(78.5,placeorderController.calculateShippingFee(order));
		
	}


	@Test
	void test3() {
	Order order = new Order(1,null,10001 ,null ,2, " ",4,5,"");
    order.setCity("Hồ Chí Minh");
    PlaceOrderController placeorderController = new PlaceOrderController();
    
    assertEquals(102.5,placeorderController.calculateShippingFee(order));
	
}
	@Test
	void test4() {
	Order order = new Order(1,null,10001 ,null ,2, " ",4,5,"");
    order.setCity("Hồ Chí Minh");
    PlaceOrderController placeorderController = new PlaceOrderController();
    
    assertEquals(120.0,placeorderController.calculateShippingFee(order));
	
}
	@Test
	void test5() {
	Order order = new Order(1,null,10001 ,null ,2, " ",4,5,"");
    order.setCity("Hà Nội");
    PlaceOrderController placeorderController = new PlaceOrderController();
    
    assertEquals(88,placeorderController.calculateShippingFee(order));
	
}
}