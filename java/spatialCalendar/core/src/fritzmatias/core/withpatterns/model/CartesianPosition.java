/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.withpatterns.model;

/**
 * <p>
 * </p>
 * @author user
 * @since Version: 2
 */
public interface CartesianPosition extends Position {
	
	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param sustractor
	 * @return a new position object
	 */
	public CartesianPosition sustract(CartesianPosition sustractor);

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param appender
	 * @return a new position object
	 */
	public CartesianPosition append(CartesianPosition appender);

}
