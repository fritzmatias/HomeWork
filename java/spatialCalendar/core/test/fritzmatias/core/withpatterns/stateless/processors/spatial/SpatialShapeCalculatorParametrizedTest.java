/**
s * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.stateless.processors.spatial;

import static org.junit.Assert.assertEquals;

import java.math.RoundingMode;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import fritzmatias.core.imp.SpatialCalendarBuilderImp2;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.imp.SpatialShapeTypeParametrizedTest;
import fritzmatias.investigation.SpatialShapeCalculatorInvestigationTest;
import fritzmatias.patterns.type.TypeManager;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * The file {@link SpatialShapeCalculatorInvestigationTest} contains test for
 * the old implementation with errors. Keep only for documentation.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 * @see {@link SpatialShapeCalculatorInvestigationTest}
 */
public class SpatialShapeCalculatorParametrizedTest extends SpatialShapeTypeParametrizedTest {

	static private TypeManager manager;
	static Collection<SpatialShapeType> shapes=SpatialCalendarBuilderImp2.knownAlgebraicShapeTypes2();
	
	@BeforeClass
	public static void setUp() {
		manager = SpatialFactoryImp.createShapeManager();
		manager.register(shapes);
	}

	@Test
	public void test() {

		 SpatialShapeExclusiveTypeCalculator calculator = SpatialFactoryImp.shapeClasificatorBuilder().setManager(manager).build();

		Position point = SpatialFactoryImp.createCartesianPosition(0, 0);
		SpatialShapeContext data = SpatialFactoryImp.stateSupplier().get();
		data.setPoint(point);
		data.setIncludeVertices(true);
		data.addPosition(0, super.position0);
		data.addPosition(1, super.position1);
		data.addPosition(2, super.position2);
		data.setScale(MathAccuracy.createScale(13, RoundingMode.DOWN));

		SpatialShapeType actual = calculator.apply(data);

		assertEquals(super.expectedType, actual);
	}

}
