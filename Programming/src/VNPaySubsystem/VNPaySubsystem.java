package VNPaySubsystem;

import Exception.InternalServerErrorException;
import Exception.InvalidDataErrorException;
import Exception.NotEnoughBalanceException;
import Model.Card;
import Model.CreditCard;
import Model.DebitCard;
import Model.PaymentTransaction;

public class VNPaySubsystem implements VNPayInterface {
	private VNPaySubsystemController controller;
	
	

	public VNPaySubsystem() {
		super();
		controller = new VNPaySubsystemController();
	}

	@Override
	public PaymentTransaction payOrder(Card card, double amount, String contents)
			throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException {
		// TODO Auto-generated method stub
		return controller.payOrder(card, (int)amount, contents);
	}

	@Override
	public PaymentTransaction refund(Card card, double amount, String contents)
			throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentTransaction getBalance(Card card)
			throws InternalServerErrorException, InvalidDataErrorException, NotEnoughBalanceException {
		// TODO Auto-generated method stub
		return null;
	}

}
