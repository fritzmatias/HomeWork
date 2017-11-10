/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.shapes.imp;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.withpatterns.model.PolarPosition;
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
public class SpatialShapeTypePolarImpLineWithPointInside extends SpatialShapeType {

	public SpatialShapeTypePolarImpLineWithPointInside() {
		super("sequia");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(SpatialShapeContext context) {

		PolarPosition minDegree = context.getPositions().values().stream().map(p -> p.polar())
				.min((a, b) -> MathAccuracy.compare(a.getDegrees(), b.getDegrees(), context.getScale())).get();

		// todos los puntos con cuya diferencia en grados es 0 o 180, se
		// encuentran alineados con el origen.
//		Map<PolarPosition, Boolean> m = context.getPositions().values().stream().map(p -> p.polar())
//				.collect(Collectors.toMap(Function.identity(),
//						p -> MathAccuracy.equals(minDegree.getDegrees() - p.polar().getDegrees(), 0, context.getScale())
//								|| MathAccuracy.equals(minDegree.getDegrees() - p.polar().getDegrees(), 180,
//										context.getScale())));
		Map<PolarPosition, Boolean> m = context.getPositions().values().stream().map(p->p.polar()).collect(Collectors.toMap(
				Function.identity(),
				p -> (((int)p.sustractDegrees(minDegree).polar().getDegrees()) % 180) == 0));

		if (m.size() == context.getPositions().size()) {
			// Todos los puntos estàn alineados => es una recta que incluye al
			// origen.
			return true;
		}
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

	@Override
	public SpatialSystemStateType getType() {
		return SpatialSystemStateType.RectaConPunto;
	}

}
