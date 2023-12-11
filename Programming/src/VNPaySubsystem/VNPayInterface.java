package VNPaySubsystem;

import Exception.InternalServerErrorException;
import Exception.InvalidDataErrorException;
import Exception.NotEnoughBalanceException;
import Model.Order;
import Model.PaymentTransaction;

public interface VNPayInterface {
	public abstract PaymentTransaction payOrder(Order order)
		      throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException;
	
	public abstract PaymentTransaction refund(Order order)
		      throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException;
}
