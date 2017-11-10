/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.patterns.lazzy;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 * Lazzy cachable getter. Once the first {@link #get()} is call and the
 * returnable value is calculated the object is immutable. Future calls will
 * return the original object<br>
 * This imutablity is related to the resultable value instance, wich is
 * calculated only once, not to the parameters or the returned object itself, if
 * they are not immutebles. No action is taken to warrant the full
 * imutability.<br>
 * </p>
 * 
 * @author user
 * @since Version: 1
 * @param <T> type of the parameter
 * @param <R> type of the returnable value
 */
public abstract class FunctionLazzyCacheable<T, R> implements Function<T, R> {
	private Supplier<? extends R> supplier;
	private R result = null;
	private T param;

	/**
	 * <p>
	 * The first object processed. This is not an immutable warranty, the
	 * returned object could be modified in the future if it is mutable.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return the first object calculated.
	 */
	public final R get() {
		if (result == null) {
			result = this.apply(param);
		}
		return result;
	};

	/**
	 * <p>
	 * The set is mutable until the fist get is called. After that, an
	 * exceptions {@link UnsupportedOperationException} is thrown.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param parameter
	 * @return
	 * @throws UnsupportedOperationException
	 *             if this methis is called after the first {@link #get()}
	 */
	public FunctionLazzyCacheable<? extends T, ? extends R> setParameter(T parameter)
			throws UnsupportedOperationException {
		throwExceptionIfwasExecuted();
		this.param = parameter;
		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 */
	private void throwExceptionIfwasExecuted() throws UnsupportedOperationException {
		if (result != null) {
			throw new UnsupportedOperationException("The object is immutable.");
		}
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param supplier
	 * @throws UnsupportedOperationException
	 *             if this methis is called after the first {@link #get()}
	 * @return
	 */
	public FunctionLazzyCacheable<? extends T, ? extends R> setSupplier(Supplier<? extends R> supplier)
			throws UnsupportedOperationException {
		throwExceptionIfwasExecuted();
		this.supplier = supplier;
		return this;
	}

	/**
	 * <p>
	 * Returns the supplier of returnable type
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	protected Supplier<? extends R> supplier() {
		return supplier;
	}
}
