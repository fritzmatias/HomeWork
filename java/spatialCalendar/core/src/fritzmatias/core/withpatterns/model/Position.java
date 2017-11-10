/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * <p>
 * This interface extends {@link org.opengis.geometry.coordinate.Position} to be
 * used in the future with some implementation of that library.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public interface Position extends org.opengis.geometry.coordinate.Position {

	/**
	 * <p>
	 * Return the origin of coordinates.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @return
	 */
	public Position origin();
	
	/**
	 * <p>
	 * Calculates the cross product between the segments TA and TB, where T is
	 * this point.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param A
	 *            one end of the segment TA
	 * @param B
	 *            one end of the segment TB
	 * @return the cross product without specific scale
	 * @see {@link Vector2D#crossProduct(Vector2D, Vector2D)}
	 */
	public double crossProduct(Position A, Position B);

	/**
	 * <p>
	 * Is the postive value representing the straight distance between position
	 * and this points.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param position
	 * @return the positive value distance between the position and this, with
	 *         the scale related to {@link #getScale()}
	 */
	public double distance(Position position);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @return a cartesian representation of this point.
	 * @see {@link Position#polar()} {@link Position#cartesian()}
	 */
	public CartesianPosition cartesian();

	/**
	 * <p>
	 * Translation from a position to a polar. The Accuracy is not defined.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @return the polar representation of this point
	 * @see {@link Position#polar()} {@link Position#cartesian()}
	 */
	public PolarPosition polar();

//	/**
//	 * <p>
//	 * </p>
//	 * 
//	 * @author user
//	 * @since Version: 2
//	 * @param scale
//	 * @return
//	 */
//	public Position setScale(MathContext scale);

}
