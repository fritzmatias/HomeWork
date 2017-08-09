/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.investigation;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.stateless.processors.spatial.SpatialShapeCalculator;

import org.junit.Assert;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RunWith(Parameterized.class)
public class ShapeSolutionTest {
	@Parameter(0)
	public String testName;
	@Parameter(1)
	public Vector2D A;
	@Parameter(2)
	public Vector2D B;
	@Parameter(3)
	public Vector2D C;
	@Parameter(4)
	public SpatialSystemStateType expected;
	final static public Vector2D p = new Vector2D(0, 0);

	@Parameters(name = "{0}")
	public static Collection<Object[]> getData() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		// Triangulo Vacio
		data.add(new Object[] { "1- point O outside vectors oriented", new Vector2D(0, 1), new Vector2D(2, 2),
				new Vector2D(3, 0), SpatialSystemStateType.TrianguloVacio });
		data.add(new Object[] { "2- point O outside vectors unsorted", new Vector2D(0, 1), new Vector2D(3, 0),
				new Vector2D(2, 2), SpatialSystemStateType.TrianguloVacio });
		data.add(new Object[] { "3- point O outside vectors oriented", new Vector2D(2, 2), new Vector2D(0, 1),
				new Vector2D(3, 0), SpatialSystemStateType.TrianguloVacio });

		// Triangulo encierra elemento
		data.add(new Object[] { "1- triangle O inside vectors oriented", new Vector2D(0, 1), new Vector2D(2, -2),
				new Vector2D(-3, 0), SpatialSystemStateType.TrianguloEncierraOrigen });
		data.add(new Object[] { "2- triangle O inside vectors unsorted", new Vector2D(0, 1), new Vector2D(-3, 0),
				new Vector2D(2, -2), SpatialSystemStateType.TrianguloEncierraOrigen });
		data.add(new Object[] { "3- triangle O inside vectors oriented", new Vector2D(2, -2), new Vector2D(0, 1),
				new Vector2D(-3, 0), SpatialSystemStateType.TrianguloEncierraOrigen });

		// Rectas
		data.add(new Object[] { "1-streight O inside vectors unsorted", new Vector2D(0, 3), new Vector2D(0, 1),
				new Vector2D(0, 2), SpatialSystemStateType.RectaConPunto });
		data.add(new Object[] { "2-streight O inside vectors unsorted", new Vector2D(0, 1), new Vector2D(0, 2),
				new Vector2D(0, 3), SpatialSystemStateType.RectaConPunto });
		data.add(new Object[] { "3-streight O inside vectors unsorted", new Vector2D(0, 1), new Vector2D(0, 3),
				new Vector2D(0, 2), SpatialSystemStateType.RectaConPunto });
		data.add(new Object[] { "vertical streight O inside vectors unsorted", new Vector2D(-0, -300),
				new Vector2D(0, 2000), new Vector2D(0, 1000), SpatialSystemStateType.RectaConPunto });
		data.add(new Object[] { "steight O inside vectors oriented", new Vector2D(0, 1), new Vector2D(0, 2),
				new Vector2D(0, 3), SpatialSystemStateType.RectaConPunto });
		data.add(new Object[] { "steight O inside vectors oriented", new Vector2D(1, 1), new Vector2D(1, 2),
				new Vector2D(1, 3), SpatialSystemStateType.RectaSinPunto });
		data.add(new Object[] { "vertical streight x=1  unsorted", new Vector2D(1, 1), new Vector2D(1, 3),
				new Vector2D(1, 2), SpatialSystemStateType.RectaSinPunto });
		data.add(new Object[] { "horizontal streight y=1  unsorted", new Vector2D(1, 1), new Vector2D(31, 1),
				new Vector2D(-1, 1), SpatialSystemStateType.RectaSinPunto });
		data.add(new Object[] { "daigonal+ streight y=ax+1  unsorted", new Vector2D(1, 2), new Vector2D(0, 1),
				new Vector2D(-1, 0), SpatialSystemStateType.RectaSinPunto });
		data.add(new Object[] { "daigonal- streight y=-ax-1  unsorted", new Vector2D(1, -2), new Vector2D(0, -1),
				new Vector2D(-1, 0), SpatialSystemStateType.RectaSinPunto });

		// Si el punto (0,0) es parte de las aristas del trìàngulo o un vertice.
		data.add(new Object[] { "point p overload one arist on the triangle", new Vector2D(0, 0), new Vector2D(2, 2),
				new Vector2D(3, 0), SpatialSystemStateType.TrianguloEncierraOrigen });
		data.add(new Object[] { "point p part of an arist (y=x) on the triangle parallel2", new Vector2D(-1, -1),
				new Vector2D(2, 2), new Vector2D(-3, 3), SpatialSystemStateType.TrianguloEncierraOrigen });
		data.add(new Object[] { "point O part of streight unsorted", new Vector2D(0, 1), new Vector2D(0, 3),
				new Vector2D(0, 2), SpatialSystemStateType.RectaConPunto });

		return data;
	}

	/**
	 * <p>
	 * point inside triangle sg(ABC) = sg(ABP) = sg(BCP) = sg(CAP)
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	@Test
	public void test() {
		boolean includeVertices = false;

		SpatialSystemStateType actual = SpatialSystemStateType.indefinido;
		double abc = A.crossProduct(B, C);
		double sgabc = Math.signum(abc);
		if (abc == 0) {
			if (0 == p.crossProduct(A, B)) {
				actual = SpatialSystemStateType.RectaConPunto;
			} else {
				actual = SpatialSystemStateType.RectaSinPunto;
			}
		} else {
			double segmentA = A.crossProduct(B, p);
			double segmentB = B.crossProduct(C, p);
			double segmentC = C.crossProduct(A, p);

			// (segmentA*segmentB*segmentC==0) corresponde a si el punto
			// pertenece a las aristas del triangulo
			if ((includeVertices && segmentA * segmentB * segmentC == 0) || sgabc == Math.signum(segmentA)
					&& sgabc == Math.signum(segmentB) && sgabc == Math.signum(segmentC)) {
				actual = SpatialSystemStateType.TrianguloEncierraOrigen;
			} else {
				actual = SpatialSystemStateType.TrianguloVacio;
			}
		}

		Assert.assertEquals(testName, expected, actual);
	}

	/**
	 * <p>
	 * This test will be ok depending of the data and the criteria if the
	 * vertices or aristas are part or not of the triangle.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	@Test
	public void solutionTestRigthOrderTriangleAllPonints() {
		SpatialShapeCalculator f = new SpatialShapeCalculator(true);
		SpatialSystemStateType actual = f.apply(p, new Vector2D[] { A, B, C });
		Assert.assertEquals(testName, expected, actual);
	}

	@Test
	public void solutionTestExpectedToFailBecuaseExclutionOfAristsInTriangle() {
		SpatialShapeCalculator f = new SpatialShapeCalculator(false);
		SpatialSystemStateType actual = f.apply(p, new Vector2D[] { A, B, C });
		Assert.assertEquals(testName, expected, actual);
	}

	/**
	 * <p>
	 * This test will fail because the order of the points in the vector.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	@Test
	public void solutionTestExpectedToFailBecauseVectorOrder() {
		SpatialShapeCalculator f = new SpatialShapeCalculator();
		SpatialSystemStateType actual = f.apply(p, new Vector2D[] { A, C, B });
		Assert.assertEquals(testName, expected, actual);
	}
}
