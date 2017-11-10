/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fritzmatias.utils.Math;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public class MathTest {
	double x = 4;
	double y=3;
	double precision = java.lang.Math.pow(0.1, 15);

	@Test
	public void sinAsinTest() {
		assertEquals(Math.toRadians(x), Math.asin(Math.sin(Math.toRadians(x))), precision);
		assertEquals(Math.sin(Math.toRadians(x)), Math.sin(Math.asin(Math.sin(Math.toRadians(x)))), precision);
	}

	@Test
	public void cosAcosTest() {
		assertEquals(Math.toRadians(x), Math.acos(Math.cos(Math.toRadians(x))), precision); // fail, precision > 15, because of pi
		assertEquals(Math.cos(Math.toRadians(x)), Math.cos(Math.acos(Math.cos(Math.toRadians(x)))), precision);
		x=x*1000000;
		assertEquals(Math.cos(Math.toRadians(x)), Math.cos(Math.acos(Math.cos(Math.toRadians(x)))), precision);
	}

	@Test
	public void trigonometricIdentities() {
		double radians=Math.toRadians(x*10000);
		assertEquals(Math.cos(radians)*Math.cos(radians), 1-(Math.sin(radians)*Math.sin(radians)),precision); // fail, precision > 15, because of pi
		assertEquals(Math.sin(radians)*Math.sin(radians), 1-(Math.cos(radians)*Math.cos(radians)),precision);
	}
	@Test
	public void degreesRadiansTest() {
		assertEquals(x, Math.toDegrees(Math.toRadians(x)), precision);
		assertEquals(Math.toDegrees(x), Math.toDegrees(Math.toRadians(Math.toDegrees(x))), precision);
	}
	@Test
	public void radiansDegreesTest() {
		assertEquals(x, Math.toRadians(Math.toDegrees(x)), precision);
		assertEquals(Math.toRadians(x), Math.toRadians(Math.toDegrees(Math.toRadians(x))), precision);
	}

	@Test
	public void radiansAtan2Test() {
		double r=Math.sqrt(x*x+y*y);
		double teta = Math.atan2(y,x);
		assertEquals(x,r* Math.cos(teta), precision);
		assertEquals(y,r* Math.sin(teta), precision);
	}	
	
	@Test
	public void normalizeModTest() {
		assertTrue((3.1 == Math.normalizeMod(3.1, 360)));
	}
}
