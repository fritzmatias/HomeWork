/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.investigation.accuracy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * http://www.dma.fi.upm.es/personal/mabellanas/tfcs/kirkpatrick/Aplicacion/algoritmos.htm
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class ApacheLinearAlgebraAccuracyInvestigationTest {
	Vector2D ab = new Vector2D(0, 1), AB = new Vector2D(0, 5), rab = new Vector2D(0.1, 0.9);
	Vector2D bc = new Vector2D(1, 1), BC = new Vector2D(5, 5);
	Vector2D ca = new Vector2D(1, 0), CA = new Vector2D(5, 0), rca = new Vector2D(0.9, 0.1);
	Vector2D ad = new Vector2D(0, 2);
	Vector2D ae = new Vector2D(0, 3);
	Vector2D af = new Vector2D(0, 4);
	Vector2D center = new Vector2D(0, 0);
	double tolerance = Math.pow(0.1, 15);

	@Test
	public void testCrossProduct() {

		Assert.assertEquals("inverse point comparasion", -1 * ab.crossProduct(ca, bc), ab.crossProduct(bc, ca),
				tolerance);// cambio de posicion
		Assert.assertEquals("90 degrees +", -1.0d, center.crossProduct(ab, ca), tolerance); // 90º
		Assert.assertEquals("90 degrees -", 1.0d, center.crossProduct(ca, ab), tolerance); // 90º
		Assert.assertEquals("< 90 degrees +", -0.8d, center.crossProduct(rab, rca), tolerance); // 90º
		Assert.assertEquals("< 90 degrees -", 0.8d, center.crossProduct(rca, rab), tolerance); // 90º
		Assert.assertEquals("90 degrees big circle +", 25.0d, center.crossProduct(CA, AB), tolerance); // 90º
		Assert.assertEquals("90 degrees big circle -", -25.0d, BC.crossProduct(CA, AB), tolerance); // 90º
		Assert.assertEquals("90 degrees big circle normalized +", 1.0d,
				center.crossProduct(CA, AB) / (CA.getNorm() * AB.getNorm()), tolerance); // 90º
		Assert.assertEquals("parallel vectors", 0.0d, ca.crossProduct(ae, ca), tolerance); // parallelos
		Assert.assertTrue(ca.dotProduct(ae) == 0);

	}

	@Test
	public void testLine() {
		Line l = new Line(ad, ae, tolerance);

		Assert.assertTrue(l.contains(ab));
		Assert.assertTrue(l.contains(center));
		Assert.assertTrue(l.contains(af));
		Assert.assertFalse(l.contains(bc));

	}

	@Test
	public void testcrossProductTolerance() {
		int r = 10000;
		for (int angdeg = 0; angdeg < 360; angdeg++) {
			int m = 1;
			int scale = (int) Math.log10(r);
			Vector2D v1 = new Vector2D(m * r * Math.cos(Math.toRadians(angdeg)),
					m * r * Math.sin(Math.toRadians(angdeg)));
			m = 3;
			Vector2D v2 = new Vector2D(m * r * Math.cos(Math.toRadians(angdeg)),
					m * r * Math.sin(Math.toRadians(angdeg)));

			m = 5;
			Vector2D v3 = new Vector2D(m * r * Math.cos(Math.toRadians(angdeg)),
					m * r * Math.sin(Math.toRadians(angdeg)));
			System.out.println("AngDeg:" + angdeg + " scale:" + scale + " Direct:" + v1.crossProduct(v2, v3) + " my:"
					+ crossProduct(scale, v1, v2, v3));

			Assert.assertTrue(crossProduct(v1, v2, v3).compareTo(BigDecimal.ZERO)==0);
			Assert.assertTrue(crossProduct(scale,v1, v2, v3)==0);
//			Assert.assertEquals(0L, v1.crossProduct(v2, v3), tolerance);
			// Assert.assertFalse(v1.crossProduct(v2, v3) == 0);
		}
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @param tolerance2
	 * @since Version: 1
	 * @param v1
	 * @param v2
	 * @param v3
	 * @return
	 */
	public double crossProduct(int scale, Vector2D v1, Vector2D v2, Vector2D v3) {
		return BigDecimal.valueOf(v1.crossProduct(v2, v3)).setScale(scale, RoundingMode.DOWN).doubleValue();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @param tolerance2
	 * @since Version: 1
	 * @param v1
	 * @param v2
	 * @param v3
	 * @return
	 */
	public BigDecimal crossProduct(Vector2D v1, Vector2D v2, Vector2D v3) {
		double d1 = v1.getX();
		double d2 = v2.getX();
		double d3= v3.getX();
		
		int xPresicion=Math.max(maxPresicion(d1, d2), maxPresicion(d2, d3));
		d1 = v1.getY();
		d2 = v2.getY();
		d3= v3.getY();
		int yPresicion=Math.max(maxPresicion(d1, d2), maxPresicion(d2, d3));
		int precision=Math.max(xPresicion, yPresicion);
		
		System.out.println("xpresicion: "+xPresicion+ ", ypresicion: "+yPresicion);
		return BigDecimal.valueOf(v1.crossProduct(v2, v3)).setScale(precision, RoundingMode.DOWN);
	}
	
	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param d1
	 * @param d2
	 * @return
	 */
	private int maxPresicion(double d1, double d2) {
		return Math.max(BigDecimal.valueOf(d1).precision(), BigDecimal.valueOf(d2).precision());
	}

}
