/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.shapes.imp;

import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.utils.MathAccuracy;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public final class SpatialShapeTypeCartesianImpLineWithPointOutside extends SpatialShapeType {

	public SpatialShapeTypeCartesianImpLineWithPointOutside() {
		super("optimo");

	}

	@Override
	public boolean test(SpatialShapeContext context) {
		Position a = context.getPosition(0);
		Position b = context.getPosition(1);
		Position c = context.getPosition(2);
		Position p = context.getPoint();

		// ab x ac == 0 -> Linea && pa x pb != 0 no alineados (pc es implicito)
		return MathAccuracy.equals(a.crossProduct(b, c), 0, context.getScale())
				&& MathAccuracy.notEquals(p.crossProduct(a, b), 0, context.getScale());
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

	@Override
	public SpatialSystemStateType getType() {
		return SpatialSystemStateType.RectaSinPunto;
	}

}
