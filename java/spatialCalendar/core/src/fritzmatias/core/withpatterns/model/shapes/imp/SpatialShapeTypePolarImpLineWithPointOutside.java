/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.shapes.imp;

import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.withpatterns.model.PolarPosition;
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
public class SpatialShapeTypePolarImpLineWithPointOutside extends SpatialShapeType {

	public SpatialShapeTypePolarImpLineWithPointOutside() {
		super("optimo");

	}

	@Override
	public boolean test(SpatialShapeContext context) {

		PolarPosition minDegree = context.getPositions().values().stream().map(p -> p.polar())
				.min((a, b) -> MathAccuracy.compare(a.getDegrees(), b.getDegrees(), context.getScale())).get();
		
		NavigableSet<Position> distances = new TreeSet<Position>(
				(a, b) -> MathAccuracy.compare(a.polar().getRadio(), b.polar().getRadio(), context.getScale()));
		
		distances.addAll(context.getPositions().values());
		PolarPosition far = (PolarPosition) distances.pollFirst();
		PolarPosition near = (PolarPosition) distances.pollLast();


	
		Map<PolarPosition, Boolean> m = context.getPositions().values().stream().map(p -> p.polar())
				/*Puntos que no pertenecen a una recta que pasa por el 0 y el min*/
				.filter(p->(((int)p.sustractDegrees(minDegree).polar().getDegrees()) % 180) != 0)
				.collect(Collectors.toMap(Function.identity(),
						p -> MathAccuracy.equals(p.getDegrees(), near.getDegrees(), context.getScale())
								|| MathAccuracy.equals(p.getDegrees() - near.getDegrees(), 180, context.getScale())));

		throw new RuntimeException("Not Implemented.");
		/*
		 * EL modulo determina si se cumple el mismo patron de distancia entre
		 * los puntos. 1- filtro las superposiciones que dan lugar a rectas con
		 * el origen. 2- calculo las distancias cuya diff != 180 (180 => recta
		 * por el origen)
		 */
//		m = context.getPositions().values().stream().map(p -> p.polar())
//				.filter(p -> MathAccuracy.notEquals(p.getDegrees(), near.getDegrees(), context.getScale())
//						&& MathAccuracy.notEquals(p.getDegrees() - near.getDegrees(), 180, context.getScale()))
//				.collect(Collectors.toMap(Function.identity(),
//						p -> MathAccuracy.equals((int) (p.getDegrees() % near.getDegrees()), 0, context.getScale())
//								&& MathAccuracy.lessThan(p.getDegrees() - near.getDegrees(), 180, context.getScale())));
//
//		if (m.size() > 1 && m.size() == context.getPositions().size() - 1) {
//			// No Todos los puntos estàn alineados => es una recta que no
//			// incluye al
//			// origen.
//			return true;
//		}
//		return false;
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
