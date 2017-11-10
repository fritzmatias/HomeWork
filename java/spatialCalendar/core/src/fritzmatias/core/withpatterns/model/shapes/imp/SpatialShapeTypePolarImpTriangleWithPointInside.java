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
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialShapeTypePolarImpTriangleWithPointInside extends SpatialShapeType {

	public SpatialShapeTypePolarImpTriangleWithPointInside() {
		super("lluvia");
	}

	@Override
	public boolean test(SpatialShapeContext context) {
		Position min = context.getPositions().values().stream()
				.min((a, b) -> (int) (v(a) - v(b)))
				.get();

		/*
		 * EL modulo determina si se cumple el mismo patron de distancia entre los puntos.
		 * */
		Map<Position, Double> m = context.getPositions().values().stream().filter(p->(v(p) % v(min)) == 0)
				.collect(Collectors.toMap(Function.identity(),
						p -> (v(p)-v(min)) ));

		if(false) {
			return true;
		}
		return false;
		
	}
	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param min
	 * @return
	 */
	protected double v(Position min) {
		return min.getDirectPosition().getOrdinate(0);
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

		double perimeter=a.distance(b)+b.distance(c)+c.distance(a);
		positions.setPerimeter(perimeter);
		return positions;
	}

	@Override
	public SpatialSystemStateType getType() {
		return SpatialSystemStateType.TrianguloEncierraOrigen;
	}

}
