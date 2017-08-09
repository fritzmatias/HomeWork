package fritzmatias.core.stateless.processors.spatial;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.model.SpatialSystemStateWithPositions;
import fritzmatias.core.stateless.processors.spatial.impl.AngularSpeedImp;
import fritzmatias.core.stateless.processors.spatial.impl.CircularOrbitImp;
import fritzmatias.core.model.Orbit;
import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;

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

	public static BiFunction<Integer, Planet, Vector2D> createDefaultPlanetPositionCalculator() {
		return SpatialObjectFactory.createPlanetPositionCalculator(0);
	}

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

	public static BiFunction<Integer, Planet, Vector2D> createPlanetPositionCalculator(int scale) {
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
	public static BiFunction<Vector2D, Vector2D[], SpatialSystemStateType> createShapeCalculator(boolean includeVertices) {
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
	public static SpatialSystemCenterdSunStateCalculator createSpatialSystemCenteredSunProcessor(int scale) {
		SpatialSystemCenterdSunStateCalculator c = new SpatialSystemCenterdSunStateCalculator();
		c.setPositionCalculator(SpatialObjectFactory.createPlanetPositionCalculator(scale));
		return c;
	}

}
