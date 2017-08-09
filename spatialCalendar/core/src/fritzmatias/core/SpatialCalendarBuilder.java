/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import java.util.Set;
import java.util.TreeSet;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Planet;
import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.stateless.processors.spatial.SpatialSystemCenterdSunStateCalculator;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialCalendarBuilder {

	public static SpatialCalendarConfiguration createConfiguration() {
		SpatialCalendarConfiguration conf = new SpatialCalendarConfiguration();
		return conf;
	}

	public static SpatialCalendarSystem build(SpatialCalendarConfiguration config) {
		SpatialCalendarSystem calendar=new SpatialCalendarSystem();
		calendar.setConfig(config);
		
		SpatialSystemCenterdSunStateCalculator stateCalculator = SpatialObjectFactory.createSpatialSystemCenteredSunProcessor(config.getScale());
		if (config.isIncludeVertices()) {
			stateCalculator.setShapeCalculator(SpatialObjectFactory.createShapeCalculator(true));
		}

		calendar.setStateCalculator(stateCalculator);
				
		return calendar;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @return
	 */
	public static Set<Planet> buildPlanets() {
		Set<Planet> planets = new TreeSet<Planet>(
				(a, b) -> Double.compare(a.getSpeed().getDegreesSpeed(), b.getSpeed().getDegreesSpeed()));
		planets.add(SpatialObjectFactory.createPlanet("F", 1, AngularDirection.Hour, 300));
		planets.add(SpatialObjectFactory.createPlanet("V", 3, AngularDirection.Hour, 2000));
		planets.add(SpatialObjectFactory.createPlanet("I", 5, AngularDirection.AntiHour, 1000));
		return planets;
	}

}
