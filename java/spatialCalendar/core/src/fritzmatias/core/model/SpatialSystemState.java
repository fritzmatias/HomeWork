package fritzmatias.core.model;

import fritzmatias.core.withpatterns.model.SpatialShapeType;

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

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @return
	 * @deprecated
	 * @see {@link #setShape(SpatialShapeType) #getShape()}
	 */
	@Deprecated
	public SpatialSystemStateType getType() {
		if(this.getShape() != null) {
			return this.getShape().getType();
		}
		return type;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param type
	 * @deprecated
	 * @see {@link #setShape(SpatialShapeType) #getShape()}
	 */
	@Deprecated
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
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		sb.append("day").append(" : ").append(day);
		sb.append(", ");
		sb.append("type").append(" : ").append(this.getType());
		sb.append(", ");
		sb.append("intensity").append(" : ").append(intensity);
		sb.append(" }");

		return sb.toString();
	}

	private SpatialShapeType shape;

	public SpatialShapeType getShape() {
		return shape;
	}

	public SpatialSystemState setShape(SpatialShapeType shape) {
		this.shape = shape;
		this.setType(SpatialSystemStateType.indefinido);
		return this;
	}

	public void setType(SpatialShapeType shape) {
		this.setShape(shape);
	}
	
}
