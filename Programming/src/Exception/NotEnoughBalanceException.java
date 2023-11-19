package Exception;

public class NotEnoughBalanceException extends RuntimeException {
	public NotEnoughBalanceException() {
		super("Not enough balance");
	}
}