/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import fritzmatias.core.withpatterns.model.Position;

/**
 * <p>
 * After a transformation process.<br>
 * The access to the original position retain the precision required to some
 * mathematical operations.
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public interface TransformedPosition extends Position {

	/**
	 * <p>
	 * Returns the original position, without any translation made or this.<br>
	 * This will be a more precise value, to operate with to manage precision
	 * operations.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @return
	 */
	public Position getOriginalPosition();

}
