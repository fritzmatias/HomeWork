/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.reduction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public class BigDecimalAccuracyReductionByRoundInvestigationTest {


	@Test
	public void roundScaleNegative() {
		BigDecimal expected = BigDecimal.valueOf(0.0);
		BigDecimal centecimal = BigDecimal.valueOf(0.01);

		// The scale, on round can't be negative
		BigDecimal actual = centecimal.round(new MathContext(-1, RoundingMode.DOWN)); // Exception
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void precisionTwoScaleThreeRound() {
		BigDecimal expected = BigDecimal.valueOf(0.0);
		BigDecimal centecimal = BigDecimal.valueOf(0.011);

		Assert.assertEquals(3, centecimal.scale()); // true
		Assert.assertEquals(2, centecimal.precision()); // true
		int precision=Math.min(expected.precision(), centecimal.precision());
		BigDecimal actual = centecimal.round(new MathContext(precision, RoundingMode.FLOOR));
		
		Assert.assertEquals(centecimal.precision(), actual.precision()); // true
		Assert.assertEquals(centecimal.scale(), actual.scale()); // true
		// The precision of 1, don't reduce the value.
		Assert.assertEquals(expected, actual); //  fail
	}

	@Test
	public void precisionOneScaleTwoRound() {
		BigDecimal expected = BigDecimal.valueOf(0.0);
		BigDecimal centecimal = BigDecimal.valueOf(0.01);

		Assert.assertEquals(2, centecimal.scale()); // true
		Assert.assertEquals(1, centecimal.precision()); // true
		int precision=Math.min(expected.precision(), centecimal.precision());
		BigDecimal actual = centecimal.round(new MathContext(precision, RoundingMode.FLOOR));
		
		Assert.assertEquals(centecimal.precision(), actual.precision()); // true
		Assert.assertEquals(centecimal.scale(), actual.scale()); // true
		// The precision of 1, don't reduce the value.
		Assert.assertEquals(expected, actual); //  fail
	}

	@Test
	public void doubleZeroRoundToIntegerZero() {
		BigDecimal actual = BigDecimal.valueOf(0.0);  // scale of 5, precision of 6
		BigDecimal expected = BigDecimal.ZERO; // scale of 4, precision of 5

		int precision=Math.min(expected.precision(), actual.precision());
		Assert.assertEquals(1, precision);

		BigDecimal roundReduction = actual.round(new MathContext(precision,RoundingMode.DOWN));
		// The scale no change.
		Assert.assertEquals("Scale", expected.scale(), actual.scale()); // scale
		Assert.assertEquals("Value", expected, roundReduction); // false
	}

	@Test
	public void rationalFractionRoundEquals() {
		BigDecimal actual = BigDecimal.valueOf(0.12345);  // scale of 5, precision of 6
		BigDecimal expected = BigDecimal.valueOf(0.1234); // scale of 4, precision of 5

		int precision=Math.min(expected.precision(), actual.precision()); 
		// explicit reduction
		BigDecimal roundReduction = actual.round(new MathContext(precision, RoundingMode.DOWN));
		Assert.assertEquals(expected.precision(), roundReduction.precision()); // true
		Assert.assertEquals(expected.scale(), roundReduction.scale()); // true

		Assert.assertEquals(expected, roundReduction); // true
	}


}
