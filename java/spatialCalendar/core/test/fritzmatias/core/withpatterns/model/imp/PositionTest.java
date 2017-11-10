/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.PolarPosition;
import fritzmatias.core.withpatterns.model.Position;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 2
 */
@RunWith(Parameterized.class)
public class PositionTest {
	@Parameter(0)
	public String testName;
	@Parameter(1)
	public Position position;
	@Parameter(2)
	public double expectedCrossProduct;
	public Position position2 = SpatialFactoryImp.createCartesianPosition(1, 0);
	public Position position3 = SpatialFactoryImp.createCartesianPosition(0, 1);
	static long maxdistance = 100000000;
	private static double presicion = Math.pow(0.1, 15 - (Math.log10(maxdistance)));

	@Parameters(name = "{0}")
	public static Collection<Object[]> getData() {
		System.out.print("MaxPresicion: " + presicion);
		Collection<Object[]> list = new ArrayList<Object[]>();
		for (long i = 1000; i <= maxdistance; i = i * 100) {
			list.add(cartesian("", i * 0, 1, 0));
			list.add(cartesian("", i * 1, 0, 0));
			list.add(cartesian("", i * 4, 3, -6));
			list.add(cartesian("", i * 3, 4, -6));
			list.add(cartesian("", i * 1, 1, -1));
			list.add(cartesian("", i * 1.3333333, 1.3333333, i * -1.6666666));
			list.add(cartesian("RR:" + i, i * 0, i * 1, 0));
			list.add(cartesian("RR:" + i, i * 1, i * 0, 0));
			list.add(cartesian("RR:" + i, i * 4, i * 3, i * -6));
			list.add(cartesian("RR:" + i, i * 3, i * 4, i * -6));
			list.add(cartesian("RR:" + i, i * 1, i * 1, i * -1));
			list.add(cartesian("RR:" + i, i * 1.3333333, i * 1.3333333, i * -1.6666666));

			list.add(polar("", i * 0, 1, 0));
			list.add(polar("", i * 0, 1, 0));
			list.add(polar("", i * 1, 0, 0));
			list.add(polar("", i * 4, 3, -6));
			list.add(polar("", i * 3, 4, -6));
			list.add(polar("", i * 1, 1, -1));
			list.add(polar("", i * 1.3333333, 1.3333333, i * -1.6666666));
			list.add(polar("RR:" + i, i * 0, i * 1, 0));
			list.add(polar("RR:" + i, i * 1, i * 0, 0));
			list.add(polar("RR:" + i, i * 4, i * 3, i * -6));
			list.add(polar("RR:" + i, i * 3, i * 4, i * -6));
			list.add(polar("RR:" + i, i * 1, i * 1, i * -1));
			list.add(polar("RR:" + i, i * 1.3333333, i * 1.3333333, i * -1.6666666));

		}

		return list;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @param polarPosition2DImp
	 * @param string
	 * @since Version: 1
	 * @return
	 */
	private static Object[] cartesian(String string, double x, double y, double cross) {
		CartesianPosition2DImp c = new CartesianPosition2DImp(x, y);
		return new Object[] { c.toString() + " " + string, c, cross };

	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @param polarPosition2DImp
	 * @param string
	 * @since Version: 1
	 * @return
	 */
	private static Object[] polar(String string, double x, double y, double cross) {
		PolarPosition c = new CartesianPosition2DImp(x, y).polar();
		return new Object[] { c.toString() + " " + string, c, cross };

	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#cartesian()}.
	 */
	@Test
	public void testCartesian() {
		CartesianPosition direct = position.cartesian();
		CartesianPosition converted = position.polar().cartesian().polar().cartesian();
		assertEquals(0, direct.getDirectPosition().getOrdinate(0) - converted.getDirectPosition().getOrdinate(0),
				presicion);
		assertEquals(0, direct.getDirectPosition().getOrdinate(1) - converted.getDirectPosition().getOrdinate(1),
				presicion);
	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#getDegrees()}.
	 */
	@Test
	public void testGetDegrees() {
		Position direct = position.polar();
		Position converted = position.polar().cartesian().polar();
		assertEquals(0, direct.getDirectPosition().getOrdinate(0) - converted.getDirectPosition().getOrdinate(0),
				presicion);
		assertEquals(0, direct.getDirectPosition().getOrdinate(1) - converted.getDirectPosition().getOrdinate(1),
				presicion);
	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#getRadians()}.
	 */
	@Test
	public void testGetRadians() {
		PolarPosition natural = position.polar();
		PolarPosition converted = position.polar().cartesian().polar();
		assertEquals(0, natural.getRadians() - converted.getRadians(), presicion);
	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#getRadio()}.
	 */
	@Test
	public void testGetRadio() {
		PolarPosition natural = position.polar();
		PolarPosition converted = position.polar().cartesian().polar();
		assertEquals(0, natural.getRadio() - converted.getRadio(), presicion);
	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#polar()}.
	 */
	@Test
	public void testPolar() {
		PolarPosition natural = position.polar();
		PolarPosition converted = position.polar().cartesian().polar();

		assertEquals(natural.getDirectPosition().getOrdinate(0), converted.getDirectPosition().getOrdinate(0),
				presicion);
		assertEquals(natural.getDirectPosition().getOrdinate(1), converted.getDirectPosition().getOrdinate(1),
				presicion);

	}

	@Test
	public void testTransform() {
		PolarPosition p = position.polar();
		CartesianPosition c = position.cartesian();

		assertEquals(p, Positions.cartesian(c.getDirectPosition().getOrdinate(0), c.getDirectPosition().getOrdinate(1))
				.polar());
		double r = p.getDirectPosition().getOrdinate(0);
		double t = p.getDirectPosition().getOrdinate(1);
		CartesianPosition pc=Positions.polar(r, t).cartesian();
		assertEquals(c, pc);
		assertEquals(c, Positions.polar(p.getDirectPosition().getOrdinate(0), p.getDirectPosition().getOrdinate(1))
				.cartesian());
	}
	
	@Test
	public void testTransformA() {
		PolarPosition p = position.polar();
		CartesianPosition c = position.cartesian();
//		PolarPosition p = Positions.cartesian(1000.0, 0.0).polar();
//		CartesianPosition c = Positions.cartesian(1000.0, 0.0);
		p.cartesian();
		assertEquals(p, Positions.cartesian(c.getDirectPosition().getOrdinate(0), c.getDirectPosition().getOrdinate(1))
				.polar());
		BigDecimal r = BigDecimal.valueOf(p.getDirectPosition().getOrdinate(0)).setScale(20, RoundingMode.DOWN);
		BigDecimal t = BigDecimal.valueOf(p.getDirectPosition().getOrdinate(1)).setScale(20, RoundingMode.DOWN);
		Double d=new Double(1.0);

		long lVal=d.longValue();
		int scale=0;
		for(long div=10; ((long)(lVal/div) > 0); scale++) {
			div*=div;
		}
		System.out.println(scale);
		CartesianPosition pc=Positions.polar(r.doubleValue(), t.doubleValue()).cartesian();
		BigDecimal x = BigDecimal.valueOf(pc.getDirectPosition().getOrdinate(0)).setScale(11, RoundingMode.HALF_EVEN);
		BigDecimal y = BigDecimal.valueOf(pc.getDirectPosition().getOrdinate(1)).setScale(11, RoundingMode.HALF_EVEN);
		pc=Positions.cartesian(x.doubleValue(), y.doubleValue());
		assertEquals(c, pc);
//		assertEquals(c, Positions.polar(p.getDirectPosition().getOrdinate(0), p.getDirectPosition().getOrdinate(1))
//				.cartesian());
	}	

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#sustractDegrees(fritzmatias.core.withpatterns.model.Position)}.
	 */
	@Test
	public void testSustract() {
		PolarPosition point = SpatialFactoryImp.createPolarPosition(0, 360);
		PolarPosition polar = position.polar();

		PolarPosition polarCalculated = polar.sustractDegrees(point);
		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);
		assertEquals(polar.getDirectPosition().getOrdinate(1), polarCalculated.getDirectPosition().getOrdinate(1),
				presicion);

		polarCalculated = polar.sustractDegrees(360);
		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);
		assertEquals(polar.getDirectPosition().getOrdinate(1), polarCalculated.getDirectPosition().getOrdinate(1),
				presicion);

		polarCalculated = polar.sustractDegrees(-25);
		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);
		assertTrue(polarCalculated.getDirectPosition().getOrdinate(1) >= 0);

		polarCalculated = polar.sustractDegrees(24.145);
		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);
		assertTrue(polarCalculated.getDirectPosition().getOrdinate(1) >= 0);

		CartesianPosition cartesian = position.cartesian();
		CartesianPosition cartesianCalculated = cartesian.sustract(cartesian);

		assertEquals(0, cartesianCalculated.getDirectPosition().getOrdinate(0), presicion);
		assertEquals(0, cartesianCalculated.getDirectPosition().getOrdinate(1), presicion);
	}

	@Test
	public void testPrecision() {
		assertTrue(presicion < 0.00001);
	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#addDegrees(fritzmatias.core.withpatterns.model.Position)}.
	 */
	@Test
	public void testAppend() {
		PolarPosition point = new PolarPosition2DImp(0, 360);
		PolarPosition polar = position.polar();
		PolarPosition polarCalculated = polar.addDegrees(point);

		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);
		assertEquals(polar.getDirectPosition().getOrdinate(1), polarCalculated.getDirectPosition().getOrdinate(1),
				presicion);

		polarCalculated = polar.addDegrees(360);
		assertEquals(polar.getDirectPosition().getOrdinate(1), polarCalculated.getDirectPosition().getOrdinate(1),
				presicion);
		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);

		polarCalculated = polar.addDegrees(-25);
		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);
		assertTrue(polarCalculated.getDirectPosition().getOrdinate(1) >= 0);

		polarCalculated = polar.addDegrees(24.145);
		assertEquals(polar.getDirectPosition().getOrdinate(0), polarCalculated.getDirectPosition().getOrdinate(0),
				presicion);
		assertTrue(polarCalculated.getDirectPosition().getOrdinate(1) >= 0);

		// Conmutability
		polarCalculated = SpatialFactoryImp.createPolarPosition(0, 360);
		assertEquals(polar.addDegrees(point).getDegrees(), point.addDegrees(polar).getDegrees(), presicion);
		polarCalculated = SpatialFactoryImp.createPolarPosition(0, 0);
		assertEquals(polar.addDegrees(point).getDegrees(), point.addDegrees(polar).getDegrees(), presicion);
		polarCalculated = SpatialFactoryImp.createPolarPosition(0, -25);
		assertEquals(polar.addDegrees(point).getDegrees(), point.addDegrees(polar).getDegrees(), presicion);
		polarCalculated = SpatialFactoryImp.createPolarPosition(0, 24.145);
		assertEquals(polar.addDegrees(point).getDegrees(), point.addDegrees(polar).getDegrees(), presicion);
		polarCalculated = SpatialFactoryImp.createPolarPosition(0, -24.145);
		assertEquals(polar.addDegrees(point).getDegrees(), point.addDegrees(polar).getDegrees(), presicion);

		CartesianPosition cartesian = position.cartesian();
		CartesianPosition cartesianCalculated = cartesian.append(cartesian);

		assertEquals(2 * cartesian.getDirectPosition().getOrdinate(0),
				cartesianCalculated.getDirectPosition().getOrdinate(0), presicion);
		assertEquals(2 * cartesian.getDirectPosition().getOrdinate(1),
				cartesianCalculated.getDirectPosition().getOrdinate(1), presicion);

	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#crossProduct(fritzmatias.core.withpatterns.model.Position, fritzmatias.core.withpatterns.model.Position)}.
	 */
	// @Test
	public void testCrossProductPositionPosition() {
		Position natural = position.cartesian();
		assertEquals(expectedCrossProduct, natural.crossProduct(position2, position3), presicion);
		assertEquals(expectedCrossProduct, natural.crossProduct(position2, position3), presicion);
	}

	/**
	 * Test method for
	 * {@link fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp#distance(fritzmatias.core.withpatterns.model.Position)}.
	 */
	@Test
	public void testDistancePosition() {
		assertEquals(position2.cartesian().distance(position), position.cartesian().distance(position2), presicion);
		assertEquals(position2.polar().distance(position), position.polar().distance(position2), presicion);
		assertEquals(position2.cartesian().distance(position), position.polar().distance(position2), presicion);

		PolarPosition polar = position.polar();
		// Conmutability
		PolarPosition point = SpatialFactoryImp.createPolarPosition(0, 360);
		assertEquals(polar.distance(point), point.distance(polar), presicion);
		point = SpatialFactoryImp.createPolarPosition(0, 0);
		assertEquals(polar.distance(point), point.distance(polar), presicion);
		point = SpatialFactoryImp.createPolarPosition(0, -25);
		assertEquals(polar.distance(point), point.distance(polar), presicion);
		point = SpatialFactoryImp.createPolarPosition(0, 24.145);
		assertEquals(polar.distance(point), point.distance(polar), presicion);
		point = SpatialFactoryImp.createPolarPosition(0, -24.145);
		assertEquals(polar.distance(point), point.distance(polar), presicion);

	}

	@Test
	public void testEqualsAndHashCode() {
		PolarPosition polar = position.polar();
		CartesianPosition cartesian = position.cartesian();

		Assert.assertEquals(polar, cartesian.polar());
		Assert.assertEquals(cartesian, polar.cartesian());
		Assert.assertEquals(position.polar(), polar); // different objects

		// hashCode should be equlas if 2obj are equals
		Assert.assertEquals(polar.hashCode(), cartesian.polar().hashCode());
		Assert.assertEquals(cartesian.hashCode(), polar.cartesian().hashCode());
		Assert.assertNotEquals(polar.hashCode(), cartesian.hashCode());
	}
}
