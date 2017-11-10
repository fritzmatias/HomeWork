/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.imp;

import java.math.MathContext;
import java.math.RoundingMode;

import fritzmatias.core.SpatialCalendarConfiguration;
import fritzmatias.core.SpatialCalendarSystem;
import fritzmatias.patterns.buider.Builder;
import fritzmatias.patterns.buider.imp.ImmutableBuilderConfigurableAbstract;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
// public abstract class SpatialCalendarAbstractBuilder extends
// ImmutableBuilderAbstract<SpatialCalendarSystem>
// implements BuilderConfigurable<SpatialCalendarSystem,
// SpatialCalendarConfiguration> {
public abstract class SpatialCalendarAbstractBuilder
		extends ImmutableBuilderConfigurableAbstract<SpatialCalendarSystem, SpatialCalendarConfiguration> {

	private MathContext maxScale;

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @return
	 * @since Version: 2
	 */
	protected SpatialCalendarAbstractBuilder calculateMaxScale() {
		double maxDistance = getConfig().getPlanets().stream().map(p -> p.getOrbit().getStreightDistanceTo0(0))
				.max((a, b) -> (int) (a - b)).get();

		int pres = estimatePrecision(maxDistance);
		this.maxScale = new MathContext(pres, RoundingMode.DOWN);

		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param maxDistance
	 * @return
	 */
	public int estimatePrecision(double maxDistance) {
		return 15 - (int) (Math.log10(maxDistance)-2);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @return
	 * @since Version: 1
	 */
	protected SpatialCalendarAbstractBuilder validateScale() {
		if (maxScale.getPrecision() < getConfig().getScale().getPrecision()) {
			throw new IllegalArgumentException("The precision defined can't be accomplish by this implementation.");
		}
		return this;
	}

}