/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.investigation;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import org.junit.Test;

import fritzmatias.core.imp.SpatialCalendarBuilderImp2;
import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.imp.SpatialShapeTypeParametrizedTest;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialShapeExclusiveTypeCalculator;
import fritzmatias.patterns.type.TypeManager;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialShapeCalculatorInvestigationTest extends SpatialShapeTypeParametrizedTest {

	@Test
	public void test() {

		TypeManager<SpatialShapeType> manager = SpatialFactoryImp.createShapeManager();
		Collection<SpatialShapeType> list = SpatialCalendarBuilderImp2.knownAlgebraicShapeTypes();

		manager.register(list);
		 SpatialShapeExclusiveTypeCalculator calculator = SpatialFactoryImp.shapeClasificatorBuilder()
				.setManager(manager).build();

		Position point = SpatialFactoryImp.createCartesianPosition(0, 0);
		SpatialShapeContext data = SpatialFactoryImp.stateSupplier().get();
		data.setPoint(point);
		data.setIncludeVertices(true);
		data.addPosition(0, super.position0);
		data.addPosition(1, super.position1);
		data.addPosition(2, super.position2);

		SpatialShapeType actual = calculator.apply(data);

		assertEquals(super.expectedType, actual);
	}

	@Test
	public void testOldVersion() {

		BiFunction<Vector2D, Vector2D[], SpatialSystemStateType> calculator = SpatialObjectFactory
				.createShapeCalculator(true);

		Position point = SpatialFactoryImp.createCartesianPosition(0, 0);

		SpatialSystemStateType actual = calculator.apply(v(point),
				new Vector2D[] { v(super.position0), v(super.position1), v(super.position2) });

		assertEquals(super.expectedType.getFormalStatusName().toLowerCase(),
				actual.getFormalStatusName().toLowerCase());
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param point
	 * @return
	 */
	private Vector2D v(Position point) {
		return SpatialObjectFactory.createVector2D(point.getDirectPosition().getOrdinate(0),
				point.getDirectPosition().getOrdinate(1));
	}

}
