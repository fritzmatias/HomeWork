package fritzmatias.core.stateless.processors.spatial;

/**
 * <p>
 * Represents the speed contract
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @param <R> 
 * @since Version: 1
 */
public interface SpatialSpeed<R> {

	/**
	 * <p>
	 * The returned value should be restricted by 0<= x < 360.
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @return the absolute value of the speed in degrees.
	 */
	public R getDegreesSpeed();

}
