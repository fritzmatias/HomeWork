/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;

import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * This class extends the {@link SpatialSystemState} for back compatibility
 * proposes and implements the new interface {@link SpatialShapeContext} <br>
 * </p>
 * 
 * @see {@link SpatialSystemState} {@link SpatialShapeContext}
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialStateImp extends SpatialSystemState implements SpatialShapeContext {
	private Map<Integer, Position> order = Maps.newConcurrentMap();
	private boolean includeVertices;
	private Position point;
	private ImmutableCollection<? extends Position> positions;
	// max precision for doubles
	private MathContext scale = MathAccuracy.createScale(15, RoundingMode.DOWN);

	@Override
	public void addPosition(int order, Position spatialPosition) {
		this.order.put(Integer.valueOf(order), spatialPosition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.SystemPositions#getPosition(int)
	 */
	@Override
	public Position getPosition(int order) {
		return this.order.get(order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SystemPositions#setPoint(fritzmatias.
	 * core.withpatterns.model.Position)
	 */
	@Override
	public void setPoint(Position point) {
		this.point = point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SystemPositions#setIncludeVertices(
	 * boolean)
	 */
	@Override
	public void setIncludeVertices(boolean includeVertices) {
		this.includeVertices = includeVertices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.SystemPositions#getPoint()
	 */
	@Override
	public Position getPoint() {
		return this.point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SystemPositions#getIncludeVertices()
	 */
	@Override
	public boolean getIncludeVertices() {
		return this.includeVertices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.SystemPositions#getScale()
	 */
	@Override
	public MathContext getScale() {
		return this.scale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.SystemPositions#setScale()
	 */
	@Override
	public void setScale(MathContext scale) {
		this.scale = scale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SpatialShapeContext#setDay(java.lang.
	 * Integer)
	 */
	@Override
	public void setDay(Integer day) {
		super.setDay(day);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SpatialShapeContext#setPerimeter(
	 * double)
	 */
	@Override
	public void setPerimeter(double perimeter) {
		super.setIntensity(new BigDecimal(perimeter).longValue());
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("{ ");
		sb.append(super.toString());
		sb.append(", ");
		sb.append("positions").append(" : ").append(positions);
//		sb.append("positions").append(" : ").append(order);
		sb.append(" }");

		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SpatialShapeContext#getPositions()
	 */
	@Override
	public ImmutableMap<Integer, Position> getPositions() {
		int i = 0;
		Builder<Integer,Position> map = ImmutableMap.<Integer, Position>builder();
		for (Position p : this.positions) {
			map.put(Integer.valueOf(i++), p);
		}
		return map.build();
		// return ImmutableMap.<Integer,
		// Position>builder().putAll(order).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SpatialShapeContext#setPositions(com.
	 * google.common.collect.ImmutableCollection)
	 */
	@Override
	public void setPositions(ImmutableCollection<? extends Position> positions) {
		this.positions = positions;
		
		int i = 0;
		Builder<Integer,Position> map = ImmutableMap.<Integer, Position>builder();
		for (Position p : this.positions) {
			map.put(Integer.valueOf(i++), p);
		}
		order=map.build();

	}

}
