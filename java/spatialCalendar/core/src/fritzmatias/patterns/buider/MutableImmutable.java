/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.patterns.buider;

import javax.naming.OperationNotSupportedException;

/**
 * <p>
 * This interface is a hack for mutable objects to be transformed as immutable.
 * The extension of a mutable object and the implementation of this interface
 * should warrantie the imutability of the child making physical copies of the
 * mutable parts avoiding the coupling by reference.
 * </p>
 * 
 * @author matias
 * @param <T>
 *            type of the implemented object
 * @since Version: 1
 * @see {@link Immutator}
 */
public interface MutableImmutable<T> extends Cloneable {

	public boolean isMutable();

	/**
	 * <p>
	 * Is a atomic call, which transform and returns a mutable object OR return
	 * a new immutable construction of the object, with immutability
	 * warranties<br>
	 * After the call of this method, the {@link #isMutable()} should be false,
	 * and all the future calls to any set of this object should thrown an
	 * {@link OperationNotSupportedException}.<br>
	 * All the mutable attributes will be copied as part of a new object, and
	 * all the sets invalidated.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @return
	 */
	public T immutate();

	/**
	 * <p>
	 * {@link Cloneable}
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @return
	 */
	public T clone();
}
