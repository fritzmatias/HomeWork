/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.evaluation;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * In this class the tests are forced to fail in the normal usage of BigDecimal
 * exploring the concepts "natural" behavior with unexpected results related to
 * data itself or conceptual misunderstanding.<br>
 * The operations tested are addition, subtraction, multiplication and division
 * in they pure form.
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see https://docs.python.org/3/tutorial/floatingpoint.html
 *      https://en.wikipedia.org/wiki/BigDecimal-precision_floating-point_format
 */
@RunWith(Suite.class)
@SuiteClasses({ BigDecimalAccuracyEvaluationCompareToInvestigationTest.class,
		BigDecimalAccuracyEvaluationEqualsInvestigationTest.class,
		BigDecimalAccuracyEvaluationBadlyInvestigationTest.class })
public class AllEvaluationTests {

}
