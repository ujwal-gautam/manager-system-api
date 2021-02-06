package in.manager.system.exception;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:31 AM
 */

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String message) {
        super(message);
    }
}
