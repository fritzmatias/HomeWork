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
 */
public final class SpatialShapeTypeCartesianImpTriangleWithPointInside extends SpatialShapeType {

	public SpatialShapeTypeCartesianImpTriangleWithPointInside() {
		super("lluvia");
	}

	@Override
	public boolean test(SpatialShapeContext context) {
		Position a = context.getPosition(0);
		Position b = context.getPosition(1);
		Position c = context.getPosition(2);
		Position p = context.getPoint();
		MathContext accuracyScale = context.getScale();

		double normA = MathAccuracy.round(a.crossProduct(b, p), accuracyScale);
		double normB = MathAccuracy.round(b.crossProduct(c, p), accuracyScale);
		double normC = MathAccuracy.round(c.crossProduct(a, p), accuracyScale);

		double abc = MathAccuracy.round(a.crossProduct(b, c), accuracyScale);
		double bca = MathAccuracy.round(b.crossProduct(c, a), accuracyScale);
		double cab = MathAccuracy.round(c.crossProduct(a, b), accuracyScale);
		double sgabc = Math.signum(abc);

		if (MathAccuracy.notEquals(abc, 0, accuracyScale) || MathAccuracy.notEquals(bca, 0, accuracyScale)
				|| MathAccuracy.notEquals(cab, 0, accuracyScale)) {
			// es un triangulo.
			if (MathAccuracy.equals(sgabc, Math.signum(normA), accuracyScale)
					&& MathAccuracy.equals(sgabc, Math.signum(normB), accuracyScale)
					&& MathAccuracy.equals(sgabc, Math.signum(normC), accuracyScale)) {
				// punto interior.
				return true;
			} else if (MathAccuracy.equals(normA, 0, accuracyScale)
					|| MathAccuracy.equals(normB, 0, accuracyScale)
					|| MathAccuracy.equals(normC, 0, accuracyScale)) {
				// alineado con una arista // Puede ser frontera
				return (isInternalFrontierAligned(p, a, b, context.getIncludeVertices(), accuracyScale)
						|| isInternalFrontierAligned(p, b, c, context.getIncludeVertices(), accuracyScale)
						|| isInternalFrontierAligned(p, c, a, context.getIncludeVertices(), accuracyScale));
			}

		}

		return false;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param p
	 * @param a
	 * @param b
	 * @param includeVertices
	 */
	private boolean isInternalFrontierAligned(Position p, Position a, Position b, boolean includeVertices,
			MathContext scale) {
		if (MathAccuracy.equals(a.crossProduct(b, p), 0, scale)) {
			// TODO: revisar criterio.
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
	public SpatialShapeContext apply(SpatialShapeContext positions) {
		Position a = positions.getPosition(0);
		Position b = positions.getPosition(1);
		Position c = positions.getPosition(2);

		double perimeter = a.distance(b) + b.distance(c) + c.distance(a);
		positions.setPerimeter(perimeter);
		return positions;
	}

	@Override
	public SpatialSystemStateType getType() {
		return SpatialSystemStateType.TrianguloEncierraOrigen;
	}

}
