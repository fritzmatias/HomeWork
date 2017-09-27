/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.doubl;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * In this class the tests are forced to fail in the normal usage of
 * dobules exploring the concepts "natural" behavior with unexpected
 * results related to data itself or conceptual misunderstanding.<br>
 * The operations tested are addition, subtraction, multiplication and division
 * in they pure form.
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see 
 * https://docs.python.org/3/tutorial/floatingpoint.html
 * https://en.wikipedia.org/wiki/Double-precision_floating-point_format
 */
public class DoubleAccuracyOperationsInvestigationTest {

	@Test
	public void sumDecimalFractionTest() {
		double expected = 0.3;
		double tolerance = Math.pow(0.1, 17); // <17 =>OK
		double n = ((double) 1 / 10);

		double actual = n + n + n;
		Assert.assertEquals(expected, actual, tolerance);
	}
	@Test
	public void sustractionFraction() {
		double expected = 0.1;
		double tolerance = Math.pow(0.1, 17); // <17 =>OK
		double n = ((double) 1 / 10);

		double actual = 0.3 - n - n;
		Assert.assertEquals(expected, actual, tolerance);
	}

	@Test
	public void multiplicationFraction() {
		double decimal = 0.1;
		double centecimal = 0.01;
		double tolerance = Math.pow(0.1, 18); // <18 => OK

		double actual = decimal * decimal;
		Assert.assertEquals(centecimal, actual, tolerance);
	}
	@Test
	public void divideFraction1() {
		double decimal = (double) 1/10;
		double tolerance = Math.pow(0.1, 18); // <16 => OK

		double actual = decimal/(1/10); // Because the divisor is implicit casted to int. -> 0.1/0
		Assert.assertEquals(1.0, actual, tolerance);
	}
	@Test
	public void divideFraction2() {
		double decimal = (double)1/3;
		double centecimal = (double)2/3;
		double tolerance = Math.pow(0.1, 18);

		double actual = decimal/centecimal;
		Assert.assertEquals(0.5, actual, tolerance); // true
	}
	@Test
	public void divideFraction03_01() {
		double decimal = (double)1/10;
		double fraction = (double)1/3;
		double tolerance = Math.pow(0.1, 17); // <17 => OK

		
		double actual = decimal/fraction;
		Assert.assertEquals(0.3, actual, tolerance); // false
	}
	@Test
	public void divideFraction03_02() {
		double decimal = (double)1/10;
		double fraction = (double)1/7;
		double tolerance = Math.pow(0.1, 16); // <16 => OK

		
		double actual = decimal/fraction;
		Assert.assertEquals(0.7, actual, tolerance); // false
	}
	@Test
	public void divideFraction03_03() {
		double decimal = (double)1/10;
		double fraction = (double)1/11;
		double tolerance = Math.pow(0.1, 18); // <17 => OK

		
		double actual = decimal/fraction;
		Assert.assertEquals(1.1, actual, tolerance); // true
	}
	@Test
	public void divideFraction03_04() {
		double decimal = (double)1/10;
		double fraction = (double)1/13;
		double tolerance = Math.pow(0.1, 18); // <17 => OK

		
		double actual = decimal/fraction;
		Assert.assertEquals(1.3, actual, tolerance); // true
	}
	
}
