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

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public class BigDecimalAccuracyScaleTest {

	@Test
	public void manualUnScale() {
		BigDecimal unscale = BigDecimal.valueOf(12345);
		Assert.assertEquals(5, unscale.precision()); // true
		Assert.assertEquals(0, unscale.scale()); // true

		BigDecimal centecimal = BigDecimal.valueOf(123.45);
		Assert.assertEquals(5, centecimal.precision()); // true
		Assert.assertEquals(2, centecimal.scale()); // true

		// The problem is the double type representation
		BigDecimal converted = BigDecimal.valueOf(centecimal.doubleValue() * Math.pow(10, centecimal.scale()));
		Assert.assertEquals(1, converted.scale()); // true

		// So the BigDecimals Objects are different
		Assert.assertEquals(centecimal.precision() + 1, converted.precision()); // true
		Assert.assertEquals("Value",unscale, converted); // fail, required trim
	}

	@Test
	public void manualUnScaleByPowerOfTen() {
		BigDecimal unscale = BigDecimal.valueOf(12345);
		Assert.assertEquals(5, unscale.precision());// true
		Assert.assertEquals(0, unscale.scale());// true

		BigDecimal centecimal = BigDecimal.valueOf(123.45);
		Assert.assertEquals(5, centecimal.precision());// true
		Assert.assertEquals(2, centecimal.scale());// true

		BigDecimal properUnscale = centecimal.scaleByPowerOfTen(centecimal.scale());
		Assert.assertEquals(5, properUnscale.precision());// true
		Assert.assertEquals(0, properUnscale.scale());// true
		Assert.assertEquals(centecimal.unscaledValue(), properUnscale.unscaledValue());// true

		Assert.assertEquals(unscale.unscaledValue(), properUnscale.unscaledValue()); // true
		Assert.assertEquals(unscale, properUnscale); // true
	}

	@Test
	public void setPositiveScaleEqualObject01_0() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(0.01);

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(2);
		Assert.assertEquals("Value",centecimal, actual); // fail
	};

	@Test
	public void setPositiveScaleEqualObject01_1() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(0.01);

		// The internal representation is modified, not the external representation of value
		BigDecimal actual = decimal.setScale(2);
		Assert.assertEquals("UnscaledValue",centecimal.unscaledValue(), actual.unscaledValue()); // fail
	};

	@Test
	public void setPositiveScaleEqualObject02_0() {
		BigDecimal decimal = BigDecimal.valueOf(1);
		BigDecimal centecimal = BigDecimal.valueOf(0.1);

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(2);
		Assert.assertEquals("Value",centecimal, actual); // fail
	};
	@Test
	public void setPositiveScaleEqualObject02_1() {
		BigDecimal decimal = BigDecimal.valueOf(1);
		BigDecimal centecimal = BigDecimal.valueOf(0.1);

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(2);
		Assert.assertEquals("UnscaledValue",centecimal.unscaledValue(), actual.unscaledValue()); // fail
	};
	@Test
	public void setPositiveScaleEqualObject02_2() {
		BigDecimal decimal = BigDecimal.valueOf(1);
		BigDecimal centecimal = BigDecimal.valueOf(0.1);

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(2);
		Assert.assertEquals("Value",centecimal, actual); // fail
	};

	@Test
	public void setNegativeScaleEqualObject() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(1.00);

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(-2); // exception
	};

	@Test
	public void setNegativeScaleEqualObject01_0() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(0.00);

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(-2, RoundingMode.HALF_EVEN); // puts 0 before the fraction point
		Assert.assertTrue(centecimal.stripTrailingZeros().compareTo(actual.stripTrailingZeros()) == 0); // true
		Assert.assertEquals("Value",centecimal, actual); // fail
	};

	@Test
	public void setNegativeScaleEqualObject01_1() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(0.00);

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(-2, RoundingMode.HALF_EVEN);
		Assert.assertEquals(centecimal.precision(), actual.precision()); // true
		Assert.assertEquals(centecimal.unscaledValue(), actual.unscaledValue()); // true
		Assert.assertEquals("Scale",centecimal.scale(), actual.scale()); // fail
	};

	@Test
	public void setNegativeScaleEqualObject01_2() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.ZERO;

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(-1, RoundingMode.HALF_EVEN);
		Assert.assertEquals(centecimal.precision(), actual.precision()); // true
		Assert.assertEquals(centecimal.unscaledValue(), actual.unscaledValue()); // true
		Assert.assertEquals("Scale",centecimal.scale(), actual.scale()); // fail
	};

	@Test
	public void setNegativeScaleEqualObject01_3() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.ZERO;

		// The internal representation is modified, not the external
		BigDecimal actual = decimal.setScale(-1, RoundingMode.HALF_EVEN);
		Assert.assertEquals("Value",centecimal, actual); // fail
	};

	@Test
	public void expandValueBySetScaleKeepsPresition0_00() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(0.01);

		BigDecimal actual = decimal.setScale(2);

		// 0.1 != 0.10
		Assert.assertEquals("Value", decimal, actual); // fail
	}

	@Test
	public void expandValueBySetScaleKeepsPresition0_01() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(0.01);

		BigDecimal actual = decimal.setScale(2);

		Assert.assertEquals("Precision",decimal.precision(), actual.precision()); // fail
	}

}
