package Exception;

public class InvalidDataErrorException extends RuntimeException {
	public InvalidDataErrorException() {
		super("Invalid Data");
	}
}