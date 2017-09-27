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
/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class BigDecimalAccuracyPrecisionInvestigationTest {
	
	@Test 
	public void integerMovePointToLeft() {
		BigDecimal integer=BigDecimal.valueOf(1);
		BigDecimal expected=BigDecimal.valueOf(0.1);
		
		BigDecimal actual=integer.movePointLeft(1);
		Assert.assertEquals(expected, actual); // true
	}
	@Test
	public void integerMovePointToRight() {
		BigDecimal integer=BigDecimal.valueOf(1);
		BigDecimal expected=BigDecimal.valueOf(10);
		
		BigDecimal actual=integer.movePointRight(1);
		Assert.assertEquals(expected, actual); // true
	}

	@Test 
	public void doubleMovePointToLeft() {
		BigDecimal doubl=BigDecimal.valueOf(0.1);
		BigDecimal expected=BigDecimal.valueOf(0.01);
		
		BigDecimal actual=doubl.movePointLeft(1);
		Assert.assertEquals(expected, actual); // true
	}
	@Test
	public void doubleMovePointToRight() {
		BigDecimal doubl=BigDecimal.valueOf(0.1);
		BigDecimal expected=BigDecimal.valueOf(1);
		
		BigDecimal actual=doubl.movePointRight(1);
		Assert.assertEquals(expected, actual); // true
	}

	@Test 
	public void movePointToLeftByZero() {
		BigDecimal doubl=BigDecimal.valueOf(0.1);
		
		BigDecimal actual=doubl.movePointLeft(0);
		Assert.assertEquals(doubl, actual); // true
	}
	@Test
	public void movePointToRightByZero() {
		BigDecimal doubl=BigDecimal.valueOf(0.1);
		
		BigDecimal actual=doubl.movePointRight(0);
		Assert.assertEquals(doubl, actual); // true
	}

}
