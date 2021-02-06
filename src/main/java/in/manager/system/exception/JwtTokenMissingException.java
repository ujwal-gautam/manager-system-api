package in.manager.system.exception;

import javax.naming.AuthenticationException;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 12:53 PM
 */
public class JwtTokenMissingException extends AuthenticationException {

    public JwtTokenMissingException(String message) {
        super(message);
    }
}
