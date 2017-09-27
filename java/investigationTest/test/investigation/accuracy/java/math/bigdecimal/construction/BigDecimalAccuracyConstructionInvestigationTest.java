/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.construction;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * A BigDecimal is defined by two values: an arbitrary precision integer and a
 * 32-bit integer scale. The value of the BigDecimal is defined to be
 * unscaledValue*10^{-scale}.<br>
 * The precision is the number of digits in the unscaled value. <br>
 * For instance,
 * <ul>
 * 
 * <li>for the number 123.45</li>
 * <ul>
 * <li>the unscale value is 12345</li>
 * <li>the precision returned is 5</li>
 * <li>and the scale is 2.</li>
 * </ul>
 *
 * <li>for the number 0.01</li>
 * <ul>
 * <li>the unscale value is 1</li>
 * <li>the precision returned is 3</li>
 * <li>and the scale is 2.</li>
 * </ul>
 *
 * </ul>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see {@link BigDecimal} {@link RoundingMode}
 */
public class BigDecimalAccuracyConstructionInvestigationTest {

	@Test
	public void integerRepresentation() {
		BigDecimal integer = BigDecimal.valueOf(12345);
		Assert.assertEquals(5, integer.precision()); // true
		Assert.assertEquals(0, integer.scale());// true
	}
	@Test
	public void doubleFractionRepresentationOfDecimal() {
		BigDecimal integer = BigDecimal.valueOf(0.1);
		Assert.assertEquals(1, integer.precision()); // true
		Assert.assertEquals(1, integer.scale());// true
	}
	@Test
	public void doubleFractionRepresentationOfCentecimal() {
		BigDecimal integer = BigDecimal.valueOf(0.01);
		Assert.assertEquals(1, integer.precision()); // true
		Assert.assertEquals(2, integer.scale());// true
	}
	@Test
	public void doubleFractionRepresentationOfMilesimal() {
		BigDecimal integer = BigDecimal.valueOf(0.012);
		Assert.assertEquals(2, integer.precision()); // true
		Assert.assertEquals(3, integer.scale());// true
	}

	@Test
	public void integerFractionRepresentation() {
		BigDecimal integer = BigDecimal.valueOf(1 / 10); // implicit conversion
															// to int
		Assert.assertEquals(BigDecimal.ZERO, integer.stripTrailingZeros()); // true
		
		Assert.assertEquals("Precision",2, integer.precision()); // fail.Because is 1
		Assert.assertEquals("Scale" ,1, integer.scale());// fail, because is 0.
		Assert.assertEquals("Value",BigDecimal.valueOf((double) 1 / 10), integer); // fail
	}

	@Test
	public void integerFractionExpandScaleAffectsPresicionRepresentation() {
		BigDecimal integer = BigDecimal.valueOf(1).setScale(5).divide(BigDecimal.valueOf(10)); 

		Assert.assertEquals("Precision",1, integer.precision()); // fail, because is 5
	}

	@Test
	public void integerFractionExpandScaleRepresentation() {
		BigDecimal integer = BigDecimal.valueOf(1).setScale(5).divide(BigDecimal.valueOf(10)); 

		Assert.assertEquals("Precision",5, integer.precision()); 
		Assert.assertEquals("Scale" ,5, integer.scale());
		Assert.assertEquals("Value",BigDecimal.valueOf((double) 1 / 10), integer.stripTrailingZeros()); // fail
	}

	@Test
	public void ZeroNegativeIntegerRepresentation() {
		BigDecimal integer = BigDecimal.valueOf(-0); // implicit conversion to
														// int
		Assert.assertEquals(1, integer.precision()); // true
		Assert.assertEquals(0, integer.scale());// true
		Assert.assertEquals(BigDecimal.ZERO, integer); // true
	}

	@Test
	public void ZeroNegativeDoubleRepresentation() {
		BigDecimal integer = BigDecimal.valueOf(-0.0); // implicit conversion to
														// int
		Assert.assertEquals(1, integer.precision()); // true
		Assert.assertEquals(1, integer.scale());// true
		Assert.assertEquals(BigDecimal.ZERO, integer.stripTrailingZeros()); // true
		Assert.assertEquals(BigDecimal.ZERO, integer); // fail, because the
														// scale of ZERO:=0
	}

	@Test
	public void integerCastToDoubleRepresentationUnscaledValueEquality() {
		BigDecimal expected = BigDecimal.valueOf(12345);
		BigDecimal integer = BigDecimal.valueOf((double) 12345);
		Assert.assertEquals(6, integer.precision());// true
		Assert.assertEquals(1, integer.scale());// true

		Assert.assertEquals(expected.unscaledValue(), integer.unscaledValue()); // False
	}

	@Test
	public void integerCastToDoubleRepresentationEquality() {
		BigDecimal expected = BigDecimal.valueOf(12345);
		BigDecimal integer = BigDecimal.valueOf((double) 12345);
		Assert.assertEquals(5, expected.precision());// true
		Assert.assertEquals(0, expected.scale());// true
		Assert.assertEquals(6, integer.precision());// true
		Assert.assertEquals(1, integer.scale());// true

		Assert.assertEquals(expected, integer); // False
	}

	@Test
	public void integerCastToDoubleRepresentationStripTrailingZeros() {
		BigDecimal expected = BigDecimal.valueOf(12345);
		BigDecimal integer = BigDecimal.valueOf((double) 12345).stripTrailingZeros();
		Assert.assertEquals(5, integer.precision());// true
		Assert.assertEquals(0, integer.scale());// true

		Assert.assertEquals(expected.scale(), integer.scale()); // True
		Assert.assertEquals(expected.precision(), integer.precision()); // True
		Assert.assertEquals(expected.unscaledValue(), integer.unscaledValue()); // True
		Assert.assertEquals(expected, integer); // true
	}

	@Test
	public void bigDecimalRationalFractionsFromDivision() {
		BigDecimal fraction = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(2));

		Assert.assertEquals(BigDecimal.valueOf(0.5), fraction); // True
	}

	@Test
	public void bigDecimalIrrationalFractionsFromDivisionException() {
		BigDecimal fraction = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3)); // Exception

	}

	@Test
	public void bigDecimalIrrationalFractionsFromDivision() {
		BigDecimal fraction = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);

		Assert.assertEquals(BigDecimal.ZERO, fraction); // True
		Assert.assertEquals(BigDecimal.valueOf(0.3), fraction); // false, fraction is 0
	}

	@Test
	public void bigDecimalIrrationalFractionsByDivitionExceptionZeroDivition() {
		BigDecimal decimal = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);
		BigDecimal centecimal = BigDecimal.valueOf(2).divide(BigDecimal.valueOf(3), RoundingMode.DOWN); // scale
																										// problem

		Assert.assertEquals(BigDecimal.ZERO, decimal); // True
		Assert.assertEquals(BigDecimal.ZERO, centecimal); // True
		BigDecimal actual = decimal.divide(centecimal); // Zero Division
														// Exception
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.5), actual.stripTrailingZeros()); // never
																									// comes
																									// here
	}

	@Test
	public void bigDecimalScaledIrrationalFractionsByDivitionRationalResultScaleFix() {
		BigDecimal decimal = BigDecimal.valueOf(1).setScale(5).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);
		BigDecimal centecimal = BigDecimal.valueOf(2).setScale(5).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);

		BigDecimal actual = decimal.divide(centecimal, 5, RoundingMode.DOWN); // true
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.5), actual.stripTrailingZeros()); // true
	}

	@Test
	public void bigDecimalScaledIrrationalFractionsByDivitionDifferetScaleRationalResultScaleFix() {
		BigDecimal decimal = BigDecimal.valueOf(1).setScale(5).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);
		BigDecimal centecimal = BigDecimal.valueOf(2).setScale(6).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);

		int scale = Math.min(decimal.scale(), centecimal.scale());
		BigDecimal actual = decimal.divide(centecimal, scale, RoundingMode.HALF_EVEN); // true
		Assert.assertEquals("Object Equals", BigDecimal.valueOf(0.5), actual.stripTrailingZeros()); // true
	}

}
