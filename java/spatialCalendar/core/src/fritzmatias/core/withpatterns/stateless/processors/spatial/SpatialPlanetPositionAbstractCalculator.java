package fritzmatias.core.withpatterns.stateless.processors.spatial;

import java.math.MathContext;
import java.util.function.BiFunction;
import java.util.function.Supplier;

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
public abstract class SpatialPlanetPositionAbstractCalculator implements BiFunction<Integer, Planet, Position> {
	private MathContext scale;
	private Supplier<PolarPosition> polarSupplier;

	public SpatialPlanetPositionAbstractCalculator(MathContext scale) {
		super();
		this.scale = scale;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 */
	public SpatialPlanetPositionAbstractCalculator() {
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @return the presision
	 */
	public MathContext scale() {
		return scale;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @param scale
	 *            the precision to set
	 * @return
	 */
	public SpatialPlanetPositionAbstractCalculator setScale(MathContext scale) {
		this.scale = scale;
		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @return the polarSuplier
	 */
	public Supplier<PolarPosition> getPolarSupplier() {
		return polarSupplier;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @param supplier
	 *            the polarSuplier to set
	 * @return
	 */
	public SpatialPlanetPositionAbstractCalculator setPolarSupplier(Supplier<PolarPosition> supplier) {
		this.polarSupplier = supplier;
		return this;
	}

}
