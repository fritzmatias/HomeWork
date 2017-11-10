/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.shapes.imp;

import java.math.MathContext;

import fritzmatias.core.model.SpatialSystemStateType;
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
public final class SpatialShapeTypeCartesianImpTriangleWithPointOutside extends SpatialShapeType {

	public SpatialShapeTypeCartesianImpTriangleWithPointOutside() {
		super("normal");
	}

	@Override
	public boolean test(SpatialShapeContext context) {
		MathContext accuracyScale = context.getScale();
		Position a = context.getPosition(0);
		Position b = context.getPosition(1);
		Position c = context.getPosition(2);
		Position p = context.getPoint();

		double normA = MathAccuracy.round(a.crossProduct(b, p), accuracyScale);
		double normB = MathAccuracy.round(b.crossProduct(c, p), accuracyScale);
		double normC = MathAccuracy.round(c.crossProduct(a, p), accuracyScale);

		double abc = MathAccuracy.round(a.crossProduct(b, c), accuracyScale);
		double bca = MathAccuracy.round(b.crossProduct(c, a), accuracyScale);
		double cab = MathAccuracy.round(c.crossProduct(a, b), accuracyScale);
		double sgabc = Math.signum(abc);

		if (isTriangle(accuracyScale, a, b, c)) {
			if (!isInternalPoint(accuracyScale, normA, normB, normC, sgabc)) {
				// ! punto interior. * segmentB * segmentC
				if (isAlignedWithASide(accuracyScale, normA, normB, normC)) {
					// alineado con una arista // Puede ser frontera
					return !isFrontier(context);
				} else {
					// punto exterior sin ser paralelo a una arista.
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param context
	 * @return
	 */
	public boolean isFrontier(SpatialShapeContext context) {
		MathContext accuracyScale = context.getScale();
		Position a = context.getPosition(0);
		Position b = context.getPosition(1);
		Position c = context.getPosition(2);
		Position p = context.getPoint();

		return isFrontier(p, a, b, context.getIncludeVertices(), accuracyScale)
				|| isFrontier(p, b, c, context.getIncludeVertices(), accuracyScale)
				|| isFrontier(p, c, a, context.getIncludeVertices(), accuracyScale);
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
	private boolean isAlignedWithASide(MathContext accuracyScale, double segmentA, double segmentB, double segmentC) {
		return MathAccuracy.equals(segmentA, 0, accuracyScale) || MathAccuracy.equals(segmentB, 0, accuracyScale)
				|| MathAccuracy.equals(segmentC, 0, accuracyScale);
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
	 * @param sgabc
	 * @return
	 */
	private boolean isInternalPoint(MathContext accuracyScale, double segmentA, double segmentB, double segmentC,
			double sgabc) {
		return MathAccuracy.equals(sgabc, Math.signum(segmentA), accuracyScale)
				&& MathAccuracy.equals(sgabc, Math.signum(segmentB), accuracyScale)
				&& MathAccuracy.equals(sgabc, Math.signum(segmentC), accuracyScale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param accuracyScale
	 * @param abc
	 * @param bca
	 * @param cab
	 * @return
	 */
	private boolean isTriangle(MathContext accuracyScale, Position a, Position b, Position c) {
		double abc = MathAccuracy.round(a.crossProduct(b, c), accuracyScale);
		double bca = MathAccuracy.round(b.crossProduct(c, a), accuracyScale);
		double cab = MathAccuracy.round(c.crossProduct(a, b), accuracyScale);

		return MathAccuracy.notEquals(abc, 0, accuracyScale) || MathAccuracy.notEquals(bca, 0, accuracyScale)
				|| MathAccuracy.notEquals(cab, 0, accuracyScale);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param p
	 *            the point to be evaluated
	 * @param a
	 * @param b
	 * @param includeVertices
	 */
	private boolean isFrontier(Position p, Position a, Position b, boolean includeVertices, MathContext scale) {
		if (MathAccuracy.equals(a.crossProduct(b, p), 0, scale)) {
			// TODO: revisar criterio
			// alineado con AB
			if (MathAccuracy.lessThanEquals(p.distance(a) + p.distance(b), a.distance(b), scale)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public SpatialShapeContext apply(SpatialShapeContext t) {

		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.model.SpatialShapeType#getType()
	 */
	@Override
	public SpatialSystemStateType getType() {
		return SpatialSystemStateType.TrianguloVacio;
	}

}
