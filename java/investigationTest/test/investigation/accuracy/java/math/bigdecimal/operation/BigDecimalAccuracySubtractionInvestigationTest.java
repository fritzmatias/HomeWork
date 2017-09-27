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
public class BigDecimalAccuracySubtractionInvestigationTest {
	@Test
	public void subtractionFractionSameValueSameScale() {
		BigDecimal expected = BigDecimal.valueOf(0.1);
		BigDecimal n = BigDecimal.valueOf(((double) 1 / 10));

		BigDecimal actual = BigDecimal.valueOf(0.3).subtract(n).subtract(n);
		Assert.assertEquals("Object Equals", expected, actual); // true
	}
	@Test
	public void subtractionFractionSameValueSameScaleDoubleBigDecimal() {
		BigDecimal expected = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(10));
		BigDecimal n = BigDecimal.valueOf(((double) 1 / 10));

		BigDecimal actual = BigDecimal.valueOf(0.3).subtract(n).subtract(n);
		Assert.assertEquals("Object Equals", expected, actual); // true
	}
	
	@Test
	public void subtractIrrationalFractionDifferentScalesEquality() {
		BigDecimal expected = BigDecimal.valueOf((double)1/3).multiply(BigDecimal.valueOf(3)); // scale 16
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		BigDecimal n = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), 15, RoundingMode.HALF_EVEN); // scale 15
		BigDecimal actual = n.add(n).add(n); // tricky operation

		Assert.assertEquals("3 sums of 1/3 compared to 1", expected, expected.subtract(actual)); // fail - because 0.33+0.33+0.33 != 1
	}
	@Test
	public void subtractIrrationalFractionDifferentScalesCompare() {
		BigDecimal expected = BigDecimal.valueOf((double)1/3).multiply(BigDecimal.valueOf(3)); // scale 16
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		BigDecimal n = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), 15, RoundingMode.HALF_EVEN); // scale 15
		BigDecimal actual = n.add(n).add(n); // tricky operation

		Assert.assertEquals("3 sums of 1/3 compared to 1", expected.compareTo(expected.subtract(actual))); // fail - because 0.33+0.33+0.33 != 1
	}
}
