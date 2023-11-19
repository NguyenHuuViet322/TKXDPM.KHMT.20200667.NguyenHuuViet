package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Controller.RushOrderController;
import Model.Order;

class TestcalculateShippingFeeTest {

	
	public TestcalculateShippingFeeTest() {
		super();
	}
	@Test
	void test1() {
        Order order = new Order(1,null,1000000001 ,null ,1, " ",4,5,"");
        
        RushOrderController rushorderController = new RushOrderController();
        
        assertEquals(0,rushorderController.calculateShippingFee(order));

	}
	@Test
	void test2() {
Order order = new Order(1,null,10001 ,null ,1, " ",4,5,"");
        order.setCity("Hà Nội");
        RushOrderController rushorderController = new RushOrderController();
        
        assertEquals(78.5,rushorderController.calculateShippingFee(order));
		
	}


	@Test
void test3() {
	Order order = new Order(1,null,10001 ,null ,2, " ",4,5,"");
    order.setCity("Hồ Chí Minh");
    RushOrderController rushorderController = new RushOrderController();
    
    assertEquals(102.5,rushorderController.calculateShippingFee(order));
	
}
	@Test
void test4() {
	Order order = new Order(1,null,10001 ,null ,2, " ",4,5,"");
    order.setCity("Hồ Chí Minh");
    RushOrderController rushorderController = new RushOrderController();
    
    assertEquals(120.0,rushorderController.calculateShippingFee(order));
	
}
	@Test
void test5() {
	Order order = new Order(1,null,10001 ,null ,2, " ",4,5,"");
    order.setCity("Hà Nội");
    RushOrderController rushorderController = new RushOrderController();
    
    assertEquals(88,rushorderController.calculateShippingFee(order));
	
}
}