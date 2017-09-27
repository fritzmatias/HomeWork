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
public class BigDecimalAccuracyReductionByScaleInvestigationTest {

	@Test
	public void reductionAndInflationByScale() {
		BigDecimal actual = BigDecimal.valueOf(0.12345);
		BigDecimal expected = BigDecimal.valueOf(0.1234);

		int scale=Math.min(expected.scale(), actual.scale());
		// explicit reduction
		BigDecimal scaleReduction = actual.setScale(scale, RoundingMode.DOWN);
		Assert.assertEquals(expected.precision(), scaleReduction.precision());
		Assert.assertEquals(expected.scale(), scaleReduction.scale());

		Assert.assertEquals(expected, scaleReduction); // true
		Assert.assertEquals(scaleReduction.unscaledValue(), actual.unscaledValue()); // Fail
	}

	@Test
	public void equalityOfReduction() {
		BigDecimal actual = BigDecimal.valueOf(0.12345);  // scale of 5, precision of 6
		BigDecimal expected = BigDecimal.valueOf(0.1234); // scale of 4, precision of 5

		int scale=Math.min(expected.scale(), actual.scale());
		// explicit reduction
		BigDecimal scaleReduction = actual.setScale(scale, RoundingMode.DOWN);
		Assert.assertEquals(expected.precision(), scaleReduction.precision()); // true
		Assert.assertEquals(expected.scale(), scaleReduction.scale()); // true

		Assert.assertEquals(expected, scaleReduction); // true
		Assert.assertEquals(expected.unscaledValue(), scaleReduction.unscaledValue()); // true
	}

	@Test
	public void doubleZeroScaleToIntegerZero() {
		BigDecimal actual = BigDecimal.valueOf(0.0);  // scale of 5, precision of 6
		BigDecimal expected = BigDecimal.ZERO; // scale of 4, precision of 5

		int scale=Math.min(expected.scale(), actual.scale());
		Assert.assertEquals(0, scale);
		// explicit reduction
		BigDecimal scaleReduction = actual.setScale(scale, RoundingMode.DOWN);
		Assert.assertEquals(expected.precision(), scaleReduction.precision()); // true
		Assert.assertEquals(expected.scale(), scaleReduction.scale()); // true
		Assert.assertEquals(expected, scaleReduction); // true
	}

}
