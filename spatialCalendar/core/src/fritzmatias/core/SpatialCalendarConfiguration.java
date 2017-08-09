/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import java.util.Set;

import fritzmatias.core.model.Planet;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialCalendarConfiguration {
	private Set<Planet> planets;
	private int minRangeDays;
	private int maxDaysPerYear;
	private int maxRangeDays;
	private int scale;
	private boolean includeVertices;

	public int getDaysPerYear() {
		if (planets == null) {
			throw new IllegalArgumentException("a Planet set is required.");
		}
		
		// Define the size of the collection for big amount of data,is more
		// performant on memory request.
		this.maxDaysPerYear = planets.stream().map(p -> {
			int d = p.getDaysPerYear();
			// log.info("Planet: {}, daysPerYear: {}", p, d);
			return d;
		}).max(Integer::compare).get();
		return maxDaysPerYear;
	}

	public Set<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(Set<Planet> planets) {
		this.planets = planets;
	}

	public void setRangeToBuild(int min,int max) {
		this.minRangeDays=min;
		this.maxRangeDays = max;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public boolean isIncludeVertices() {
		return includeVertices;
	}

	public void setIncludeVertices(boolean includeVertices) {
		this.includeVertices = includeVertices;
	}

	public int getMinRangeDays() {
		return minRangeDays;
	}

	public int getMaxRangeDays() {
		return maxRangeDays;
	}

}
