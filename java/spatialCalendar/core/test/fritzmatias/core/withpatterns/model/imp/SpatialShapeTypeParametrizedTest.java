/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import static fritzmatias.core.TBuilder.cartesian;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpLineWithPointInside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpLineWithPointOutside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpTriangleWithPointInside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpTriangleWithPointOutside;
/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
@RunWith(Parameterized.class)
public class SpatialShapeTypeParametrizedTest {

	@Parameter(0)
	public SpatialShapeType expectedType;
	@Parameter(1)
	public String name;
	@Parameter(2)
	public Position position0;
	@Parameter(3)
	public Position position1;
	@Parameter(4)
	public Position position2;
	
	
	
	@Parameters(name="{1}")
	public static Collection<Object[]> getData(){
		Collection<Object[]> list=new ArrayList<Object[]>();
		list.add(lin("recta 0,0 y=0 ", cartesian(0, 0), cartesian(2, 0), cartesian(-3, 0)));
		list.add(lin("recta y=0 ", cartesian(1, 0), cartesian(2, 0), cartesian(-3, 0)));
		list.add(lin("recta x=0 ", cartesian(0, 1), cartesian(0, 2), cartesian(0, 3)));
		list.add(lin("recta y=x ", cartesian(1, 1), cartesian(2, 2), cartesian(-3, -3)));
		list.add(lin("recta y=-x", cartesian(1, -1), cartesian(2, -2), cartesian(-3, 3)));

		list.add(lout("recta y=1 ", cartesian(1, 1), cartesian(2, 1), cartesian(-3, 1)));
		list.add(lout("recta x=1 ", cartesian(1, 1), cartesian(1, 2), cartesian(1, 3)));
		list.add(lout("recta y=x+1 ", cartesian(1, 2), cartesian(2, 3), cartesian(-3, -2)));
		list.add(lout("recta y=-x+1", cartesian(1, 0), cartesian(2, -1), cartesian(-3, 4)));
		
		list.add(tin("triangulo punto en (0,0) ", cartesian(0, 0), cartesian(2, 2), cartesian(-3, 0)));
		list.add(tin("triangulo punto sobre y=0 ", cartesian(0, 1), cartesian(0, -2), cartesian(3, 0)));
		list.add(tin("triangulo punto sobre x=0 ", cartesian(1, 0), cartesian(-1, 0), cartesian(0, 1)));
		list.add(tin("triangulo punto sobre y=x ", cartesian(-1, -1), cartesian(2, 2), cartesian(3, -3)));
		list.add(tin("triangulo punto sobre y=-x", cartesian(1, -1), cartesian(-1, 1), cartesian(3, 3)));

		list.add(tout("triangulo small y=1 ", cartesian(1, 1), cartesian(2, 1), cartesian(3, 0)));
		list.add(tout("triangulo big y=1 ", cartesian(1, 1), cartesian(2, 1), cartesian(130, 0)));
		list.add(tout("triangulo alineado x=0 ", cartesian(0, 1), cartesian(0, 2), cartesian(1, 3)));
		list.add(tout("triangulo alineado y=0 ", cartesian(1, 0), cartesian(2, 0), cartesian(3, 2)));
		list.add(tout("triangulo alineado y=0 opuesto", cartesian(-1, 0), cartesian(-2, 0), cartesian(-3, 2)));
		list.add(tout("triangulo alineado x=y", cartesian(1, 1), cartesian(2, 2), cartesian(3, 1)));
		list.add(tout("triangulo !alineado opuesto x=y ", cartesian(1, 1), cartesian(1, 0), cartesian(0, 1)));
		
		return list;
	}
	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
t	 * @since Version: 2
	 * @param string
	 * @param createPosition
	 * @param createPosition2
	 * @param createPosition3
	 * @return
	 */
	protected static Object[] lin(String string, Position createPosition, Position createPosition2,
			Position createPosition3) {
		return new Object[] {new SpatialShapeTypeCartesianImpLineWithPointInside(),string, createPosition, createPosition2, createPosition3};
	}
	protected static Object[] lout(String string, Position createPosition, Position createPosition2,
			Position createPosition3) {
		return new Object[] {new SpatialShapeTypeCartesianImpLineWithPointOutside(),string, createPosition, createPosition2, createPosition3};
	}
	protected static Object[] tin(String string, Position createPosition, Position createPosition2,
			Position createPosition3) {
		return new Object[] {new SpatialShapeTypeCartesianImpTriangleWithPointInside(),string, createPosition, createPosition2, createPosition3};
	}
	protected static Object[] tout(String string, Position createPosition, Position createPosition2,
			Position createPosition3) {
		return new Object[] {new SpatialShapeTypeCartesianImpTriangleWithPointOutside(),string, createPosition, createPosition2, createPosition3};
	}

	@Test
	public void lineWithPointInside() {
		SpatialShapeType type = new SpatialShapeTypeCartesianImpLineWithPointInside();
		SpatialShapeContext positions = SpatialFactoryImp.stateSupplier().get();
		commonTest(type, positions);
	}

	/**
	 * <p>
	 * y=x+1
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 */
	@Test
	public void lineWithOutPointOutside() {
		SpatialShapeType type = new SpatialShapeTypeCartesianImpLineWithPointOutside();
		SpatialShapeContext positions = SpatialFactoryImp.stateSupplier().get();
		commonTest(type, positions);
	}

	@Test
	public void triangleWhithPointInside() {
		SpatialShapeType type = new SpatialShapeTypeCartesianImpTriangleWithPointInside();
		SpatialShapeContext positions = SpatialFactoryImp.stateSupplier().get();
		positions.setIncludeVertices(true);
		commonTest(type, positions);
	}

	@Test
	public void triangleWhithPointOutside() {
		SpatialShapeType type = new SpatialShapeTypeCartesianImpTriangleWithPointOutside();
		SpatialShapeContext positions = SpatialFactoryImp.stateSupplier().get();
		positions.setIncludeVertices(true);
		commonTest(type, positions);
	}

	
	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param type
	 * @param positions
	 */
	private void commonTest(SpatialShapeType type, SpatialShapeContext positions) {
		positions.setPoint(cartesian(0, 0));	
		positions.addPosition(0, position0);
		positions.addPosition(1, position1);
		positions.addPosition(2, position2);

		positions.setScale(new MathContext(12, RoundingMode.DOWN));
		if(type.equals(expectedType)) {
			// Testing expected values
			assertTrue(type.test(positions));
		}else {
			// Testing false negatives
			assertFalse(type.test(positions));
		}
	}

}
