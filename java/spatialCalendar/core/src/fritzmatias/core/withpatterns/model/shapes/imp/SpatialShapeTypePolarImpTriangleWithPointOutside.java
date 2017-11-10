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
public class SpatialShapeTypePolarImpTriangleWithPointOutside extends SpatialShapeType {

	public SpatialShapeTypePolarImpTriangleWithPointOutside() {
		super("normal");
	}

	@Override
	public boolean test(SpatialShapeContext context) {
		Position a = context.getPosition(0);
		Position b = context.getPosition(1);
		Position c = context.getPosition(2);
		Position p = context.getPoint();

		double segmentA = a.crossProduct(b, p);
		double segmentB = b.crossProduct(c, p);
		double segmentC = c.crossProduct(a, p);

		double abc = a.crossProduct(b, c);
		double sgabc = Math.signum(abc);

		if (abc != 0) {
			// es un triangulo.
			if (!((sgabc == Math.signum(segmentA) && sgabc == Math.signum(segmentB)
					&& sgabc == Math.signum(segmentC)))) {
				// ! punto interior.
				if (segmentA * segmentB * segmentC == 0) {
					// alineado con una arista // Puede ser frontera
					return !(isInternalFrontierAligned(p, a, b, context.getIncludeVertices(), context.getScale())
							|| isInternalFrontierAligned(p, b, c, context.getIncludeVertices(), context.getScale())
							|| isInternalFrontierAligned(p, c, a, context.getIncludeVertices(), context.getScale()));
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
