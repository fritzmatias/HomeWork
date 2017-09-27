package investigation.accuracy.java.math.doubl;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * In this class the tests are forced to fail in the normal usage of dobules
 * exploring the concepts "natural" behavior with unexpected results related to
 * data itself or conceptual misunderstanding.<br>
 * The operations tested are equals, compareTo.<br>
 * The problem associated is the discrimination between negative 0 and positive
 * zero which makes -0.0d != 0.0d, using equals, ==. 
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see 
 * https://docs.python.org/3/tutorial/floatingpoint.html
 * https://en.wikipedia.org/wiki/Double-precision_floating-point_format
 */
public class DoubleAccuracyEvaluationInvestigationTest {

	@Test
	public void doubleEvaluationNonTrivialScopeErrorCritreiaTest() {
		double expected = 0.3;
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		double tolerance = Math.pow(0.1, 17);
		double n = ((double) 1 / 10);

		double actual = n + n + n; // tricky operation

		if( correctEvaluation(expected, actual, tolerance)) {
			//  inside tolerance.
		};

		//outside range

	}

	/**
	 * <p>
	 * </p>
	 * @author matias
	 * @since Version: 1
	 * @param expected
	 * @param actual
	 * @param tolerance
	 */
	private boolean correctEvaluation(double expected, double actual, double tolerance) {
		if (Math.abs(tolerance) == Double.MIN_VALUE) {
			throw new IllegalArgumentException("The tolerance is too much precise. Is no differente to use ==");
		}

		// all the criterias can be paramateres with any sign
		if (Math.abs(Math.abs(expected) - Math.abs(actual)) <= Math.abs(tolerance)) {			
			return true;

		}
		return false;
	}

	@Test
	public void positiveNegativeZero() {
		double positive = +0.0;
		double negative = -0.0; // 

		Assert.assertTrue(positive == negative); // true
		Assert.assertTrue(negative == (0.0 * -1)); // true
		Assert.assertTrue(positive == 0.0); // true
		Assert.assertTrue(0.0 == negative); // true
	}
	@Test
	public void positiveNegativeFloatingPointIntegerZero() {
		double positive = +0.0;
		double negative = -0; // loose the sign, because implicit int cast

		Assert.assertTrue(positive == negative); // true
		Assert.assertTrue(negative == (0.0 * -1)); // true
		Assert.assertTrue(positive == 0.0); // true
		Assert.assertTrue(0.0 == negative); // true
	}
	
	@Test
	public void positiveNegativeCalculatedZero01() {
		double positive = +0.3;
		double negative = 0.3 * (-1);

		Assert.assertTrue(positive != negative); // True
		Assert.assertTrue((positive + negative) == -(positive + negative)); // true
		Assert.assertTrue((negative + positive) == -(positive + negative)); // true
		Assert.assertTrue((negative - (-positive)) == -(positive + negative)); // true
	}

	@Test
	public void positiveNegativeCalculatedZero02() {
		double positive = (double) 1 / 6;
		double negative = (double)3 / (6 * (-3)); // - 1/6

		Assert.assertTrue((positive + negative) == -(positive + negative)); // true
		Assert.assertTrue((negative + positive) == -(positive + negative)); // true
		Assert.assertTrue((negative - (-positive)) == -(positive + negative)); // true
	}

	/**
	 * <p>
	 * This test is a common error trying to fix the precision error.
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 */
	@Test
	public void doubleBadEvaluationCritreiaTest1_0() {
		double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		double tolerance = Math.pow(0.1, 16);
		double n = ((double) 1 / 10);

		double actual = n + n + n; // tricky operation
		if (expected - actual <= tolerance) {
			// the evaluation is very bad. Depends of the sign of the operation
			// result, loose one extreme of the range, and assumes:
			// expected <= actual + tolerance --absolute values--
			if (Math.abs(actual) > Math.abs(expected) && Math.abs(actual) - Math.abs(expected) <= Math.abs(tolerance)) {
				Assert.fail("Too Simplistic error calculation. Produce False positive when tolerance pow <17: "
						+ (-tolerance) + "<=" + (expected - actual) + " <= " + tolerance + " ?"); // fail
			}

			Assert.fail("If actual > 0 && actual > expected && torlerance pow >= 17, "
					+ "means a false positive but, because is over the tolerance range :/");
			return;
		}

		Assert.fail("Only comes here if the result of the tricky operation is < 0. Wich includes a FALSE Negative.");
	}

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
	public void doubleBadEvaluationCritreiaTest2_0() {
		double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		double tolerance = Math.pow(0.1, 1); // <17 =>OK
		double n = ((double) 1 / 10) * -1;
		double actual = n + n + n; // tricky operation - negative return

		badFalseableCriteria2(expected, actual, tolerance); // fail - false negative
	}

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
	public void doubleBadEvaluationCritreiaTest2_1() {
		double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		double tolerance = Math.pow(0.1, 1); // <17 =>OK
		double n = ((double) 1 / 10);
		double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, actual, tolerance); // fail - forced
	}

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
	public void doubleBadEvaluationCritreiaTest2_2() {
		double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		double tolerance = Math.pow(0.1, 17); // <17 =>OK
		double n = ((double) 1 / 10);
		double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, actual, tolerance); // fail - forced
	}

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
	public void doubleBadEvaluationCritreiaTest2_3() {
		double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		double tolerance = Math.pow(0.1, 16); // <17 =>OK
		double n = ((double) 1 / 10);
		double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, expected, -tolerance); // fail - false negative
	}

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
	public void doubleBadEvaluationCritreiaTest2_4() {
		double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		double tolerance = Math.pow(0.1, 17); // <17 =>OK
		double n = ((double) 1 / 10);
		double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, expected, tolerance); // fail
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param expected
	 * @param tolerance
	 * @param n
	 */
	private void badFalseableCriteria2(double expected, double actual, double tolerance) {

		// Evaluation criteria produce a FALSE NEGATIVE evaluation when the
		// actual < 0 && expected >0 && tolerance >0
		double evaluationCriteria = expected - actual;
		if (-tolerance <= evaluationCriteria && evaluationCriteria <= tolerance) {
			// if expected <= actual
			// ---> expected - actual <= 0
			// ---> if -tolerance <= expected - actual could be sound ok, but
			// the error is the assumption of : actual >=0 && expected >=0 ?
			// then if actual <0 && expected >=0, that is a FALSE NEGATIVE
			// evaluation.
			// Because of the && connector between the range evaluation, no
			// FALSE POSITIVES will occur.

			// actual > expected ? => false positive.
			if ((Math.abs(actual) >= Math.abs(expected) && Math.abs(actual) - Math.abs(expected) > Math.abs(tolerance))
					|| (Math.abs(actual) < Math.abs(expected)
							&& Math.abs(expected) - Math.abs(actual) > Math.abs(tolerance))) {
				// This evaluation is only to check the false positive.
				// depends of the sign of the operation result.
				Assert.fail("Never occurs because the && in the evaluation of the range");

				return;
			}

			Assert.assertTrue(
					"Inside of the tolerance scope. This not validate the invalid caracteristics of the criteria.",
					false);
			// if expected > actual
			return;
		} else {
			if (Math.abs(actual) >= Math.abs(expected)
					&& Math.abs(actual) - Math.abs(expected) <= Math.abs(tolerance)) {
				Assert.assertTrue(
						"False negative because was considered as outside the tolerance range but is inside: "
								+ (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
								+ " but when actual² > expected² should be: " + (-Math.abs(tolerance)) + " <="
								+ (Math.abs(actual) - Math.abs(expected)) + " <= " + Math.abs(tolerance),
						Math.abs(tolerance) == tolerance);

				// This evaluation is only to check the false negative.
				Assert.fail("False negative because was considered as outside the tolerance range but is inside: "
						+ (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
						+ " but when actual² > expected² should be: " + (-Math.abs(tolerance)) + " <="
						+ (Math.abs(actual) - Math.abs(expected)) + " <= " + Math.abs(tolerance));
			}
			if (Math.abs(actual) < Math.abs(expected) && Math.abs(expected) - Math.abs(actual) <= Math.abs(tolerance)) {
				Assert.assertTrue(
						"False negative because was considered as outside the tolerance range but is inside: "
								+ (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
								+ " but when actual² > expected² should be: " + (-Math.abs(tolerance)) + " <="
								+ (Math.abs(actual) - Math.abs(expected)) + " <= " + Math.abs(tolerance),
						Math.abs(tolerance) == tolerance);
				// This evaluation is only to check the false negative.
				Assert.fail("False negative because was considered as outside the tolerance range but is inside: "
						+ (-tolerance) + " <=" + evaluationCriteria + " <= " + tolerance
						+ " but when expected² > actual² should be: " + (-Math.abs(tolerance)) + " <="
						+ (Math.abs(expected) - Math.abs(actual)) + " <= " + Math.abs(tolerance));
			}

			Assert.assertTrue(
					"Negative OK. Out of the tolerance scope. This not validate the invalid caracteristics of the criteria.",
					false);
		}
	}

}
