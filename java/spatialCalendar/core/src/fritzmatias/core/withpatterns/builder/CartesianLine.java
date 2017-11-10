/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.withpatterns.builder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;

import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.imp.Positions;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * Poligono construido desde multiples ecuaciones de rectas.
 * </p>
 * http://www.robertobigoni.eu/Matematica/Lines/lines08/lines08.html
 *
 * @author user
 * @since Version: 1
 */
public class CartesianLine implements Function<Double, CartesianPosition> {
	private MathContext scale = MathAccuracy.createScale(17, RoundingMode.HALF_EVEN);
	private Double inclination = Double.valueOf(0.0);
	private Double independentTerm = Double.valueOf(0.0);

	// protected LineSuppl}ier<T> setInclination(final Position position) {
	// // x=cos *r , y=sin * r => m=y/x=sin/cos=tan
	// // double m = Math.tan(Math.toRadians(Math.normalizeMod( doubleValue,
	// // 360)));
	// double m = Math.tan(position.getRadians());
	// this.setInclination(m);
	// return this;
	// }

	public CartesianLine() {
		this.inclination = 1.0;
		this.independentTerm = 0.0;
	}

	/**
	 * <p>
	 * Creates a line between the position and the center of reference.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param position
	 * @return
	 */
	public CartesianLine(final CartesianPosition position, MathContext scale) {
		inferLineParameters(position, position.origin().cartesian(), scale);
	}
	public CartesianLine(final CartesianPosition position ) {
		inferLineParameters(position, position.origin().cartesian(), scale);
	}


	public CartesianLine(final CartesianPosition first, final CartesianPosition last, MathContext scale) {
		inferLineParameters(first, last, scale);
	}

	public CartesianLine(final CartesianPosition first, final CartesianPosition last) {
		inferLineParameters(first, last, scale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param inclination
	 * @param indepTerm
	 */
	public CartesianLine(Double inclination, Double indepTerm, MathContext scale) {
		setInclination(inclination);
		setIndependentTerm(indepTerm);
		setScale(scale);
	}

	/**
	 * <p>
	 * The line is defined by the points. The domain of this function is not
	 * defined for points with teta= 45º & teta= -45º at the same time. Or other
	 * non bijective relation.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param first
	 * @param last
	 */
	private void inferLineParameters(final CartesianPosition first, final CartesianPosition last,
			final MathContext scale) {
		// x=cos *r , y=sin * r => m=y/x=sin/cos=tan
		// m=f(x1)-f(x2)/x1-x2 ^ x1 != x2 ^ x1 > x2
		CartesianPosition r = first.sustract(last);
		BigDecimal x = BigDecimal.valueOf(r.getDirectPosition().getOrdinate(0));
		BigDecimal fx = BigDecimal.valueOf(r.getDirectPosition().getOrdinate(1));
		if (MathAccuracy.equals(x, BigDecimal.ZERO, scale)) {
			if (MathAccuracy.equals(fx, BigDecimal.ZERO, scale)) {
				StringBuilder sb = new StringBuilder("The points used are equals.").append("Cartesian:").append("{ ")
						.append(first).append(",").append(last).append(" }");
				throw new IllegalArgumentException(sb.toString());
			}
			StringBuilder sb = new StringBuilder(
					"The x value of both points are equals and y value different. Defines a no bijective function. This points are out of the domain.")
							.append("Cartesian:").append("{ ").append(first).append(",").append(last).append(" }")
							.append("Polar:").append("{ ").append(first.polar()).append(",").append(last.polar())
							.append(" }");

			throw new IllegalArgumentException(sb.toString());

		}

		MathContext doubleScale = MathAccuracy.createScale(scale.getPrecision() + 5, RoundingMode.HALF_EVEN);
		if (scale.getPrecision() + 5 > 15) {
			doubleScale = MathAccuracy.createScale(17, RoundingMode.HALF_EVEN);
		}

		BigDecimal m = fx.divide(x, doubleScale);
		// m.x1 + b=y1=f(x1) => b= f(x1)- m.x1
		BigDecimal b = determineIndependentTerm(first, m).setScale(scale.getPrecision(), scale.getRoundingMode());
		BigDecimal b1 = determineIndependentTerm(last, m).setScale(scale.getPrecision(), scale.getRoundingMode());
		
		
		BigDecimal diff=b.subtract(b1).setScale(doubleScale.getPrecision(), doubleScale.getRoundingMode());
		if (MathAccuracy.notEquals(b, b1, scale)) {
			throw new IllegalArgumentException("The points are not part of the same line.");
		}
		this.setInclination(m.doubleValue());
		this.setIndependentTerm(b.doubleValue());
		this.setScale(scale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param scale2
	 */
	private void setScale(MathContext scale2) {
		this.scale = scale2;
	}

	/**
	 * <p>
	 * // m.x2 + b=y2=f(x2) => b= f(x2)- m.x2
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param position
	 * @param m
	 * @return
	 */
	private static BigDecimal determineIndependentTerm(final CartesianPosition position, BigDecimal m) {
		BigDecimal multiply = m.multiply(BigDecimal.valueOf(position.getDirectPosition().getOrdinate(0)));
		BigDecimal b = BigDecimal.valueOf(position.getDirectPosition().getOrdinate(1));
		return b.subtract(multiply);
	}

	private CartesianLine setInclination(final Double m) {
		this.inclination = m;
		return this;
	}

	protected Double getInclination() {
		return inclination;
	}

	private CartesianLine setIndependentTerm(final Double b) {
		this.independentTerm = b;
		return this;
	}

	public Double getIndepTerm() {
		return independentTerm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public CartesianPosition apply(Double x) {
		CartesianPosition position = Positions.cartesian(x, (inclination * x) + independentTerm);
		return position;
	}

	public boolean contains(CartesianPosition position) {
		Double x = position.getDirectPosition().getOrdinate(0);
		Double y = position.getDirectPosition().getOrdinate(1);

		return y.equals(this.apply(x).getDirectPosition().getOrdinate(1));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inclination == null) ? 0 : inclination.hashCode());
		result = prime * result + ((independentTerm == null) ? 0 : independentTerm.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartesianLine other = (CartesianLine) obj;
		if (inclination == null) {
			if (other.inclination != null)
				return false;
		} else if (!inclination.equals(other.inclination))
			return false;
		if (independentTerm == null) {
			if (other.independentTerm != null)
				return false;
		} else if (!independentTerm.equals(other.independentTerm))
			return false;
		if (scale == null) {
			if (other.scale != null)
				return false;
		} else if (!scale.equals(other.scale))
			return false;
		return true;
	}
}
