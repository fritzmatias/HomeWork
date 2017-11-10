/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.PolarPosition;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.utils.Math;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class PolarPosition2DImp extends AbstractTransformedPosition2DImp implements PolarPosition {
	
	public final static PolarPosition ORIGIN=CartesianPosition2DImp.ORIGIN.polar();

	public PolarPosition2DImp() {
		super(0, 0);
	}

	public PolarPosition2DImp(double radio, double angDegree) {
		super(radio, normalizeDegree(angDegree));

	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param originalPosition
	 * @param radio1
	 * @param degrees
	 */
	protected PolarPosition2DImp(Position originalPosition, double radio1, double degrees) {
		super(originalPosition, radio1, degrees);
	}

	public static PolarPosition create(double radio, double angDegree) {
		return new PolarPosition2DImp(radio, angDegree);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param angDegree
	 * @return a positive value x where 0<= x < 360
	 */
	protected static double normalizeDegree(double degree) {
		return Math.normalizeMod(degree, 360);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	private static final long serialVersionUID = -21717233686647618L;

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
		CartesianPosition t = this.cartesian();

		return t.crossProduct(A, B);
	}

	@Override
	public double distance(Position position) {
		// https://es.wikipedia.org/wiki/Teorema_del_coseno
		PolarPosition p = position.polar();
		double degrees = 0;
		degrees = angleDifference(p);

		double ra = this.getRadio();
		double rb = p.getRadio();
		double result = ra * ra + rb * rb - 2 * ra * rb * Math.cos(Math.toRadians(degrees));
		return Math.sqrt(result);
	}

	/**
	 * <p>
	 * The difference in angle between the points.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param p
	 * @return
	 */
	public double angleDifference(PolarPosition p) {
		double degrees;
		if (this.getDegrees() > p.getDegrees()) {
			degrees = this.sustractDegrees(p).getDegrees();
		} else {
			degrees = p.sustractDegrees(this).getDegrees();
		}
		return degrees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.Position#cartesian()
	 */
	@Override
	public CartesianPosition cartesian() {
		if(super.getOriginalPosition() instanceof CartesianPosition) {
			return (CartesianPosition) super.getOriginalPosition();
		}
		return transformTocartesian();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.PolarPosition#getDegrees()
	 */
	@Override
	public double getDegrees() {
		return super.getY();
	}

	protected CartesianPosition transformTocartesian() {
		double x = this.getRadio() * Math.cos(this.getRadians());
		double y = this.getRadio() * Math.sin(this.getRadians());
		return new CartesianPosition2DImp(this, x, y);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.PolarPosition#getRadians()
	 */
	@Override
	public double getRadians() {
		return Math.toRadians(super.getY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.PolarPosition#getRadio()
	 */
	@Override
	public double getRadio() {
		return super.getX();
		// return super.getDirectPosition().getOrdinate(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.Position#polar()
	 */
	@Override
	public PolarPosition polar() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.Position#sustract(fritzmatias.core.
	 * withpatterns.model.Position)
	 */
	@Override
	public PolarPosition sustractDegrees(PolarPosition sustractor) {
		PolarPosition p = sustractor;
		return this.sustractDegrees(p.getDegrees());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.Position#append(fritzmatias.core.
	 * withpatterns.model.Position)
	 */
	@Override
	public PolarPosition addDegrees(PolarPosition appender) {
		PolarPosition p = appender;
		return addDegrees(p.getDegrees());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.PolarPosition#sustractDegrees(double)
	 */
	@Override
	public PolarPosition sustractDegrees(double sustractor) {
		return create(this.getRadio(), this.getDegrees() + normalizeDegree(sustractor));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.PolarPosition#addDegrees(double)
	 */
	@Override
	public PolarPosition addDegrees(double appender) {
		return create(this.getRadio(), this.getDegrees() + normalizeDegree(appender));
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
		StringBuilder sb=new StringBuilder("Polar"); 
		sb.append("(").append(this.getX()).append(",").append(this.getY()).append(")");	
		return sb.toString();
	}
}
