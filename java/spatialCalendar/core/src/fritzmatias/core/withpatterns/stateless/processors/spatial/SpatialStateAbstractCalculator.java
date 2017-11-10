/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.stateless.processors.spatial;

import java.math.MathContext;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableCollection;

import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
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
public abstract class SpatialStateAbstractCalculator implements BiFunction<Integer, ImmutableCollection<Planet>, SpatialSystemState> {
	private final Position sunpoint = SpatialFactoryImp.createCartesianPosition(0, 0);
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SpatialShapeExclusiveTypeCalculator shapeCalculator;
	private BiFunction<Integer, Planet, Position> positionCalculator;
	private Supplier<SpatialShapeContext> supplier;
	private MathContext scale;

	public SpatialStateAbstractCalculator() {
		super();
	}

	public SpatialStateAbstractCalculator(SpatialShapeExclusiveTypeCalculator shapeCalculator,
			BiFunction<Integer, Planet, Position> positionCalculator, Supplier<SpatialShapeContext> supplier) {
		super();
		this.shapeCalculator = shapeCalculator;
		this.positionCalculator = positionCalculator;
		this.supplier = supplier;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param calculator
	 * @return
	 */
	public SpatialStateAbstractCalculator setPositionCalculator(BiFunction<Integer, Planet, Position> calculator) {
		this.positionCalculator = calculator;
		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @param shapeCalculator
	 *            the shapeCalculator to set
	 * @return
	 */
	public SpatialStateAbstractCalculator setShapeCalculator(SpatialShapeExclusiveTypeCalculator shapeCalculator) {
		this.shapeCalculator = shapeCalculator;
		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @param supplier
	 *            the supplier to set
	 * @return
	 */
	public SpatialStateAbstractCalculator setSupplier(Supplier<SpatialShapeContext> supplier) {
		this.supplier = supplier;
		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param createShapeTypes
	 */
	public void register(Collection<SpatialShapeType> createShapeTypes) {
		this.shapeCalculator.register(createShapeTypes);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	protected SpatialShapeExclusiveTypeCalculator getShapeCalculator() {
		return this.shapeCalculator;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	protected BiFunction<Integer, Planet, Position> getPositionCalculator() {
		return this.positionCalculator;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	public Supplier<SpatialShapeContext> supplier() {
		return this.supplier;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	protected Position getSunPoint() {
		return this.sunpoint;
	}

	public MathContext getScale() {
		return scale;
	}

	/**
	 * <p>
	 * Overrides the scale of the internal calculations.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param scale
	 * @return
	 */
	public SpatialStateAbstractCalculator setScale(MathContext scale) {
		this.scale = scale;
		return this;
	}

}
