/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.evaluation;

import java.math.BigDecimal;
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
public class BigDecimalAccuracyEvaluationEqualsInvestigationTest {
	@Test
	public void equlasNegativeZeroStaticZero() {
		BigDecimal a = BigDecimal.ZERO;
		BigDecimal b = BigDecimal.valueOf(-0.0);

		Assert.assertEquals(a, b); // fail
	}

	@Test
	public void equalsPositiveZeroNegativeZeroDouble() {
		BigDecimal positive = BigDecimal.valueOf(+0.0);
		BigDecimal negative = BigDecimal.valueOf(-0.0);

		Assert.assertEquals(positive, negative); // true
	}

	@Test
	public void equalsIrrationalFractionDoubleBigDecimal() {
		BigDecimal dble = BigDecimal.valueOf((double) 3 / 7);
		BigDecimal bd = BigDecimal.valueOf(3).divide(BigDecimal.valueOf(7), 17, RoundingMode.DOWN);

		Assert.assertEquals(bd, dble); // fail
	}

	@Test
	public void equalsIrrationalFractionScaleTruncatedToSame() {
		BigDecimal expected = BigDecimal.valueOf((double) 1 / 3).multiply(BigDecimal.valueOf(3)); // scale
																									// 16
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		BigDecimal n = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), 15, RoundingMode.HALF_EVEN); // scale
																										// 15
		BigDecimal actual = n.add(n).add(n); // tricky operation

		int scale = Math.min(expected.scale(), actual.scale());
		BigDecimal bd = expected.setScale(scale - 1, RoundingMode.HALF_UP);
		BigDecimal bd1 = actual.setScale(scale - 1, RoundingMode.HALF_UP);
		Assert.assertEquals(bd1, bd); // OK - because the scale is the same
	}

	@Test
	public void equalsPositiveNegativeCalculatedZero01() {
		BigDecimal positive = BigDecimal.valueOf(+0.3);
		BigDecimal negative = BigDecimal.valueOf(0.3 * (-1));

		Assert.assertEquals(positive, negative.negate()); // True
		Assert.assertEquals(positive.add(negative), positive.add(negative).negate()); // true
		Assert.assertEquals(negative.add(positive), positive.add(negative).negate()); // true
		Assert.assertEquals(negative.subtract(positive.negate()), positive.add(negative).negate()); // true
	}

	@Test
	public void equalsPositiveNegativeCalculatedZero02() {
		BigDecimal positive = BigDecimal.valueOf((double) 1 / 6);
		BigDecimal negative = BigDecimal.valueOf((double) 3 / (6 * (-3)));// -1/6

		Assert.assertEquals(positive.add(negative), positive.add(negative).negate()); // true
		Assert.assertEquals(negative.add(positive), positive.add(negative).negate()); // true
		Assert.assertEquals(negative.subtract(positive.negate()), positive.add(negative).negate()); // true
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @see {@link #multiplication02()} {@link #sumOperation01()}
	 */
	@Test
	public void equalsDifferentScaleStripTrailingZeros() {
		BigDecimal d = BigDecimal.valueOf(0.12345);
		BigDecimal expected = BigDecimal.valueOf(12345);

		BigDecimal actual = d.multiply(BigDecimal.valueOf(100000)).stripTrailingZeros();
		Assert.assertEquals(expected, actual); // true
	}

}
