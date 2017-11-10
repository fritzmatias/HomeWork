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
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public final class SpatialShapeTypeCartesianImpTriangleWithPointInside2 extends SpatialShapeType {

	public SpatialShapeTypeCartesianImpTriangleWithPointInside2() {
		super("lluvia");
	}

	@Override
	public boolean test(SpatialShapeContext context) {
		ImmutableCartesianPointShapeHelper helper = new ImmutableCartesianPointShapeHelper(context.getScale(),
				context.getPoint(), context.getPosition(0), context.getPosition(1), context.getPosition(2));

		if (helper.isTriangle()) {
			// es un triangulo.
			if (helper.isInternalPoint()) {
				// punto interior.
				return true;
			} else if (helper.isTheEvaluationPointAlignedWithASide()) {
				// alineado con una arista // Puede ser frontera
				return helper.isFrontier();
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
