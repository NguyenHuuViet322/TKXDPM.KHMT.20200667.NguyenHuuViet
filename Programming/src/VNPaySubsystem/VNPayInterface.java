package VNPaySubsystem;

import Exception.InternalServerErrorException;
import Exception.InvalidDataErrorException;
import Exception.NotEnoughBalanceException;
import Model.Card;
import Model.CreditCard;
import Model.PaymentTransaction;

public interface VNPayInterface {
	public abstract PaymentTransaction payOrder(Card card, double amount, String contents)
		      throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException;
	
	public abstract PaymentTransaction refund(Card card, double amount, String contents)
		      throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException;
	
	public abstract PaymentTransaction getBalance(Card card)
		      throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException;
	
}
