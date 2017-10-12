/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public class TrigonometricAccuracyInvestigationTest {

	@Test
	public void cosBigDecimalDoubleAccuracyEqualsRadians() {
		double tolerance = Math.pow(0.1, 6); // ok < 7
		double r = Math.pow(10, 10);
		double t = 1; // Radians
		
		double x = r * Math.cos(t);
		BigDecimal cos = BigDecimal.valueOf(Math.cos(t));
		BigDecimal bdX = BigDecimal.valueOf(r).multiply(cos);
		assertEquals(x, bdX.doubleValue(), tolerance); // ok
	}

	@Test
	public void cosBigDecimalDoubleAccuracyEqualsDegree() {
		double tolerance = Math.pow(0.1, 17);
		double r = Math.pow(10, 10);
		double t = 1; // Degree

		double x = r * Math.cos(Math.toRadians(t));
		BigDecimal cos = BigDecimal.valueOf(Math.cos(Math.toRadians(t)));
		BigDecimal bdX = BigDecimal.valueOf(r).multiply(cos);
		assertEquals(x, bdX.doubleValue(), tolerance); // OK
	}

	/**
	 * <p>
	 * The BigDecimal on multiplication calculates different values when the
	 * decimals are > 16 positions.<br>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void multiplicationAccuracyBigDecimalDouble() {
		double tolerance = Math.pow(0.1, 7); // <6 OK
		double r = Math.pow(10, 10);
		double t = 1; // Radians

		double x = r * Math.cos(t);
		BigDecimal cos = BigDecimal.valueOf(Math.cos(t));
		assertEquals(Math.cos(t), cos.doubleValue(), tolerance); // OK
		BigDecimal bdX = BigDecimal.valueOf(r).multiply(cos);
		assertEquals(x, bdX.doubleValue(), tolerance); // fail 
	}

	/**
	 * <p>
	 * The max tolerance is a function of the Max exponent precision and the
	 * integer multiplicand. Loosing floating point precision because the
	 * integer multiplication.<br>
	 * The max decimal precision for trigonometric functions is exponent of 14.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void doubleAccuracyIntegerPartRelatedAsFunction() {
		int intExponent = 15; // Some big integer exponent
		double r = Math.pow(10, intExponent);
		int scale = 14 - (intExponent); // function of r
		double tolerance = Math.pow(0.1, scale); // negative scale
		double t = 1; // radians value

		double x = r * Math.cos(t);
		BigDecimal cos = BigDecimal.valueOf(Math.cos(t));
		BigDecimal bdX = BigDecimal.valueOf(r).multiply(cos).setScale(scale, RoundingMode.DOWN);
		assertEquals(x, bdX.doubleValue(), tolerance); // OK
	}

	/**
	 * <p>
	 * This test fail, because the tolerance exponent is constant. And the
	 * accuracy of double vary in relation of the values involve in the
	 * multiplication, and their precision.<br>
	 * fail, because the tolerance is constant
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @see {@link #doubleAccuracyIntegerPartRelatedAsFunction()}
	 */
	@Test
	public void doubleAccuracyIntegerPartRelated() {
		int intExponent = 5; // ok <5
		double r = Math.pow(10, intExponent);
		int scale = 14; // 14 is ok when intExponent < 5
		double tolerance = Math.pow(0.1, scale); // The tolerance depends on
													// intExponent
		double t = 1; // radians value

		double x = r * Math.cos(t);
		BigDecimal cos = BigDecimal.valueOf(Math.cos(t));
		BigDecimal bdX = BigDecimal.valueOf(r).multiply(cos);
		assertEquals(x, bdX.doubleValue(), tolerance); // fail
	}

	@Test
	public void aCosCosEqualsToleranceDegreeExp14() {
		double tolerance = Math.pow(0.1, 14); // with exponent of <15 the test
												// is OK
		double t = 3; // Degree

		BigDecimal cos = BigDecimal.valueOf(Math.cos(Math.toRadians(t)));
		assertEquals(Math.toRadians(t), Math.acos(cos.doubleValue()), tolerance); // true
	}

	@Test
	public void aCosCosEqualsToleranceDegreeExp15() {
		double tolerance = Math.pow(0.1, 15); // with exponent of <15 the test
												// is OK
		double t = 1; // Degree value

		BigDecimal cos = BigDecimal.valueOf(Math.cos(Math.toRadians(t)));
		assertEquals(Math.toRadians(t), Math.acos(cos.doubleValue()), tolerance); // fail
	}
	
	@Test
	public void aCosCosEqualsToleranceRadians() {
		double tolerance = Math.pow(0.1, 17); 
		double t = 1; // Radians value

		BigDecimal cos = BigDecimal.valueOf(Math.cos(t));

		assertEquals(t, Math.acos(Math.cos(t)), tolerance); // fail
		assertEquals(t, Math.acos(cos.doubleValue()), tolerance); // fail
	}

	@Test
	public void aSinSinEqualsToleranceDegree() {
		double tolerance = Math.pow(0.1, 17); // with exponent of <15 the test
												// is OK
		double t = 3; // Degree

		BigDecimal sin = BigDecimal.valueOf(Math.sin(Math.toRadians(t)));
		assertEquals(Math.toRadians(t), Math.asin(sin.doubleValue()), tolerance); // true
	}

	@Test
	public void aSinSinEqualsToleranceRadians() {
		double tolerance = Math.pow(0.1, 17); // with exponent of <15 the test
												// is OK
		double t = 1; // Radians value

		BigDecimal sin = BigDecimal.valueOf(Math.sin(t));

		assertEquals(t, Math.asin(sin.doubleValue()), tolerance); // ok
	}
	@Test
	public void aTanTanEqualsToleranceDegree() {
		double tolerance = Math.pow(0.1, 17); // with exponent of <15 the test
												// is OK
		double t = 3; // Degree

		BigDecimal tan = BigDecimal.valueOf(Math.tan(Math.toRadians(t)));
		assertEquals(Math.toRadians(t), Math.atan(tan.doubleValue()), tolerance); // true
	}

	@Test
	public void aTanTanEqualsToleranceRadians() {
		double tolerance = Math.pow(0.1, 17); // with exponent of <15 the test
												// is OK
		double t = 1; // Degree value

		BigDecimal tan = BigDecimal.valueOf(Math.tan(t));

		assertEquals(t, Math.atan(tan.doubleValue()), tolerance); // ok
	}	
}
