/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.accuracy.java.math.doubl;

import org.junit.Test;

import org.junit.Assert;

/**
 * <p>
 * In this class the tests are forced to fail in the normal usage of
 * dobules exploring the concepts "natural" behavior with unexpected
 * results related to data itself or conceptual misunderstanding.<br>
 * The operations tested are addition, subtraction, multiplication and division
 * in they pure form.
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see 
 * https://docs.python.org/3/tutorial/floatingpoint.html
 * https://en.wikipedia.org/wiki/double-precision_floating-point_format
 */
public class DoubleAccuracyAssignationInvestigationTest {

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @see {@link #assingnationImplicitIntCast()}
	 *      {@link #assingnationExplicitIntCast()}
	 *      {@link #assignationExplicitCast()}
	 */
	@Test
	public void assingnationImplicitIntCast() {
		double expected = 1.0 / 10; // implicit double operation
		double d = 1 / 10; // implicit cast to int before the convertion to
							// double

		Assert.assertTrue(0.0 == d); // Is no the right way to evaluate doubles
										// or assert them, but is useful to
										// express the dimension of the error.
										// Because this assert is true in this
										// example.

		Assert.assertEquals(expected, d, 0.0001); // fail
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @see {@link #assingnationImplicitIntCast()}
	 *      {@link #assingnationExplicitIntCast()}
	 *      {@link #assignationExplicitCast()}
	 */
	@Test
	public void assingnationExplicitIntCast() {
		double expected = (double) 1 / 10; // implicit double operation
		double d = (int) 1.0 / 10; // explicit cast to int before the convertion
									// to double

		Assert.assertTrue(0.0 == d); // Is no the right way to evaluate doubles
										// or assert them, but is useful to
										// express the dimension of the error.
										// Because this assert is true in this
										// example

		Assert.assertEquals(expected, d, 0.0001); // fail
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @see {@link #assingnationImplicitIntCast()}
	 *      {@link #assingnationExplicitIntCast()}
	 *      {@link #assignationExplicitCast()}
	 */
	@Test
	public void assignationExplicitDoubleCast() {
		double expected = (double) 1 / 10; // implicit double operation
		double d = (double) (1 / 10); // erroneous explicit cast because the
										// implicit int cast

		Assert.assertTrue(0.0 == d); // Is no the right way to evaluate doubles
										// or assert them, but is useful to
										// express the dimension of the error.
										// Because this assert is true in this
										// example.

		Assert.assertEquals(expected, d, 0.0001); // fail

	}

	@Test
	public void ZeroFloatingPointInteger_NP() {
		double expected=(-0.0);
		double actual=(0);
		
		Assert.assertEquals(expected, actual, 0.0001); // OK
	}
	@Test
	public void ZeroFloatingPointInteger_NN() {
		double expected=(-0.0);
		double actual=(-0); // loose the sign !!
		
		Assert.assertEquals(expected, actual, 0.0001); // OK
	}
	@Test
	public void ZeroFloatingPointInteger_PN_01() {
		double expected=(0.0);
		double actual=(-0); // loose the sign !!
		
		Assert.assertEquals(expected, actual, 0.0001); // OK
	}
	@Test
	public void ZeroFloatingPointInteger_PN_02() {
		double expected=(0.0);
		double actual=(0)*-1; // Don't loose the sign
		
		Assert.assertEquals(expected, actual, 0.0001); // OK
	}
	@Test
	public void ZeroFloatingPointInteger_PN_03() {
		double expected=(0.0);
		double actual=(0)*-1.0; // Don't loose the sign
		
		Assert.assertTrue(expected == actual); // OK
		Assert.assertEquals(expected, actual, 0.0001); // OK
	}	
	@Test
	public void ZeroFloatingPointInteger_PP() {
		double expected=(0.0);
		double actual=(0);
		
		Assert.assertEquals(expected, actual, 0.0001); // OK
	}
	@Test
	public void ZeroIntegerInteger_PN_01() {
		double expected=(0);
		double actual=(-0); // loose the sign !!
		
		Assert.assertEquals(expected, actual, 0.0001); // OK
		Assert.assertTrue( actual == -0 ); // OK
		Assert.assertTrue(0 == 0*-1); // OK 
	}
	@Test
	public void ZeroIntegerInteger_PN_02() {
		double expected=(0);
		double actual=(0)*-1; // don't loose the sign
		
		Assert.assertEquals(expected, actual, 0.0001); // OK
	}	
}
