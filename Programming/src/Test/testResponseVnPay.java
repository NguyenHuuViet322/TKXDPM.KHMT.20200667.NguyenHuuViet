package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exception.InvalidDataErrorException;
import Model.Response;
class TestResponseVnPay {

	public TestResponseVnPay() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	void testResponseCode24() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=24&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=24&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Invalid Data");
		    }
	}

	@Test
	void testResponseCode51() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=51&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=51&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Not enough balance");
		    }
	}
	@Test
	void testResponseCode12() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=12&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=12&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Invalid Data");
		    }
	}
	@Test
	void testResponseCode23() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=13&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=13&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Invalid Data");
		    }
	}


	@Test
	void testResponseCode65() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=65&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=65&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Internal Server Error");
		    }
	}
	@Test
	void testResponseCode75() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=75&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=75&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Internal Server Error");
		    }
	}
	@Test
	void testResponseCode99() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=99&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=99&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Internal Server Error");
		    }
	}
	@Test
	void testResponseCode79() {
		Response response = new Response("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=79&vnp_TmnCode=4ZYDIQGL");
		response.HandleErrorCode("https://sandbox.vnpayment.vn/paymentv2/Ncb/Transaction/ketquatrave?vnp_Amount=106700000&vnp_BankCode=VNPAY&vnp_CardType=QRCODE&vnp_OrderInfo=Thanh+toan+hang+hoa&vnp_PayDate=20231119160410&vnp_ResponseCode=79&vnp_TmnCode=4ZYDIQGL");
		
		try {
			response.ProcessErrorCode();
		    } catch (Exception e) {	     
		      assertEquals(e.getMessage(), "Internal Server Error");
		    }
	}
}
