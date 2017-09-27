/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
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
 * zero which makes -0.0d != 0.0d, using equals, compareTo, ==.
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see https://docs.python.org/3/tutorial/floatingpoint.html
 *      https://en.wikipedia.org/wiki/Double-precision_floating-point_format
 */
public class DoubleObjectAccuracyEvaluationInvestigationTest {

	@Test
	public void doubleEvaluationNonTrivialScopeErrorCritreiaTest_01() {
		Double expected = 0.3;
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 17);
		Double n = (new Double(1) / 10);

		Double actual = n + n + n; // tricky operation

		if (correctEvaluation(expected, actual, tolerance)) {
			// inside tolerance.
		}

		// outside range
		// OK
	}

	@Test
	public void doubleEvaluationNonTrivialScopeErrorCritreiaTest_02() {
		Double expected = new Double(0);
		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 17);
		Double n = new Double(-0.0);

		Double actual = n + n + n; // tricky operation

		if (correctEvaluation(expected, actual, tolerance)) {
			// inside tolerance.
		}

		// outside range
		// OK
	}

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
	private boolean correctEvaluation(Double expected, Double actual, Double tolerance) {
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
	public void positiveNegativeFloatingPointZero01() {
		Double positive = +0.0;
		Double negative = -0.0;

		Assert.assertTrue(negative == (0.0 * -1)); // true
		Assert.assertTrue(positive == 0.0); // true
		Assert.assertTrue(0.0 == negative); // true
		Assert.assertTrue(-0.0 == positive); // true
		Assert.assertEquals(positive, negative); // fail -0.0 != +0.0 as Double
													// Objects
	}

	@Test
	public void positiveNegativeFloatingPointIntegerZero() {
		Double positive = new Double(+0.0);
		Double negative = new Double(-0); // loose of sign !!

		Assert.assertEquals(new Double(0.0), negative); // true !! because
														// negative is 0.0 Not a
														// negative value
		Assert.assertEquals(positive, new Double(0.0)); // true
		Assert.assertEquals(positive, negative); // true
		Assert.assertTrue(positive.doubleValue() == (positive.doubleValue() * -1)); // OK
		Assert.assertEquals(negative, new Double(0.0 * -1)); // false ! Break
																// the behavior
																// of natural
																// doubles
	}

	@Test
	public void positiveNegativeCalculatedZero01() {
		Double positive = +0.3;
		Double negative = 0.3 * (-1);

		Assert.assertTrue((positive + negative) == -(positive + negative)); // true
		Assert.assertTrue((negative + positive) == -(positive + negative)); // true
		Assert.assertTrue((negative - (-positive)) == -(positive + negative)); // true
		// fail -0.0 != +0.0 as Double Objects
		Assert.assertEquals(new Double(positive + negative), new Double(-(positive + negative))); // fail
	}

	@Test
	public void positiveNegativeCalculatedZero02() {
		Double positive = new Double(1) / 6;
		Double negative = new Double((double) 3 / (6 * (-3)));

		Assert.assertTrue((positive + negative) == -(positive + negative)); // true
		Assert.assertTrue((negative + positive) == -(positive + negative)); // true
		Assert.assertTrue((negative - (-positive)) == -(positive + negative)); // true
		// fail -0.0 != +0.0 as Double Objects
		Assert.assertEquals(new Double(positive + negative), new Double(-(positive + negative))); // fail
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
		Double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 16);
		Double n = (new Double(1) / 10);

		Double actual = n + n + n; // tricky operation
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
		Double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 1); // <17 =>OK
		Double n = (new Double(1) / 10) * -1;
		Double actual = n + n + n; // tricky operation - negative return

		badFalseableCriteria2(expected, actual, tolerance); // false negative
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
		Double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 1); // <17 =>OK
		Double n = (new Double(1) / 10);
		Double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, actual, tolerance); // forced to fail
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
		Double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 17); // <17 =>OK
		Double n = (new Double(1) / 10);
		Double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, actual, tolerance); // forced to fail
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
		Double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 16); // <17 =>OK
		Double n = (new Double(1) / 10);
		Double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, expected, -tolerance); // false Negative
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
		Double expected = 0.3;

		// <17 is less precise, the tricky operation produce a difference at 17
		// decimal position
		Double tolerance = Math.pow(0.1, 17); // <17 =>OK
		Double n = (new Double(1) / 10);
		Double actual = n + n + n; // tricky operation

		badFalseableCriteria2(expected, expected, tolerance); // forced to fail
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
	private void badFalseableCriteria2(Double expected, Double actual, Double tolerance) {

		// Evaluation criteria produce a FALSE NEGATIVE evaluation when the
		// actual < 0 && expected >0 && tolerance >0
		Double evaluationCriteria = expected - actual;
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

	@Test
	public void comparePositiveNegativeFloatingPoint0To0() {
		Double a = new Double(0.0);
		Double b = new Double(-0.0);

		Assert.assertEquals(0, a.compareTo(b)); // fail - like equals
	}

	@Test
	public void comparePositiveNegativeFloatingPoint0ToN1() {
		Double a = new Double(0.0);
		Double b = new Double(-0.0);

		// is a < b
		Assert.assertEquals(-1, a.compareTo(b)); // fail - like equals
	}

	@Test
	public void comparePositiveNegativeFloatingPoint0ToP1() {
		Double a = new Double(0.0);
		Double b = new Double(-0.0);

		// is a > b
		Assert.assertEquals(1, a.compareTo(b)); // fail - like equals
	}

}
