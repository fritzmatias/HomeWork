package fritzmatias.core.model;

import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.stateless.processors.spatial.SpatialSpeed;

public class Planet {
	@Override
	public String toString() {
		return this.name;
	}

	private String name = "";
	private SpatialSpeed<Double> speed;
	private Orbit orbit;
	private double daysPerYear;

	public Planet() {
		super();
		this.setOrbit(SpatialObjectFactory.createDefaultOrbit());
		this.setSpeed(SpatialObjectFactory.createDefaultSpeed());
	}

	/**
	 * <p>
	 * This value is conditioned by the speed. and is modified each time the
	 * speed is set.<br>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @return days per year related to the constant speed.
	 */
	public int getDaysPerYear() {
		return (int) Math.floor(daysPerYear);
	}

	/**
	 * <p>
	 * Simplification from any day, to a particular state/position each year.
	 * Reduce the possibility of a math overflow and gives a possible cache utilization.
	 * 
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param day
	 * @return a day of the year in relation whith the amount of days in a year
	 *         for this planet.
	 */
	public int getDayOfYear(int day) {
		return (day % this.getDaysPerYear());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpatialSpeed<Double> getSpeed() {
		return speed;
	}

	public void setSpeed(SpatialSpeed<Double> speed) {
		this.speed = speed;
		if (this.getSpeed().getDegreesSpeed() == 0) {
			daysPerYear = 1;
		} else {
			daysPerYear = 360 / this.speed.getDegreesSpeed();
		}
	}

	public Orbit getOrbit() {
		return orbit;
	}

	public void setOrbit(Orbit orbit) {
		this.orbit = orbit;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orbit == null) {
			if (other.orbit != null)
				return false;
		} else if (!orbit.equals(other.orbit))
			return false;
		if (speed == null) {
			if (other.speed != null)
				return false;
		} else if (!speed.equals(other.speed))
			return false;
		return true;
	}

}
