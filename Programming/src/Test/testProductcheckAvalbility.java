package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Model.Product;
class TestProductcheckAvalbility {

	public TestProductcheckAvalbility() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	void test1() {
		Product product = new Product(1,"test1", 100,10,10,true);
		boolean test = product.checkAvalability(5);
		assertEquals(true,test);
	}
	@Test
	void test2() {
		Product product = new Product(1,"test1", 100,10,10,true);
		boolean test = product.checkAvalability(500);
		assertEquals(false,test);
	}

}
