/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigationTest;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.Fraction;

import org.junit.Test;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class ApacheFraction {

	@Test
	public void test() {
		double tolerance=Math.pow(0.1, 17);
		Fraction a=new Fraction(0.25);
		Fraction irrational=new Fraction(1).divide(3);
		assertEquals(0.25 ,a.doubleValue() , tolerance);
		assertEquals(1d/3, irrational.doubleValue() ,tolerance);
		assertEquals(1d, irrational.multiply(3).doubleValue() ,tolerance);
	}
}
