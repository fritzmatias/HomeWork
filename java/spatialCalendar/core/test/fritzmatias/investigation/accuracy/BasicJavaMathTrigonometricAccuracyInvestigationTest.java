/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.investigation.accuracy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * <p>
 * </p>
 * @author user
 * @since Version: 1
 */
public class BasicJavaMathTrigonometricAccuracyInvestigationTest extends BasicTrigonometricAccuracyInvestigationTest {
	
	@Test
	public void toRadiansDegreesEquivalenceTest() {
		assertEquals(0.0,Math.toRadians(degree)- Math.toRadians(360+degree), presicion);
	}

	@Test
	public void equivalenceSinASinTest() {
		double radians=Math.toRadians(degree);
		assertEquals(0.0,Math.sin(radians) - Math.sin(Math.asin(Math.sin(radians))), presicion);
	}

	@Test
	public void equivalenceCosACosTest() {
		double radians=Math.toRadians(degree);
		assertEquals(0.0,Math.cos(radians) - Math.cos(Math.acos(Math.cos(radians))), presicion);
	}
	
}
