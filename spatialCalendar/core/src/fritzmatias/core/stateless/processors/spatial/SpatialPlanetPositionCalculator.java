package fritzmatias.core.stateless.processors.spatial;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import fritzmatias.core.model.Planet;

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
public class SpatialPlanetPositionCalculator implements BiFunction<Integer, Planet, Vector2D> {

	private int scale=0;

	public SpatialPlanetPositionCalculator(int scale) {
		super();
		this.scale = scale;
	}
	
	@Override
	public Vector2D apply(Integer dia, Planet planet) {
		// TODO: is important to compensate the remaining part of the last day
		// of each year if the days per year are not integers.
		// not required for this excersice.
		int t = planet.getDayOfYear(dia);

		double angularDegreeMovement = (t * planet.getSpeed().getDegreesSpeed() )
				* planet.getOrbit().getDirection().mathModificator();

		// (360degrees + (-) movement )%360 is a positive value. 0<= x < 360
		double angularNormalizedPosition = (360 + (angularDegreeMovement)) % 360;

		
		double oDistance = planet.getOrbit().getStreightDistanceTo0(angularNormalizedPosition);
		// x= R cos r; y= R sin r
		double radians=Math.toRadians(angularNormalizedPosition);
		double x= (Math.cos(radians) * oDistance);
		double y= (Math.sin(radians) * oDistance);
		x = BigDecimal.valueOf(x)
			    .setScale(scale, RoundingMode.HALF_UP)
			    .doubleValue();
		y = BigDecimal.valueOf(y)
			    .setScale(scale, RoundingMode.HALF_UP)
			    .doubleValue();
		Vector2D position = SpatialObjectFactory.createVector2D(x, y);
		return position;
	}

}
