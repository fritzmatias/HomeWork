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
import org.junit.runner.RunWith;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class BigDecimalAccuracyEvaluationCompareToInvestigationTest {
	@Test
	public void comparePositiveZeroEqualsNegativeZero() {
		BigDecimal a = BigDecimal.valueOf(+0.0);
		BigDecimal b = BigDecimal.valueOf(0.0).negate();

		Assert.assertEquals(0, b.compareTo(a)); // OK
	}

	@Test
	public void compareNegativeZeroLessThanPositiveZero() {
		BigDecimal a = BigDecimal.valueOf(0.0).negate();
		BigDecimal b = BigDecimal.valueOf(+0.0);

		Assert.assertEquals(-1, a.compareTo(b)); // OK
	}

	@Test
	public void compareNegativeZeroGratherThanPositiveZero() {
		BigDecimal a = BigDecimal.valueOf(0.0).negate();
		BigDecimal b = BigDecimal.valueOf(0.0);

		Assert.assertEquals(1, a.compareTo(b)); // OK
	}

	@Test
	public void compareEqualsFloatingPointZeroIntegerZero() {
		BigDecimal a = BigDecimal.valueOf(0.0);
		BigDecimal b = BigDecimal.valueOf(-0);

		Assert.assertEquals(0, a.compareTo(b)); // OK
	}

	@Test
	public void compareIntegerZeroEqualsFloatingPointZero() {
		BigDecimal a = BigDecimal.valueOf(0);
		BigDecimal b = BigDecimal.valueOf(-0.0);

		Assert.assertEquals(0, a.compareTo(b)); // OK
	}

	/**
	 * <p>
	 * Different behavior between {@link BigDecimal.ZERO} and floating point
	 * values using {@link #equals(Object)}
	 * {@link BigDecimal.#compareTo(BigDecimal)}<br>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @see {link #equlasPositiveNegative0To0_01()}
	 *      {@link #compareEqualsStaticZeroFloatingPointZero()}
	 */
	@Test
	public void compareEqualsStaticZeroFloatingPointZero() {
		BigDecimal a = BigDecimal.ZERO;
		BigDecimal b = BigDecimal.valueOf(-0.0);

		Assert.assertEquals(BigDecimal.ZERO, BigDecimal.valueOf(0));
		Assert.assertEquals(0, a.compareTo(b)); // OK -- Different from equals !
		Assert.assertEquals(0, b.compareTo(a)); // OK -- Different from equals !
	}
	

	@Test
	public void compareIrrationalFractionDoubleBigDecimal() {
		BigDecimal dble = BigDecimal.valueOf((double)3/7);
		BigDecimal bd=BigDecimal.valueOf(3).divide(BigDecimal.valueOf(7), 17, RoundingMode.HALF_EVEN);
		
		Assert.assertEquals(0, bd.compareTo(dble));
	}
	
	@Test
	public void compareIrrationalFractionsDifferentScales() {
		BigDecimal expected = BigDecimal.valueOf((double)1/3).multiply(BigDecimal.valueOf(3)); // scale 16
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		BigDecimal n = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), 17, RoundingMode.HALF_EVEN); // scale 17
		BigDecimal actual = n.add(n).add(n); // tricky operation

		Assert.assertEquals("3 sums of 1/3 compared to 1", 0, expected.compareTo(actual)); // fail 
	}
	
}
