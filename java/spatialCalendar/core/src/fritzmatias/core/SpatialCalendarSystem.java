package fritzmatias.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import com.google.common.collect.ImmutableCollection;

import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialStateAbstractCalculator;
import fritzmatias.patterns.buider.BuilderConfigurable;

/**
 * <p>
 * Refactored from class to interface, thanks to the existence of the Builder
 * previously.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public interface SpatialCalendarSystem
		 {

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 * @return
	 */
	public List<SpatialSystemState> reportMaxIntensity(Map<Integer, SpatialSystemState> states);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param days
	 * @param periods
	 * @return list of strings
	 */
	public List<String> reportYearlyStatus(final int days,
			Map<SpatialSystemStateType, List<SpatialSystemState>> periods);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 * @return
	 */
	public Map<SpatialSystemStateType, List<SpatialSystemState>> periodCalculator(
			Map<Integer, SpatialSystemState> states);

	/**
	 * <p>
	 * Builds a number of states related to the amount of days for each
	 * planet.<br>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param planetset
	 * @param stateCalculator
	 * @param maxRange
	 * @return
	 * @deprecated in v2
	 */
	@Deprecated
	public Map<Integer, SpatialSystemState> buildOneYearOfStates(final ImmutableCollection<Planet> planetset,
			int minRange, int maxRange);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 * @return
	 */
	public List<SpatialSystemState> buildSwitchStatusList(Map<Integer, SpatialSystemState> states);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 * @deprecated for an encapsulated
	 */
	@Deprecated
	public SpatialStateAbstractCalculator getStateCalculator();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param stateCalculator
	 * @deprecated in v2
	 */
	@Deprecated
	public void setStateCalculator(
			BiFunction<Integer, ImmutableCollection<Planet>, SpatialSystemState> stateCalculator);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param stateCalculator
	 * @return this
	 */
	public SpatialCalendarSystem setStateCalculator(SpatialStateAbstractCalculator stateCalculator);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param createShapeTypes
	 */
	public void register(Collection<SpatialShapeType> createShapeTypes);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	Map<Integer, SpatialSystemState> calculateStates();

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 2
	 * @param states
	 * @return
	 */
	public Map<SpatialShapeType, List<SpatialSystemState>> periodShapeCalculator(
			Map<Integer, SpatialSystemState> states);

}
