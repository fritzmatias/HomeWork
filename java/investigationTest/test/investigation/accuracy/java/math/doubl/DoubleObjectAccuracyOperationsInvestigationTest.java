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
public class DoubleObjectAccuracyOperationsInvestigationTest {

	@Test
	public void sumDecimalFractionTest() {
		Double expected = 0.3;
		Double tolerance = Math.pow(0.1, 17); // <17 =>OK
		Double n = ((new Double( 1) / 10));

		Double actual = n + n + n;
		Assert.assertEquals(expected, actual, tolerance); // fail
	}
	@Test
	public void sustractionFraction() {
		Double expected = 0.1;
		Double tolerance = Math.pow(0.1, 17); // <17 =>OK
		Double n = ((new Double( 1) / 10));

		Double actual = 0.3 - n - n;
		Assert.assertEquals(expected, actual, tolerance); // fail
	}

	@Test
	public void multiplicationFraction() {
		Double decimal = 0.1;
		Double centecimal = 0.01;
		Double tolerance = Math.pow(0.1, 18); // <18 => OK

		Double actual = decimal * decimal;
		Assert.assertEquals(centecimal, actual, tolerance); // fail
	}
	@Test
	public void divideFraction1() {
		Double decimal = (new Double( 1)/10);
		Double tolerance = Math.pow(0.1, 18); // <16 => OK

		Double actual = decimal/(1/10); // Because the divisor is implicit casted to int. -> 0.1/0
		Assert.assertEquals(1.0, actual, tolerance); // fail
	}
	@Test
	public void divideFraction2() {
		Double decimal = (new Double(1)/3);
		Double centecimal = (new Double(2)/3);
		Double tolerance = Math.pow(0.1, 18);

		Double actual = decimal/centecimal;
		Assert.assertEquals(0.5, actual, tolerance); // true
	}
	@Test
	public void divideFraction03_01() {
		Double decimal = (new Double(1)/10);
		Double fraction = (new Double(1)/3);
		Double tolerance = Math.pow(0.1, 17); // <17 => OK

		
		Double actual = decimal/fraction;
		Assert.assertEquals(0.3, actual, tolerance); // false
	}
	@Test
	public void divideFraction03_02() {
		Double decimal = (new Double(1)/10);
		Double fraction = (new Double(1)/7);
		Double tolerance = Math.pow(0.1, 16); // <16 => OK

		
		Double actual = decimal/fraction;
		Assert.assertEquals(0.7, actual, tolerance); // false
	}
	@Test
	public void divideFraction03_03() {
		Double decimal = (new Double(1)/10);
		Double fraction = (new Double(1)/11);
		Double tolerance = Math.pow(0.1, 18); // <17 => OK

		
		Double actual = decimal/fraction;
		Assert.assertEquals(1.1, actual, tolerance); // true
	}
	@Test
	public void divideFraction03_04() {
		Double decimal = (new Double(1)/10);
		Double fraction = (new Double(1)/13);
		Double tolerance = Math.pow(0.1, 18); // <17 => OK

		
		Double actual = decimal/fraction;
		Assert.assertEquals(1.3, actual, tolerance); // true
	}
	
}
