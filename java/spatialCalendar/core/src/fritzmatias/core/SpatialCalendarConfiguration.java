/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import java.math.MathContext;
import java.math.RoundingMode;

import com.google.common.collect.ImmutableCollection;

import fritzmatias.core.model.Planet;
import fritzmatias.patterns.buider.BuilderConfiguration;

/**
 * <p>
 * Refactored from class to interface, thanks to the existence of the Builder
 * previously.
 * </p>
 * 
 * @see {@link SpatialCalendarBuilder}
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public interface SpatialCalendarConfiguration
		extends BuilderConfiguration<SpatialCalendarSystem> {

	public SpatialCalendarConfiguration setPlanets(ImmutableCollection<Planet> planets);

	public SpatialCalendarConfiguration setRangeToBuild(int min, int max);

	public SpatialCalendarConfiguration setScale(MathContext scale);

	public SpatialCalendarConfiguration setScale(int scale, RoundingMode mode);

	public SpatialCalendarConfiguration setIncludeVertices(boolean includeVertices);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 * @deprecated in v2
	 */
	@Deprecated
	public int getDaysPerYear();

	public boolean isIncludeVertices();

	public MathContext getScale();

	public int getMinRangeDays();

	public int getMaxRangeDays();

	public ImmutableCollection<Planet> getPlanets();
}
