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
public class RESTResourceNotFound extends RuntimeException {
	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	private static final long serialVersionUID = -5323456963503002221L;

	public RESTResourceNotFound() {
		super();
	}

	public RESTResourceNotFound(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RESTResourceNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param message
	 * @param cause
	 */
	public RESTResourceNotFound(String message) {
		super(message);
	}
	
	public RESTResourceNotFound(Throwable cause) {
		super(cause);
	}

}
