/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.PolarPosition;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class Positions {

	public final static CartesianPosition cartesian(double x, double y) {
		return new CartesianPosition2DImp(x,y);
	}

	public final static PolarPosition polar(double radii, double angDegree) {
		return new PolarPosition2DImp(radii, angDegree);
	}
	
	
}
