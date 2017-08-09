/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@ControllerAdvice
public class RESTResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { RESTResourceNotFound.class })
	public ResponseEntity<Object> handleResourceNotFound(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "The parameter is not set with the proper value.";
		return new ResponseEntity<Object>(ex.getMessage() != null ? ex.getMessage() : bodyOfResponse, new HttpHeaders(),
				HttpStatus.CONFLICT);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = { RESTNotAllowOperation.class })
	public ResponseEntity<Object> handleNotAllowOperation(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage() != null ? ex.getMessage() : "Forbidden operation.",
				new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
}
