/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.model.SpatialSystemStateType;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class CalendarTest {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() {
		SpatialCalendarConfiguration conf = SpatialCalendarBuilder.createConfiguration();

		conf.setPlanets(SpatialCalendarBuilder.buildPlanets());

		SpatialCalendarSystem calendar = SpatialCalendarBuilder.build(conf);
		conf.setRangeToBuild(0, conf.getDaysPerYear());
		
		log.info("dias: {}, reportDays: {}", conf.getDaysPerYear(), conf.getMaxRangeDays());

		Map<Integer, SpatialSystemState> states = calendar.buildOneYearOfStates(conf.getPlanets(),
				conf.getMinRangeDays(), conf.getMaxRangeDays());

		Map<SpatialSystemStateType, List<SpatialSystemState>> periods = calendar.periodCalculator(states);

		calendar.reportYearlyStatus(conf.getMaxRangeDays(), periods);
		calendar.reportMaxIntensity(states);

	}

	@Test
	public void test1() {
		SpatialCalendarConfiguration conf = SpatialCalendarBuilder.createConfiguration();

		conf.setPlanets(SpatialCalendarBuilder.buildPlanets());

		SpatialCalendarSystem calendar = SpatialCalendarBuilder.build(conf);
		conf.setRangeToBuild(0, 1);
		
		log.info("dias: {}, reportDays: {}", conf.getDaysPerYear(), conf.getMaxRangeDays());

		Map<Integer, SpatialSystemState> states = calendar.buildOneYearOfStates(conf.getPlanets(),
				conf.getMinRangeDays(), conf.getMaxRangeDays());

		Map<SpatialSystemStateType, List<SpatialSystemState>> periods = calendar.periodCalculator(states);

		calendar.reportYearlyStatus(conf.getMaxRangeDays(), periods);
		calendar.reportMaxIntensity(states);
		List<SpatialSystemState> switchs = calendar.buildSwitchStatusList(states);
		log.info("{}",switchs);

	}
	
}
