/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.stateless.processors.spatial;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.model.SpatialSystemStateWithPositions;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialSystemCenterdSunStateCalculator implements BiFunction<Integer, Set<Planet>, SpatialSystemState> {
	private final Vector2D sunpoint = new Vector2D(0, 0);
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private BiFunction<Vector2D, Vector2D[], SpatialSystemStateType> shapeCalculator = SpatialObjectFactory
			.createShapeCalculator(true);
	private BiFunction<Integer, Planet, Vector2D> positionCalculator = SpatialObjectFactory
			.createDefaultPlanetPositionCalculator();
	private Planet sun;
	private Supplier<SpatialSystemStateWithPositions> supplier = SpatialObjectFactory.createSpatialSystemStateSupplier();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.BiFunction#apply(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public SpatialSystemState apply(final Integer day, final Set<Planet> planetset) {
		final Planet[] planets = planetset.toArray(new Planet[planetset.size()]);
		Vector2D[] positions = new Vector2D[planets.length];

		for (int positionIndex = 0; positionIndex < planets.length; positionIndex++) {
			positions[positionIndex] = positionCalculator.apply(day, planets[positionIndex]);
		}

		SpatialSystemStateType type = shapeCalculator.apply(sunpoint, positions);
		SpatialSystemStateWithPositions r = supplier.get();
		r.setDay(day);
		r.setPlanetPosition(positions);
		r.setType(type);

		if (type == SpatialSystemStateType.TrianguloEncierraOrigen) {
			r.setIntensity(calculatePerimeter(positions));
		}

		log.info("day: {}, Planets: {}, Positions: {}, type: {}", day, planets, positions, type);
		return r;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param positions
	 * @return
	 */
	protected long calculatePerimeter(Vector2D[] positions) {
		return (long) (positions[0].distanceSq(positions[1]) + positions[0].distanceSq(positions[2])
				+ positions[1].distanceSq(positions[2]));
	}

	public void setSun(Planet sun) {
		this.sun = sun;
	}

	public void setShapeCalculator(BiFunction<Vector2D, Vector2D[], SpatialSystemStateType> shapeCalculator) {
		this.shapeCalculator = shapeCalculator;
	}

	public BiFunction<Integer, Planet, Vector2D> getPositionCalculator() {
		return positionCalculator;
	}

	public void setPositionCalculator(BiFunction<Integer, Planet, Vector2D> positionCalculator) {
		this.positionCalculator = positionCalculator;
	}

	public Supplier<SpatialSystemStateWithPositions> getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier<SpatialSystemStateWithPositions> supplier) {
		this.supplier = supplier;
	}

	public Vector2D getSunpoint() {
		return sunpoint;
	}

	public BiFunction<Vector2D, Vector2D[], SpatialSystemStateType> getShapeCalculator() {
		return shapeCalculator;
	}

}
