/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.doubl;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import investigation.accuracy.java.math.bigdecimal.evaluation.AllEvaluationTests;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
@RunWith(Suite.class)
@SuiteClasses({ DoubleAccuracyAssignationInvestigationTest.class, DoubleAccuracyOperationsInvestigationTest.class,
		AllEvaluationTests.class })
public class AllTests {

}
