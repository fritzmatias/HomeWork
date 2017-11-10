/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.stateless.processors.spatial;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableCollection;

import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialStatePolarCalculator extends SpatialStateAbstractCalculator {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.BiFunction#apply(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public SpatialSystemState apply(final Integer day, final ImmutableCollection<Planet> planetset) {

		final Planet[] planets = planetset.toArray(new Planet[planetset.size()]);

		SpatialShapeContext context = super.supplier().get();
		
		context.setDay(day);
		context.setPoint(super.getSunPoint());
		if (this.getScale() != null) {
			context.setScale(this.getScale());
		}

		for (int positionIndex = 0; positionIndex < planets.length; positionIndex++) {
			Position spatialPosition = super.getPositionCalculator().apply(day, planets[positionIndex]);
			context.addPosition(positionIndex, spatialPosition);
		}

		SpatialShapeType type = super.getShapeCalculator().apply(context);

		context.setType(type);

		log.info("day: {}, Planets: {}, Positions: {}", day, planets, context);

		return (SpatialSystemState) context;
	}

	public SpatialStatePolarCalculator() {
		super();
	}

	public SpatialStatePolarCalculator(SpatialShapeExclusiveTypeCalculator shapeCalculator,
			BiFunction<Integer, Planet, Position> positionCalculator, Supplier<SpatialShapeContext> supplier) {
		super(shapeCalculator, positionCalculator, supplier);
	}
}
