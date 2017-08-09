package fritzmatias.core.model;

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
	double getStreightDistanceTo0(double normalizedPosition);
}
