/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.utils;

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
public class MathAccuracyTest {
	@Test
	public void roundTest() {
		double decimal = 0.0;
		double centecimal = 0.01;

		Assert.assertEquals(decimal, MathAccuracy.round(centecimal, MathAccuracy.createScale(0, RoundingMode.DOWN)),
				0.0);
	}
}
