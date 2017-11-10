package fritzmatias.core.withpatterns.stateless.processors.spatial;

import java.math.MathContext;

import fritzmatias.core.model.Planet;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
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
public class SpatialPlanetPositionCartesianCalculator extends SpatialPlanetPositionAbstractCalculator {

	public SpatialPlanetPositionCartesianCalculator(MathContext presision) {
		super(presision);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 */
	public SpatialPlanetPositionCartesianCalculator() {
	}

	@Override
	public Position apply(Integer dia, Planet planet) {
		double angularDegreeMovement = (dia * planet.getSpeed().getDegreesSpeed())
				* planet.getOrbit().getDirection().mathModificator();

//		PolarPosition position = super.getPolarSupplier().get();
//		position.getDirectPosition().setOrdinate(1, angularDegreeMovement);
		double oDistance = planet.getOrbit().getStreightDistanceTo0(angularDegreeMovement);
//		position.getDirectPosition().setOrdinate(0, oDistance);
//		return position.cartesian();
		return SpatialFactoryImp.createPolarPosition(oDistance,angularDegreeMovement);
	}

}
