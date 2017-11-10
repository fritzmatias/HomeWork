/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest.exceptions;

/**
 * <p>
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class RESTNotAllowOperation extends RuntimeException {

	public RESTNotAllowOperation() {
		super();
	}

	public RESTNotAllowOperation(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RESTNotAllowOperation(String message, Throwable cause) {
		super(message, cause);
	}

	public RESTNotAllowOperation(String message) {
		super(message);
	}

	public RESTNotAllowOperation(Throwable cause) {
		super(cause);
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	private static final long serialVersionUID = -1009034419156155007L;

}
