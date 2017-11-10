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
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.shapes.imp.ImmutableCartesianPointShapeHelper;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialStateCartesianCalculator extends SpatialStateAbstractCalculator {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.BiFunction#apply(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public SpatialSystemState apply(final Integer day, final ImmutableCollection<Planet> planetset) {
		// final Planet[] planets = planetset.toArray(new
		// Planet[planetset.size()]);

		SpatialShapeContext context = super.supplier().get();
		context.setDay(day);
		context.setPoint(super.getSunPoint());
		if (this.getScale() != null) {
			context.setScale(this.getScale());
		}

		ImmutableCollection<? extends Position> positions = ImmutableList
				.<CartesianPosition>builder().addAll(planetset.stream()
						.map(p -> super.getPositionCalculator().apply(day, p).cartesian()).collect(Collectors.toList()))
				.build();

		context.setPositions(positions);

		ImmutableCartesianPointShapeHelper immutable = new ImmutableCartesianPointShapeHelper(context);

		// for (int positionIndex = 0; positionIndex < planets.length;
		// positionIndex++) {
		// Position spatialPosition = super.getPositionCalculator().apply(day,
		// planets[positionIndex]).cartesian();
		// context.setPosition(positionIndex, spatialPosition);
		// }

		SpatialShapeType type = super.getShapeCalculator().apply(context);

		context.setType(type);

		return (SpatialSystemState) context;
	}

	public SpatialStateCartesianCalculator() {
		super();
	}

	public SpatialStateCartesianCalculator(SpatialShapeExclusiveTypeCalculator shapeCalculator,
			BiFunction<Integer, Planet, Position> positionCalculator, Supplier<SpatialShapeContext> supplier) {
		super(shapeCalculator, positionCalculator, supplier);
	}
}
