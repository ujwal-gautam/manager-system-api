package in.manager.system.exception;

public class InactiveUserException extends RuntimeException {

    public InactiveUserException(String message) {
	super(message);
    }
}
