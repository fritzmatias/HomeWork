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
public class BigDecimalAccuracyDivisionInvestigationTest {

	@Test
	public void divideDecimalFractionByItselif() {
		BigDecimal decimal = BigDecimal.valueOf((double) 1 / 10);

		BigDecimal actual = decimal.divide(decimal);
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(1.0), actual); // false
	}

	@Test
	public void divideDoubleIrrationalFractionsRationalResult() {
		BigDecimal decimal = BigDecimal.valueOf((double) 1 / 3);
		BigDecimal centecimal = BigDecimal.valueOf((double) 2 / 3);

		BigDecimal actual = decimal.divide(centecimal); // true
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.5), actual); // true
	}

	/**
	 * <p>
	 * the scale construction problem is fixed by the explcit setting of the
	 * scale.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void divideBigDecimalIrrationalFractionsRationalResult() {
		BigDecimal decimal = BigDecimal.valueOf(1).setScale(22).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);
		BigDecimal centecimal = BigDecimal.valueOf(2).setScale(22).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);

		BigDecimal actual = decimal.divide(centecimal, 5, RoundingMode.DOWN); // true
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.5), actual.stripTrailingZeros()); // true
	}

	/**
	 * <p>
	 * the scale construction problem is not fixed and then, an unexpected
	 * division error is introduced. Shown by the division By Zero Exception
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void divideBigDecimalIrrationalFractionsRationalResultScaleProblem() {
		BigDecimal decimal = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);
		BigDecimal centecimal = BigDecimal.valueOf(2).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);

		BigDecimal actual = decimal.divide(centecimal, RoundingMode.DOWN); // true
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.5), actual.stripTrailingZeros()); // true
	}

	@Test
	public void divideFractionExceptionSalvable() {
		BigDecimal decimal = BigDecimal.valueOf((double) 1 / 10);
		BigDecimal fraction = BigDecimal.valueOf((double) 1 / 3);

		BigDecimal actual;
		try {
			actual = decimal.divide(fraction); // Exception
		} catch (ArithmeticException e) {
			actual = decimal.divide(fraction, RoundingMode.DOWN); // fix the
																	// exception
		}
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.3), actual);
	}

	/**
	 * <p>
	 * The scale problem is hidden by the double cronstruction
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void divideDoubleMixedFractionsExceptionCatched() {
		BigDecimal decimal = BigDecimal.valueOf((double) 1 / 10);
		BigDecimal fraction = BigDecimal.valueOf((double) 1 / 3);

		BigDecimal actual;
		try {
			actual = decimal.divide(fraction); // Exception
		} catch (ArithmeticException e) {
			actual = decimal.divide(fraction, RoundingMode.DOWN); // fix the //
																	// exception
		}
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.3), actual);
	}

	@Test
	public void divideDoubleIrrationalFractionOfIrrationalsKeepingPrecision() {
		BigDecimal decimal = BigDecimal.valueOf((double) 1 / 7);
		BigDecimal fraction = BigDecimal.valueOf((double) 1 / 3);

		BigDecimal actual;
		BigDecimal expected = BigDecimal.valueOf((double) 3 / 7);
		try {
			actual = decimal.divide(fraction); // Exception
		} catch (ArithmeticException e) {
			actual = decimal.divide(fraction, RoundingMode.DOWN); // fix the
																	// exception
		}

		Assert.assertEquals("Object Equals", expected, actual);
		Assert.assertEquals(0, expected.compareTo(actual));
	}

}
