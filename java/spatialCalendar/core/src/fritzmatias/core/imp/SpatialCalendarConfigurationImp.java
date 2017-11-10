/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.imp;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableCollection;

import fritzmatias.core.SpatialCalendarConfiguration;
import fritzmatias.core.SpatialCalendarSystem;
import fritzmatias.core.model.Planet;
import fritzmatias.patterns.buider.Builder;
import fritzmatias.patterns.buider.BuilderConfigurable;
import fritzmatias.patterns.buider.imp.ImmutableBuilderConfigurationAbstract;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialCalendarConfigurationImp extends ImmutableBuilderConfigurationAbstract<SpatialCalendarSystem,SpatialCalendarConfiguration>
		implements SpatialCalendarConfiguration {
	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param suppllier
	 */
	public SpatialCalendarConfigurationImp(
			Supplier<BuilderConfigurable<SpatialCalendarSystem,SpatialCalendarConfiguration>> suppllier) {
		super(suppllier);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 */
	private static final String SYSTEM = "system";
	private static final String PLANETS = "planets";
	private static final String INCLUDE_VERTICES = "includeVertices";
	private static final String SCALE = "scale";
	private static final String MAX_RANGE_DAYS = "maxRangeDays";
	private static final String MIN_RANGE_DAYS = "minRangeDays";

	public SpatialCalendarConfiguration setSpatialCalendar(Builder<SpatialCalendarSystem> impl) {
		super.set(SYSTEM, impl);
		return this;
	};

	@Deprecated
	public int getDaysPerYear() {
		ImmutableCollection<Planet> planets = this.getPlanets();
		if (planets == null) {
			throw new IllegalArgumentException("a Planet set is required.");
		}

		// Define the size of the collection for big amount of data,is more
		// performant on memory request.
		int maxDaysPerYear = planets.stream().map(p -> {
			int d = p.getDaysPerYear();
			// log.info("Planet: {}, daysPerYear: {}", p, d);
			return d;
		}).max(Integer::compare).get();
		return maxDaysPerYear;
	}

	public ImmutableCollection<Planet> getPlanets() {
		return (ImmutableCollection<Planet>) get(PLANETS);
	}

	public SpatialCalendarConfiguration setPlanets(ImmutableCollection<Planet> planets) {
		this.set(PLANETS, planets);

		return this;
	}

	public SpatialCalendarConfiguration setRangeToBuild(int min, int max) {
		super.set(MIN_RANGE_DAYS, min);
		super.set(MAX_RANGE_DAYS, max);
		return this;
	}

	public MathContext getScale() {
		return (MathContext) super.getOptional(SCALE);
	}

	public SpatialCalendarConfiguration setScale(MathContext scale) {
		super.set(SCALE, scale);
		return this;
	}

	public boolean isIncludeVertices() {
		return (boolean) get(INCLUDE_VERTICES);
	}

	public SpatialCalendarConfiguration setIncludeVertices(boolean includeVertices) {
		set(INCLUDE_VERTICES, includeVertices);
		return this;
	}

	public int getMinRangeDays() {
		return (int) get(MIN_RANGE_DAYS);
	}

	public int getMaxRangeDays() {
		return (int) get(MAX_RANGE_DAYS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.SpatialCalendarConfiguration#setScale(int,
	 * java.math.RoundingMode)
	 */
	@Override
	public SpatialCalendarConfiguration setScale(int scale, RoundingMode mode) {
		this.setScale(new MathContext(scale, mode));
		return this;
	}

}
