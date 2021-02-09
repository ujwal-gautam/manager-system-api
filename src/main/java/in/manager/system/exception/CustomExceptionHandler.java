package in.manager.system.exception;

import in.manager.system.constants.ErrorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:19 AM
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger exceptionLogger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", "Server Error");
        responseBody.put("error", ex.getMessage() + "\t" + details);
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(CustomAccessDeniedException ex, WebRequest request) {
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException ex, WebRequest request)
    {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", ErrorConstants.ALREADY_EXIST);
        responseBody.put("error", ex.getMessage());
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", ErrorConstants.INVALID_DATA);
        responseBody.put("error", ex.getMessage());
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(InvalidUserCredentialsException.class)
    public final ResponseEntity<Object> handleInvalidUserCredentialsException(InvalidUserCredentialsException ex,
                                                                              WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", ErrorConstants.INVALID_USER);
        responseBody.put("error", ex.getMessage());
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(responseBody, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler(JwtTokenMissingException.class)
    public final ResponseEntity<Object> handleJwtTokenMissingException(JwtTokenMissingException ex,
                                                                       WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", ErrorConstants.MISSING_TOKEN);
        responseBody.put("error", ex.getMessage());
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(responseBody, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

   /* @ExceptionHandler(SqlException.class)
    public final ResponseEntity<Object> sqlException(JwtTokenMissingException ex,
                                                                       WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", "CDT-004");
        responseBody.put("error", ex.getMessage());
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(responseBody, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }*/

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required'
     * request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", ErrorConstants.API_ERROR);
        responseBody.put("error", error);
        exceptionLogger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
