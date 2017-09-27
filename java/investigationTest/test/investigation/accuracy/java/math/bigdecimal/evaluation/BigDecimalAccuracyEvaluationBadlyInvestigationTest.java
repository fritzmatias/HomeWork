/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.bigdecimal.evaluation;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

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
public class BigDecimalAccuracyEvaluationBadlyInvestigationTest {

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param expected
	 * @param actual
	 * @param tolerance
	 */
	private boolean correctEvaluation(BigDecimal expected, BigDecimal actual, BigDecimal tolerance) {

		// all the criterias can be paramateres with any sign
		if (expected.abs().subtract(actual.abs()).abs().compareTo(tolerance.abs()) <= 0) {
			return true;
		}
		return false;
	}

	@Test
	public void doubleEvaluationNonTrivialScopeErrorCritreiaTest() {
		BigDecimal expected = BigDecimal.valueOf(0.0);
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		BigDecimal tolerance = BigDecimal.ONE.scaleByPowerOfTen(-17);
		BigDecimal n = BigDecimal.valueOf((double) 1 / 10);

		BigDecimal actual = n.subtract(n); // tricky operation

		if (correctEvaluation(expected, actual, tolerance)) {
			// inside tolerance.
		}
		;

		// outside range

	}
	
	//
	// /**
	// * <p>
	// * This test is a common error trying to fix the precision error.
	// * </p>
	// *
	// * @author matias
	// * @since Version: 1
	// */
	// @Test
	// public void doubleBadEvaluationCritreiaTest1_0() {
	// BigDecimal expected = BigDecimal.valueOf(0.3);
	//
	// // <17 is less precise, the tricky operation produce a difference at 17
	// // decimal position
	// BigDecimal tolerance = BigDecimal.valueOf(0.1).scaleByPowerOfTen(16);
	// BigDecimal n = BigDecimal.valueOf((double) 1 / 10);
	//
	// BigDecimal actual = n.add(n).add(n); // tricky operation
	// if (expected - actual <= tolerance) {
	// // the evaluation is very bad. Depends of the sign of the operation
	// // result, loose one extreme of the range, and assumes:
	// // expected <= actual + tolerance --absolute values--
	// if (Math.abs(actual) > Math.abs(expected) && Math.abs(actual) -
	// Math.abs(expected) <= Math.abs(tolerance)) {
	// Assert.fail("Too Simplistic error calculation. Produce False positive
	// when tolerance pow <17: "
	// + (-tolerance) + "<=" + (expected - actual) + " <= " + tolerance + " ?");
	// // fail
	// }
	//
	// Assert.fail("If actual > 0 && actual > expected && torlerance pow >= 17,"
	// + "means a false positive but, because is over the tolerance range :/");
	// return;
	// }
	//
	// Assert.fail("Only comes here if the result of the tricky operation is <0.
	// Wich includes a FALSE Negative.");
	// }

	/**
	 * <p>
	 * This is a second erroneous approach with the intention of fix the error
	 * in {@link #doubleBadEvaluationCritreiaTest1()}
	 * </p>
	 *
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void badEvaluationCritreiaTest() {
		BigDecimal expected = BigDecimal.ZERO;

		BigDecimal n = BigDecimal.valueOf(0.1);
		BigDecimal actual = n.subtract(n); // tricky operation

		Assert.assertEquals(BigDecimal.valueOf(0.0), BigDecimal.valueOf(-0.0)); // True
		Assert.assertEquals(expected, actual); // fail- Should be used compareTo
	}
	
	// /**
	// * <p>
	// * This is a second erroneous approach with the intention of fix the error
	// * in {@link #doubleBadEvaluationCritreiaTest1()}
	// * </p>
	// *
	// * @author matias
	// * @since Version: 1
	// */
	// @Testlm 
	// public void doubleBadEvaluationCritreiaTest2_2() {
	// BigDecimal expected = 0.3;
	//
	// // <17 is less precise, the tricky operation produce a difference at 17
	// // decimal position
	// BigDecimal tolerance = Math.pow(0.1, 17); // <17 =>OK
	// BigDecimal n = ((BigDecimal) 1 / 10);
	// BigDecimal actual = n + n + n; // tricky operation
	//
	// badFalseableCriteria2(expected, actual, tolerance); // fail - forced
	// }
	//
	// /**
	// * <p>
	// * This is a second erroneous approach with the intention of fix the error
	// * in {@link #doubleBadEvaluationCritreiaTest1()}
	// * </p>
	// *
	// * @author matias
	// * @since Version: 1
	// */
	// @Test
	// public void doubleBadEvaluationCritreiaTest2_3() {
	// BigDecimal expected = 0.3;
	//
	// // <17 is less precise, the tricky operation produce a difference at 17
	// // decimal position
	// BigDecimal tolerance = Math.pow(0.1, 16); // <17 =>OK
	// BigDecimal n = ((BigDecimal) 1 / 10);
	// BigDecimal actual = n + n + n; // tricky operation
	//
	// badFalseableCriteria2(expected, expected, -tolerance); // fail - false
	// negative
	// }
	//
	// /**
	// * <p>
	// * This is a second erroneous approach with the intention of fix the error
	// * in {@link #doubleBadEvaluationCritreiaTest1()}
	// * </p>
	// *
	// * @author matias
	// * @since Version: 1
	// */
	// @Test
	// public void doubleBadEvaluationCritreiaTest2_4() {
	// BigDecimal expected = 0.3;
	//
	// // <17 is less precise, the tricky operation produce a difference at 17
	// // decimal position
	// BigDecimal tolerance = Math.pow(0.1, 17); // <17 =>OK
	// BigDecimal n = ((BigDecimal) 1 / 10);
	// BigDecimal actual = n + n + n; // tricky operation
	//
	// badFalseableCriteria2(expected, expected, tolerance); // fail
	// }
	//
	// /**
	// * <p>
	// * </p>
	// *
	// * @author matias
	// * @since Version: 1
	// * @param expected
	// * @param tolerance
	// * @param n
	// */
	// private void badFalseableCriteria2(BigDecimal expected, BigDecimal
	// actual, BigDecimal tolerance) {
	//
	// // Evaluation criteria produce a FALSE NEGATIVE evaluation when the
	// // actual < 0 && expected >0 && tolerance >0
	// BigDecimal evaluationCriteria = expected - actual;
	// if (-tolerance <= evaluationCriteria && evaluationCriteria <= tolerance)
	// {
	// // if expected <= actual
	// // ---> expected - actual <= 0
	// // ---> if -tolerance <= expected - actual could be sound ok, but
	// // the error is the assumption of : actual >=0 && expected >=0 ?
	// // then if actual <0 && expected >=0, that is a FALSE NEGATIVE
	// // evaluation.
	// // Because of the && connector between the range evaluation, no
	// // FALSE POSITIVES will occur.
	//
	// // actual > expected ? => false positive.
	// if ((Math.abs(actual) >= Math.abs(expected) && Math.abs(actual) -
	// Math.abs(expected) > Math.abs(tolerance))
	// || (Math.abs(actual) < Math.abs(expected)
	// && Math.abs(expected) - Math.abs(actual) > Math.abs(tolerance))) {
	// // This evaluation is only to check the false positive.
	// // depends of the sign of the operation result.
	// Assert.fail("Never occurs because the && in the evaluation of the
	// range");
	// // Assert.assertTrue(
	// // "False positive because was considered as inside the
	// // tolerance range but is outside: "
	// // + (-tolerance) + " <=" + evaluationCriteria + " <= " +
	// // tolerance,
	// // (Math.abs(expected) >= Math.abs(actual)
	// // && (Math.abs(expected) - Math.abs(actual)) > tolerance));
	// // Assert.assertTrue(
	// // "False positive because was considered as inside the
	// // tolerance range but is outside: "
	// // + (-tolerance) + " <=" + evaluationCriteria + " <= " +
	// // tolerance,
	// // (Math.abs(expected) < Math.abs(actual) && (Math.abs(actual) -
	// // Math.abs(expected)) > tolerance));
	// return;
	// }
	//
	// Assert.assertTrue(
	// "Inside of the tolerance scope. This not validate the invalid
	// caracteristics of the criteria.",
	// false);
	// // if expected > actual
	// return;
	// } else {
	// if (Math.abs(actual) >= Math.abs(expected)
	// && Math.abs(actual) - Math.abs(expected) <= Math.abs(tolerance)) {
	// Assert.assertTrue(
	// "False negative because was considered as outside the tolerance range but
	// is inside: "
	// + (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
	// + " but when actual² > expected² should be: " + (-Math.abs(tolerance)) +
	// " <="
	// + (Math.abs(actual) - Math.abs(expected)) + " <= " + Math.abs(tolerance),
	// Math.abs(tolerance) == tolerance);
	//
	// // This evaluation is only to check the false negative.
	// Assert.fail("False negative because was considered as outside the
	// tolerance range but is inside: "
	// + (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
	// + " but when actual² > expected² should be: " + (-Math.abs(tolerance)) +
	// " <="
	// + (Math.abs(actual) - Math.abs(expected)) + " <= " +
	// Math.abs(tolerance));
	// }
	// if (Math.abs(actual) < Math.abs(expected) && Math.abs(expected) -
	// Math.abs(actual) <= Math.abs(tolerance)) {
	// Assert.assertTrue(
	// "False negative because was considered as outside the tolerance range but
	// is inside: "
	// + (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
	// + " but when actual² > expected² should be: " + (-Math.abs(tolerance)) +
	// " <="
	// + (Math.abs(actual) - Math.abs(expected)) + " <= " + Math.abs(tolerance),
	// Math.abs(tolerance) == tolerance);
	// // This evaluation is only to check the false negative.
	// Assert.fail("False negative because was considered as outside the
	// tolerance range but is inside: "
	// + (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
	// + " but when expected² > actual² should be: " + (-Math.abs(tolerance)) +
	// " <="
	// + (Math.abs(expected) - Math.abs(actual)) + " <= " +
	// Math.abs(tolerance));
	// }
	//
	// Assert.assertTrue(
	// "Negative OK. Out of the tolerance scope. This not validate the invalid
	// caracteristics of the criteria.",
	// false);
	// }
	// }

}
