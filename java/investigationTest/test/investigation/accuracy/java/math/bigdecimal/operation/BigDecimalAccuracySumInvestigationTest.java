/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

import investigation.accuracy.java.math.doubl.DoubleAccuracyOperationsInvestigationTest;

/**
 * <p>
 * In this class the tests are forced to fail in the normal usage of
 * {@link BigDecimal}, exploring the concepts "natural" behavior with unexpected
 * results related to data itself or conceptual misunderstanding.<br>
 * The operations tested are addition, subtraction, multiplication and division
 * in they pure form.
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see {@link DoubleAccuracyOperationsInvestigationTest}
 */
public class BigDecimalAccuracySumInvestigationTest {

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void sumOperation01() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal expected = BigDecimal.valueOf(0.3);

		BigDecimal actual = decimal.add(decimal).add(decimal); // true
		Assert.assertEquals(expected, actual); // true
		Assert.assertTrue(actual.doubleValue() == expected.doubleValue()); // true
	}

	@Test
	public void sumOperation02_01() {
		BigDecimal decimal = BigDecimal.valueOf(1.0);
		BigDecimal expected = BigDecimal.valueOf(3);

		BigDecimal actual = decimal.add(decimal).add(decimal);
		Assert.assertTrue(actual.doubleValue() == expected.doubleValue()); // true
		Assert.assertEquals("Object Equals", expected, actual); // false
	}

	@Test
	public void sumOperation02_02() {
		BigDecimal decimal = BigDecimal.valueOf(1.0);
		BigDecimal expected = BigDecimal.valueOf(3);

		BigDecimal actual = decimal.add(decimal).add(decimal);
		Assert.assertTrue(actual.doubleValue() == expected.doubleValue()); // true
		Assert.assertEquals("UnscaledValue", actual.unscaledValue(), expected.unscaledValue()); // false
	}

	@Test
	public void sumOperation02_03() {
		BigDecimal decimal = BigDecimal.valueOf(1.0);
		BigDecimal expected = BigDecimal.valueOf(3);

		BigDecimal actual = decimal.add(decimal).add(decimal);
		Assert.assertTrue(actual.doubleValue() == expected.doubleValue()); // true
		Assert.assertEquals("Scale", actual.scale(), expected.scale()); // false
	}

	@Test
	public void sumOperation02_04() {
		BigDecimal decimal = BigDecimal.valueOf(1.0);
		BigDecimal expected = BigDecimal.valueOf(3);

		BigDecimal actual = decimal.add(decimal).add(decimal);
		Assert.assertTrue(actual.doubleValue() == expected.doubleValue()); // true
		Assert.assertEquals("Precision", actual.precision(), expected.precision()); // false
	}
	
	@Test
	public void sumIrrationalFractionPrecisionProblem() {
		BigDecimal expected = BigDecimal.valueOf(1);
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		BigDecimal n = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_EVEN);
		BigDecimal actual = n.add(n).add(n); // tricky operation

		Assert.assertEquals("3 sums of 1/3 compared to 1", 0, expected.compareTo(actual)); // fail - because 0.33+0.33+0.33 != 1
	}
}
