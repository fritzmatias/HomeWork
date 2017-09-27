/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.operation;

import java.math.BigDecimal;

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
public class BigDecimalAccuracyMultiplyInvestigationTest {

	@Test
	public void multiplication01() {
		BigDecimal decimal = BigDecimal.valueOf(0.1);
		BigDecimal centecimal = BigDecimal.valueOf(0.01);

		BigDecimal actual = decimal.multiply(decimal); // true
		Assert.assertEquals("Scale", centecimal.scale(), actual.scale()); // true
		Assert.assertEquals("Precision", centecimal.precision(), actual.precision()); // true
		Assert.assertEquals("Object Equals", centecimal, actual); // true
	}

	@Test
	public void multiplication02() {
		BigDecimal d = BigDecimal.valueOf(0.12345);
		BigDecimal expected = BigDecimal.valueOf(12345);

		BigDecimal actual = d.multiply(BigDecimal.valueOf(100000));
		Assert.assertEquals("Object Equals", expected, actual); // false
	}

}
