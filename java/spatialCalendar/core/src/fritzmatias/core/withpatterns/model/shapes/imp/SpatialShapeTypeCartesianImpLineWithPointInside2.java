/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.shapes.imp;

import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public final class SpatialShapeTypeCartesianImpLineWithPointInside2 extends SpatialShapeType {

	public SpatialShapeTypeCartesianImpLineWithPointInside2() {
		super("sequia");
	}

	@Override
	public boolean test(SpatialShapeContext context) {
		ImmutableCartesianPointShapeHelper helper = new ImmutableCartesianPointShapeHelper(context.getScale(),
				context.getPoint(), context.getPosition(0), context.getPosition(1), context.getPosition(2));
		
		// ab x ac == 0 -> Linea && pa x pb == 0 alineados (pc es implicito)
		return helper.isALine() && helper.isTheEvaluationPointAlignedWithASide();
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
		return SpatialSystemStateType.RectaConPunto;
	}

}
