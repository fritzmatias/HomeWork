/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import investigation.accuracy.java.math.bigdecimal.construction.BigDecimalAccuracyConstructionInvestigationTest;
import investigation.accuracy.java.math.bigdecimal.evaluation.AllEvaluationTests;
import investigation.accuracy.java.math.bigdecimal.operation.AllOperationTests;
import investigation.accuracy.java.math.bigdecimal.reduction.AllReductionTest;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
@RunWith(Suite.class)
@SuiteClasses({ BigDecimalAccuracyConstructionInvestigationTest.class, AllOperationTests.class, AllReductionTest.class,
		AllEvaluationTests.class })
public class AllTests {

}
