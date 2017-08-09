package fritzmatias.core.model;

/**
 * @author user
 *
 */
public class SpatialSystemState implements Comparable<SpatialSystemState> {

	private int day = 0;

	public void setDay(int day) {
		this.day = day;
	}

	public int getDay() {
		return day;
	}

	private long intensity = 0;

	public long getIntensity() {
		return intensity;
	}

	public void setIntensity(long intensity) {
		this.intensity = intensity;
	}

	private SpatialSystemStateType type = SpatialSystemStateType.indefinido;

	public SpatialSystemStateType getType() {
		return type;
	}

	public void setType(SpatialSystemStateType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return this.getDay();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SpatialSystemState)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		SpatialSystemState o = (SpatialSystemState) obj;
		return this.getDay() == o.getDay();
	}

	@Override
	public int compareTo(SpatialSystemState o) {
		return this.getDay() - o.getDay();
	}

	@Override
	public String toString() {
		return "SpatialSystemState [day=" + day + ", type=" + type + ", intensity=" + intensity ;
	}


}
