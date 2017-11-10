/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.sis.geometry.DirectPosition2D;
import org.opengis.geometry.DirectPosition;

import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * Represents the abstract
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
@SuppressWarnings("serial")
public abstract class AbstractTransformedPosition2DImp extends Vector2D implements Position {
	private Position originalPosition = this;
	private BigDecimal ordinates[] = new BigDecimal[2];

	public AbstractTransformedPosition2DImp(double x, double y) {
		super(x, y);
		setCoordinates(x, y);
	}

	public AbstractTransformedPosition2DImp(Position original, double x, double y) {
		super(x, y);
		if (original instanceof AbstractTransformedPosition2DImp) {
			this.originalPosition = ((AbstractTransformedPosition2DImp) original).getOriginalPosition();
		} else {
			this.originalPosition = original;
		}
		setCoordinates(x, y);
	}

	/**
	 * <p>
	 * Should be used only by creators.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param x
	 * @param y
	 */
	private void setCoordinates(double x, double y) {
		ordinates[0] = BigDecimal.valueOf(x);
		ordinates[1] = BigDecimal.valueOf(y);
	}

	/**
	 * <p>
	 * Returns the original position, without any translation made or this.<br>
	 * This will be a more precise value, to operate with to manage precision
	 * operations.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @return
	 */
	protected Position getOriginalPosition() {
		if (originalPosition != this && originalPosition instanceof AbstractTransformedPosition2DImp) {
			return ((AbstractTransformedPosition2DImp) originalPosition).getOriginalPosition();
		}
		return originalPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opengis.geometry.coordinate.Position#getDirectPosition()
	 */
	@Override
	public DirectPosition getDirectPosition() {
		return new DirectPosition2D(getOrdinal(0), getOrdinal(1));
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param i
	 * @return
	 */
	protected double getOrdinal(int i) {
		return ordinates[i].doubleValue();
	}

	protected MathContext getOrdinalScale(int i) {
		return MathAccuracy.createScale(ordinates[i].precision(), RoundingMode.DOWN);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof AbstractTransformedPosition2DImp)) {
			return false;
		}

		AbstractTransformedPosition2DImp o = (AbstractTransformedPosition2DImp) obj;
		if (this.getOriginalPosition() != o.getOriginalPosition()) {
			return (super.equals(o));
		}

		return true;
	}

	@Override
	public int hashCode() {
		if (originalPosition == this) {
			// stops the recursive call
			return super.hashCode(); //Vector2D hashCode
		}
		return originalPosition.hashCode() + super.hashCode();
	}

}
