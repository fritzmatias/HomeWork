/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.shapes.imp;

import java.math.MathContext;

import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.utils.Math;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 * @see {@link SpatialShapeType}
 */
public final class ImmutableCartesianPointShapeHelper {

	final private MathContext accuracyScale;
	final private Position pointA;
	final private Position pointB;
	final private Position pointC;
	final private Position evaluatedPoint;
	final private boolean includeVertices;

	private double crossProductABC;
	private double crossProductBCA;
	private double crossProductCAB;
	private double signOfABC;
	private double crossProductCAP;
	private double crossProductBCP;
	private double crossProductABP;

	public ImmutableCartesianPointShapeHelper(MathContext accuracyScale, Position toEvaluate, Position pointA,
			Position pointB, Position pointC) {
		this.accuracyScale = accuracyScale;
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		this.evaluatedPoint = toEvaluate;
		this.includeVertices = true;

		cacheCalculations(accuracyScale, pointA, pointB, pointC);
	}

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param accuracyScale
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 */
	private void cacheCalculations(MathContext accuracyScale, Position pointA, Position pointB, Position pointC) {
		Position evaluatedPoint=this.evaluatedPoint;
		crossProductABP = crossProduct(accuracyScale, pointA, pointB, evaluatedPoint);
		crossProductBCP = crossProduct(accuracyScale, pointB, pointC, evaluatedPoint);
		crossProductCAP = crossProduct(accuracyScale, pointC, pointA, evaluatedPoint);

		crossProductABC = crossProduct(accuracyScale, pointA, pointB, pointC);
		crossProductBCA = crossProduct(accuracyScale, pointB, pointC, pointA);
		crossProductCAB = crossProduct(accuracyScale, pointC, pointA, pointB);
		signOfABC = Math.signum(crossProductABC);
	}

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param accuracyScale
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return
	 */
	private double crossProduct(MathContext accuracyScale, Position pointA, Position pointB, Position pointC) {
		return MathAccuracy.round(pointA.crossProduct(pointB, pointC), accuracyScale);
	}

	public ImmutableCartesianPointShapeHelper(SpatialShapeContext context) {
		this.accuracyScale = context.getScale();
		pointA = context.getPosition(0);
		pointB = context.getPosition(1);
		pointC = context.getPosition(2);
		evaluatedPoint = context.getPoint();
		includeVertices=context.getIncludeVertices();

		cacheCalculations(accuracyScale, pointA, pointB, pointC);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	public final boolean isFrontier() {
		return isFrontier(evaluatedPoint, pointA, pointB, includeVertices, accuracyScale)
				|| isFrontier(evaluatedPoint, pointB, pointC, includeVertices, accuracyScale)
				|| isFrontier(evaluatedPoint, pointC, pointA, includeVertices, accuracyScale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param accuracyScale
	 * @param segmentA
	 * @param segmentB
	 * @param segmentC
	 * @return
	 */
	public final boolean isTheEvaluationPointAlignedWithASide() {
		return MathAccuracy.equals(crossProductABP, 0, accuracyScale)
				|| MathAccuracy.equals(crossProductBCP, 0, accuracyScale)
				|| MathAccuracy.equals(crossProductCAP, 0, accuracyScale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	public final boolean isInternalPoint() {
		return MathAccuracy.equals(signOfABC, Math.signum(crossProductABP), accuracyScale)
				&& MathAccuracy.equals(signOfABC, Math.signum(crossProductBCP), accuracyScale)
				&& MathAccuracy.equals(signOfABC, Math.signum(crossProductCAP), accuracyScale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param accuracyScale
	 * @param crossProductABC
	 * @param bca
	 * @param cab
	 * @return
	 */
	public final boolean isTriangle() {
		return MathAccuracy.notEquals(crossProductABC, 0, accuracyScale)
				|| MathAccuracy.notEquals(crossProductBCA, 0, accuracyScale)
				|| MathAccuracy.notEquals(crossProductCAB, 0, accuracyScale);
	}

	public final boolean isALine() {
		return MathAccuracy.equals(crossProductABC, 0, accuracyScale);
	}
	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param p
	 *            the point to be evaluated
	 * @param pointA
	 * @param pointB
	 * @param includeVertices
	 */
	private final boolean isFrontier(Position p, Position pointA, Position pointB, boolean includeVertices,
			MathContext scale) {
		if (MathAccuracy.equals(pointA.crossProduct(pointB, p), 0, scale)) {
			// alineado con AB
			if (MathAccuracy.lessThanEquals(p.distance(pointA) + p.distance(pointB), pointA.distance(pointB), scale)) {
				// La distancia normalizada, entre PA+PB <= AB => punto
				// sobre la arista perteneciente al segmento AB
				return includeVertices;
			} else {
				// La distancia normalizada, entre PA+PB > AB => punto
				// paralelo a la arista, pero fuera de los puntos
				// fronteras.
				return false;
			}
		}

		// no està alineado con AB
		return false;
	}

	private final MathContext getAccuracyScale() {
		return accuracyScale;
	}

	private final Position getPointA() {
		return pointA;
	}

	private final Position getPointB() {
		return pointB;
	}

	private final Position getPointC() {
		return pointC;
	}

	private final Position getEvaluatedPoint() {
		return evaluatedPoint;
	}

	private final boolean isIncludeVertices() {
		return includeVertices;
	}

	private final double getCrossProductABC() {
		return crossProductABC;
	}

	private final double getCrossProductBCA() {
		return crossProductBCA;
	}

	private final double getCrossProductCAB() {
		return crossProductCAB;
	}

	private final double getSignOfABC() {
		return signOfABC;
	}

	private final double getCrossProductCAP() {
		return crossProductCAP;
	}

	private final double getCrossProductBCP() {
		return crossProductBCP;
	}

	private final double getCrossProductABP() {
		return crossProductABP;
	}


}
