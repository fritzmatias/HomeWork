/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class IllegalDomainException extends RuntimeException {
	private static final long serialVersionUID = 8517556428703341266L;

	public IllegalDomainException() {
		super();
	}

	public IllegalDomainException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalDomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalDomainException(String message) {
		super(message);
	}

	public IllegalDomainException(Throwable cause) {
		super(cause);
	}

}
