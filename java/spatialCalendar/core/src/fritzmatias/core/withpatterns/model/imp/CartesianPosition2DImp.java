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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.PolarPosition;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.utils.Math;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class CartesianPosition2DImp extends AbstractTransformedPosition2DImp implements CartesianPosition {
	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 */
	private static final long serialVersionUID = -5644441231363979721L;
	public static final CartesianPosition ORIGIN=create(0.0, 0.0);

	public CartesianPosition2DImp() {
		super(0, 0);
	}

	public CartesianPosition2DImp(double x, double y) {
		super(x, y);
	}

	/**
	 * <p>
	 * This constructor should be called only when a transformation is made over
	 * the original position.
	 * </p>
	 * 
	 * @author matias
	 * @param originalPosition
	 * @since Version: 1
	 * @param x
	 * @param y
	 */
	protected CartesianPosition2DImp(Position originalPosition, double x, double y) {
		super(originalPosition, x, y);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param x
	 * @param y
	 * @return
	 */
	public static CartesianPosition create(double x, double y) {
		return new CartesianPosition2DImp(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.Position#crossProduct(fritzmatias.
	 * core.withpatterns.model.Position,
	 * fritzmatias.core.withpatterns.model.Position)
	 */
	@Override
	public double crossProduct(Position A, Position B) {
		Vector2D t = transform(this.cartesian());
		Vector2D a = transform(A.cartesian());
		Vector2D b = transform(B.cartesian());

		return t.crossProduct(a, b);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 */
	private void trowImmutableException() {
		throw new UnsupportedOperationException("Immutable Object. Use create()");
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param value
	 * @return
	 */
	private MathContext buildMathContextFrom(double value) {
		MathContext context = new MathContext(BigDecimal.valueOf(value).precision(), RoundingMode.HALF_EVEN);
		return context;
	}

	@Override
	public double distance(Position p) {
		CartesianPosition position = p.cartesian();
		return super.distance((Vector2D) position);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param positions
	 * @return
	 */
	protected Vector2D transform(Position p) {
		double x = p.getDirectPosition().getOrdinate(0);
		double y = p.getDirectPosition().getOrdinate(1);
		Vector2D point = SpatialObjectFactory.createVector2D(x, y);
		return point;
	}

	protected PolarPosition transofrmToPolar() {
		// https://en.wikipedia.org/wiki/List_of_common_coordinate_transformations#From_polar_coordinates
		double x = this.getX();
		double y = this.getY();
		double radians = Math.atan2(y, x);
		double radio1 = Math.sqrt(y * y + x * x);
		double degrees = Math.toDegrees(radians);
		PolarPosition polar = new PolarPosition2DImp(this, radio1, degrees);
		return polar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.Position#cartesian()
	 */
	@Override
	public CartesianPosition cartesian() {
		return this;
	}

	@Override
	public PolarPosition polar() {
		if(super.getOriginalPosition() instanceof PolarPosition) {
			return (PolarPosition) super.getOriginalPosition();
		}
		return transofrmToPolar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.Position#sustract(fritzmatias.core.
	 * withpatterns.model.Position)
	 */
	@Override
	public CartesianPosition sustract(CartesianPosition sustractor) {
		CartesianPosition p = sustractor;
		return create(this.getDirectPosition().getOrdinate(0) - p.getDirectPosition().getOrdinate(0),
				this.getDirectPosition().getOrdinate(1) - p.getDirectPosition().getOrdinate(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.Position#append(fritzmatias.core.
	 * withpatterns.model.Position)
	 */
	@Override
	public CartesianPosition append(CartesianPosition appender) {
		CartesianPosition p = appender;
		return create(this.getDirectPosition().getOrdinate(0) + p.getDirectPosition().getOrdinate(0),
				this.getDirectPosition().getOrdinate(1) + p.getDirectPosition().getOrdinate(1));
	}

	/* (non-Javadoc)
	 * @see fritzmatias.core.withpatterns.model.Position#origin()
	 */
	@Override
	public Position origin() {
		return ORIGIN;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder("Cartesian"); 
		sb.append("(").append(this.getX()).append(",").append(this.getY()).append(")");	
		return sb.toString();
	}
}
