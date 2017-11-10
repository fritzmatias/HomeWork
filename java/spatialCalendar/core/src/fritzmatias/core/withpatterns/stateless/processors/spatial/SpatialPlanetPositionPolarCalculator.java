package fritzmatias.core.withpatterns.stateless.processors.spatial;

import java.math.MathContext;

import fritzmatias.core.model.Planet;
import fritzmatias.core.withpatterns.model.PolarPosition;
import fritzmatias.core.withpatterns.model.Position;

/**
 * <p>
 * Function who calculates the specific position of a planet in a particular day
 * in the future.<br>
 * For the day 0, the position should be the orbit starting point.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialPlanetPositionPolarCalculator extends SpatialPlanetPositionAbstractCalculator {

	public SpatialPlanetPositionPolarCalculator(MathContext presision) {
		super(presision);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 */
	public SpatialPlanetPositionPolarCalculator() {
	}

	@Override
	public Position apply(Integer dia, Planet planet) {
		double angularDegreeMovement = (dia * planet.getSpeed().getDegreesSpeed())
				* planet.getOrbit().getDirection().mathModificator();

		PolarPosition position = super.getPolarSupplier().get();
		position.getDirectPosition().setOrdinate(1, angularDegreeMovement);
		double oDistance = planet.getOrbit().getStreightDistanceTo0(position.getDegrees());
		position.getDirectPosition().setOrdinate(0, oDistance);

		return position;
	}

}
