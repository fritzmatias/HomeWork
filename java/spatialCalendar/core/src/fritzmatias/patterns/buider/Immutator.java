/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.patterns.buider;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public interface Immutator<T> {

	/**
	 * <p>
	 * Creates a new immutable representation of T.
	 * </p>
	 * @author matias
	 * @since Version: 1
	 * @param t
	 * @return a immutable representation of t
	 */
	public MutableImmutable<T> immutate(T t);
}
