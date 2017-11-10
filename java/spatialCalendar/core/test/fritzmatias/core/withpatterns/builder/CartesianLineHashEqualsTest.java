/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.core.withpatterns.builder;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.imp.Positions;
import fritzmatias.utils.MathAccuracy;
import test.HashEqualsTest;

@RunWith(Parameterized.class)
public class CartesianLineHashEqualsTest {
	@Parameter(0)
	public String testName;
	@Parameter(1)
	public Position p1;
	@Parameter(2)
	public Position p2;
	@Parameter(3)
	public Position p3;
	@Parameter(4)
	public Position p4;

	@SuppressWarnings("unused")
	private class BasicTests extends HashEqualsTest<CartesianLine> {

		public BasicTests(CartesianLine o1, CartesianLine o2) {
			super(o1, o2);
		}
	}

	@Parameters(name = "{0}:{1}{2}&{3}{4}")
	public static Collection<Object[]> getData() {
		List<Object[]> c = new ArrayList<Object[]>();
//		c.add(a(Positions.cartesian(0, 0), Positions.cartesian(1, 1), cartesian(2, 2), cartesian(-1, -1)));
//		c.add(a(Positions.cartesian(0.123456789, 0.123456789), Positions.cartesian(1.123456789, 1.123456789),
//				cartesian(2, 2), cartesian(-1, -1)));
//		c.add(a(Positions.cartesian(0, 0), Positions.cartesian(1.123456789, 1.123456789), cartesian(2, 2),
//				cartesian(-1, -1)));
		c.add(a(Positions.polar(1, 1), Positions.polar(10000, 1), Positions.polar(2, 1), Positions.polar(20000, 1)));

		return c;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param createCartesian
	 * @param createCartesian2
	 * @param cartesian
	 * @param cartesian2
	 * @return
	 */
	private static Object[] a(Position a1, Position a2, Position b1, Position b2) {
		Object[] o = new Object[5];
		StringBuilder sb=new StringBuilder();
		o[0] =""; 
		o[1] = a1;
		o[2] = a2;
		o[3] = b1;
		o[4] = b2;
		return o;
	}
	
	@Test
	public void hashCodeTest() {
		CartesianLine l1=new CartesianLine(p1.cartesian(),p2.cartesian(), MathAccuracy.createScale(13, RoundingMode.DOWN));
		CartesianLine l2=new CartesianLine(p3.cartesian(),p4.cartesian());
		
		BasicTests t=new BasicTests(l1, l2);
		t.equalsTest();
		t.hashCodeTest();
	}

}
