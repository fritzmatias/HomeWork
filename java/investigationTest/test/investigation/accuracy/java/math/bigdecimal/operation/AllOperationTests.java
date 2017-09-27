/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.operation;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
@RunWith(Suite.class)
@SuiteClasses({ BigDecimalAccuracyDivisionInvestigationTest.class, BigDecimalAccuracyMultiplyInvestigationTest.class,
		BigDecimalAccuracySubtractionInvestigationTest.class, BigDecimalAccuracySumInvestigationTest.class,
		BigDecimalAccuracyScaleTest.class,
		BigDecimalAccuracyPrecisionInvestigationTest.class })
public class AllOperationTests {

}
