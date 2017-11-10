package fritzmatias.core.stateless.processors.spatial;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Orbit;
import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemStateWithPositions;
import fritzmatias.core.stateless.processors.spatial.impl.AngularSpeedImp;
import fritzmatias.core.stateless.processors.spatial.impl.CircularOrbitImp;

/**
 * @author user
 */
public class SpatialObjectFactory {

	public static SpatialSpeed<Double> createDefaultSpeed() {
		return new AngularSpeedImp();
	}

	public static Planet createPlanet() {
		return new Planet();
	}

	public static Orbit createDefaultOrbit() {
		return SpatialObjectFactory.createCircularOrbit(AngularDirection.Hour, 1);
	}

//	public static BiFunction<Integer, Planet, Vector2D> createDefaultPlanetPositionCalculator() {
//		return SpatialObjectFactory.createPlanetPositionCalculator();
//	}

	public static Orbit createCircularOrbit(AngularDirection direction, int distance) {
		CircularOrbitImp o = new CircularOrbitImp();
		o.setRadio(distance);
		o.setDirection(direction);
		return o;
	}

	public static Vector2D createVector2D(double x, double y) {
		return new Vector2D(x, y);
	}

	public static Planet createPlanet(String name, int speed, AngularDirection direction, int distance) {
		Planet p = new Planet();
		p.setName(name);
		p.setOrbit(createCircularOrbit(direction, distance));
		p.setSpeed(buildDegreeAngularSpeed(speed));
		return p;
	}

	private static SpatialSpeed<Double> buildDegreeAngularSpeed(int degreeSpeed) {
		AngularSpeedImp speed = new AngularSpeedImp();
		speed.setSpeed(degreeSpeed);
		return speed;

	}

	public static BiFunction<Integer, Planet, Vector2D> createPlanetPositionCalculator(MathContext scale) {
		return new SpatialPlanetPositionCalculator(scale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @return
	 */
	public static SpatialShapeCalculator createShapeCalculator(boolean includeVertices) {
		return new SpatialShapeCalculator(includeVertices);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @return
	 */
	public static Supplier<SpatialSystemStateWithPositions> createSpatialSystemStateSupplier() {
		return SpatialSystemStateWithPositions::new;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param scale
	 * @return
	 */
	public static SpatialSystemCenterdSunStateCalculator createSpatialSystemCenteredSunProcessor(MathContext scale) {
		SpatialSystemCenterdSunStateCalculator c = new SpatialSystemCenterdSunStateCalculator();
		c.setPositionCalculator(createPlanetPositionCalculator(scale));
		c.setShapeCalculator(createShapeCalculator(true));
		c.setSupplier(createSpatialSystemStateSupplier());
		return c;
	}

}
