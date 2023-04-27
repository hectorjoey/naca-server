

package fhi360.it.assetverify.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<?> resourceNotFoundException(final ResourceNotFoundException ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return (ResponseEntity<?>)new ResponseEntity((Object)errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> globleExcpetionHandler(final Exception ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return (ResponseEntity<?>)new ResponseEntity((Object)errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler({ AlreadyExistsException.class })
    public ResponseEntity<?> alreadyExistsException(final AlreadyExistsException ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return (ResponseEntity<?>)new ResponseEntity((Object)errorDetails, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler({ EmailExistsException.class })
    public ResponseEntity<?> EmailExistsException(final EmailExistsException ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return (ResponseEntity<?>)new ResponseEntity((Object)errorDetails, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler({ BadRequest.class })
    public ResponseEntity<?> badRequest(final BadRequest ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return (ResponseEntity<?>)new ResponseEntity((Object)errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ PasswordMismatchException.class })
    public ResponseEntity<?> selectSupervisor(final PasswordMismatchException ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return (ResponseEntity<?>)new ResponseEntity((Object)errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
