/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns;

import java.math.MathContext;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Orbit;
import fritzmatias.core.model.Planet;
import fritzmatias.core.stateless.processors.spatial.SpatialSpeed;
import fritzmatias.core.stateless.processors.spatial.impl.AngularSpeedImp;
import fritzmatias.core.stateless.processors.spatial.impl.CircularOrbitImp;
import fritzmatias.core.withpatterns.builder.SpatialShapeClasificatorBuilder;
import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.PolarPosition;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.imp.CartesianPosition2DImp;
import fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp;
import fritzmatias.core.withpatterns.model.imp.SpatialShapeManagerImp;
import fritzmatias.core.withpatterns.model.imp.SpatialStateImp;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialPlanetPositionCartesianCalculator;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialPlanetPositionPolarCalculator;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialShapeExclusiveTypeCalculator;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialStateAbstractCalculator;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialStateCartesianCalculator;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialStatePolarCalculator;
import fritzmatias.patterns.type.TypeManager;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialFactoryImp {

	public static SpatialShapeClasificatorBuilder shapeClasificatorBuilder() {
		return SpatialShapeClasificatorBuilder.builder();
	}

	public static TypeManager<SpatialShapeType> createShapeManager() {
		return new SpatialShapeManagerImp();
	}

	public static CartesianPosition createCartesianPosition(double x, double y) {
		return new CartesianPosition2DImp(x, y);
	}

	public static PolarPosition createPolarPosition(double radio, double angDegree) {
		return new PolarPosition2DImp(radio, angDegree);
	}

	public static SpatialPlanetPositionCartesianCalculator createCircularPositionCalculator(MathContext presision) {
		return new SpatialPlanetPositionCartesianCalculator(presision);
	}

	public static SpatialPlanetPositionPolarCalculator createCircularPositionLogicalCalculator(MathContext scale) {
		return new SpatialPlanetPositionPolarCalculator(scale);
	}

	public static SpatialStateAbstractCalculator createSpatialSystemCenteredSunAlgebraicCalculator() {
		SpatialShapeExclusiveTypeCalculator p = shapeClasificatorBuilder().setManager(createShapeManager()).build();

		SpatialStateAbstractCalculator c = new SpatialStateCartesianCalculator();
		c.setShapeCalculator(p);
		c.setPositionCalculator(createPlanetPositionAlgebraicCalculator());
		c.setSupplier(createSpatialSystemStateSupplier());
		return c;
	}

	public static SpatialStateAbstractCalculator createSpatialSystemCenteredSunLogicalCalculator() {
		SpatialStateAbstractCalculator c = new SpatialStatePolarCalculator();
		c.setShapeCalculator(shapeClasificatorBuilder().setManager(createShapeManager()).build());
		c.setPositionCalculator(createPlanetPositionLogicalCalculator());
		c.setSupplier(createSpatialSystemStateSupplier());
		return c;
	}

	public static Supplier<SpatialShapeContext> stateSupplier() {
		return SpatialStateImp::new;
	}

	public static SpatialSpeed<Double> createDefaultSpeed() {
		return new AngularSpeedImp();
	}

	public static Planet createPlanet() {
		return new Planet();
	}

	public static Orbit createDefaultOrbit() {
		return createCircularOrbit(AngularDirection.AntiHour, 1);
	}

	public static BiFunction<Integer, Planet, Position> createDefaultPlanetPositionCalculator() {
		return createPlanetPositionAlgebraicCalculator();
	}

	public static Orbit createCircularOrbit(AngularDirection direction, double distance) {
		CircularOrbitImp o = new CircularOrbitImp();
		o.setRadio(distance);
		o.setDirection(direction);
		return o;
	}

	public static Vector2D createVector2D(double x, double y) {
		return new Vector2D(x, y);
	}

	public static Planet createPlanet(String name, double speed, AngularDirection direction, double distance) {
		Planet p = new Planet();
		p.setName(name);
		p.setOrbit(createCircularOrbit(direction, distance));
		p.setSpeed(buildDegreeAngularSpeed(speed));
		// p.setPositionCalculator(createCircularPositionCalculator());
		return p;
	}

	private static SpatialSpeed<Double> buildDegreeAngularSpeed(double degreeSpeed) {
		AngularSpeedImp speed = new AngularSpeedImp();
		speed.setSpeed(degreeSpeed);
		return speed;

	}

	public static BiFunction<Integer, Planet, Position> createPlanetPositionAlgebraicCalculator() {
		return new SpatialPlanetPositionCartesianCalculator();
	}

	public static BiFunction<Integer, Planet, Position> createPlanetPositionLogicalCalculator() {
		return new SpatialPlanetPositionCartesianCalculator();
	}

	public static Supplier<SpatialShapeContext> createSpatialSystemStateSupplier() {
		return SpatialStateImp::new;
	}

	public static Supplier<PolarPosition> createPolarPositionSupplier() {
		return PolarPosition2DImp::new;
	}

	public static Supplier<CartesianPosition> createCartesianPositionSupplier() {
		return CartesianPosition2DImp::new;
	}

}
