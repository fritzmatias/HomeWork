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
 * 
 * @author user
 * @since Version: 1
 */
public interface PolarPosition extends Position {
	
	/**
	 * <p>
	 * Sustraction is a no conmutative operation.<br>
	 * a sustract b != b sustract a.<br>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param sustractor
	 * @return a new position object
	 * @see {@link #sustractDegrees(double) } {@link #sustractDegrees(PolarPosition)}
	 */
	public PolarPosition sustractDegrees(PolarPosition sustractor);
	
	/**
	 * <p>
	 * Sustraction is a no conmutative operation.<br>
	 * a sustract b != b sustract a.<br>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param sustractor
	 * @return a new position object
	 * @see {@link #sustractDegrees(double) } {@link #sustractDegrees(PolarPosition)}
	 */
	public PolarPosition sustractDegrees(double sustractor);


	/**
	 * <p>
	 * Compute a normalized mathematical addition in polars.
	 * The addition of -3 degrees, should be equals to the addition of 357 degrees. 
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param appender positive increment
	 * @return a new normalized position object
	 * @see {@link #addDegrees(double)} {@link #addDegrees(PolarPosition)}
	 */
	public PolarPosition addDegrees(PolarPosition appender);

	/**
	 * <p>
	 * Compute a normalized mathematical addition in polars.<br>
	 * The addition of -3 degrees, should be equals to the addition of 357 degrees.<br>
	 * The addition between 359+2 degrees is equivalent to 1 degree.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param appender positive increment
	 * @return a new normalized position object
	 * @see {@link #addDegrees(double)} {@link #addDegrees(PolarPosition)}
	 */
	public PolarPosition addDegrees(double appender);
	
	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @return
	 */
	public double getDegrees();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @return
	 */
	public double getRadians();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	public double getRadio();

}
