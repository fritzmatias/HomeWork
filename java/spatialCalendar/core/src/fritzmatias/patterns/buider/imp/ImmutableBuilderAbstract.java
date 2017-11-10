package fritzmatias.patterns.buider.imp;

import java.util.Map;

/**
 * <p>
 * Mutable until {@link #build()} is call, and then is locked to behave as
 * immutable, Builder.<br>
 * This class is responsible to collect all the parameters to be used in the
 * {@link ImmutableBuilderAbstract#create()} implementation.<br>
 * </p>
 * <p>
 * The idea of this class is to be extended by some builder implementation
 * </p>
 * <p>
 * Surety: multiple calls to {@link #build()} will return different instances
 * under exactly the same configuration values.
 * </p>
 * 
 * @author user
 * @since Version: 2
 * @param <T>
 *            the type to be build.
 */
public abstract class ImmutableBuilderAbstract<T> extends BuilderAbstract<T> {
	private boolean lock = false;
	private String exceptionMessage;

	public ImmutableBuilderAbstract() {
		super();
		exceptionMessage = "Can't set data after calling build()";
	}

	public ImmutableBuilderAbstract(String exceptionMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param map
	 */
	public ImmutableBuilderAbstract(Map<String, Object> map) {
		super(map);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	protected String getExceptionMessage() {
		return exceptionMessage;
	}

	protected void throwExceptionifisLock() {
		if (lock) {
			throw new UnsupportedOperationException(getExceptionMessage());
		}
	}

	/**
	 * <p>
	 * A generic set who throws an exception if the {@link #build()} method was
	 * call.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param key
	 * @param value
	 * @throws UnsupportedOperationException
	 *             if the build() method was call
	 * @return this
	 */
	protected ImmutableBuilderAbstract<T> set(String key, Object value) {
		throwExceptionifisLock();
		super.set(key, value);
		return this;
	}

	/**
	 * <p>
	 * The lock is not a concurrency semaphore. Is only a lock for disable the setters.
	 * </p>
	 * @author user
	 * @since Version: 1
	 */
	protected void lockBuilder() {
		lock = true;
	}

	public T build() {
		lockBuilder();
		return create();
	}

	/**
	 * <p>
	 * Creates the specific instance once build() is called
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	protected abstract T create();

}
