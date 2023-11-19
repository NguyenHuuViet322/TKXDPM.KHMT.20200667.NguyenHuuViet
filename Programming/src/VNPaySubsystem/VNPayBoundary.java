package VNPaySubsystem;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import Model.Card;
import Model.DebitCard;
import Model.Order;
import Model.PaymentTransaction;
import Model.Request;

public class VNPayBoundary {
	public String payOrder(Order order) throws UnknownHostException {
		Request request = new Request(order);
		
        return request.buildQueryURL();
	}
	
}
