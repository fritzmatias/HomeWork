package fritzmatias.core.model;

import fritzmatias.core.stateless.processors.spatial.SpatialSpeed;
import fritzmatias.core.withpatterns.model.Position;

public interface Orbit {
	public AngularDirection getDirection();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param t
	 *            the parameter for build the phisical distance to the origin of
	 *            coordenates.
	 * @return the normalized distance between
	 */
	public double getStreightDistanceTo0(double normalizedPosition);

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param time
	 * @param speed
	 * @return
	 */
	public Position calculatePosition(double time,SpatialSpeed<Double> speed);
}
