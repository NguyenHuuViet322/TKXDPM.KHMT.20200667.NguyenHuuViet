package Exception;

public class InternalServerErrorException extends RuntimeException {
	public InternalServerErrorException() {
		super("Internal Server Error");
	}
}