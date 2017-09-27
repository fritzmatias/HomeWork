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
 * The operations tested are the equality after construction.<br>
 * A tricky value to initialize is 0.
 * in they pure form.
 * </p>
 * 
 * @author matias
 * @since Version: 1
 * @see https://docs.python.org/3/tutorial/floatingpoint.html
 *      https://en.wikipedia.org/wiki/Double-precision_floating-point_format
 */
public class DoubleObjectAccuracyAssignationInvestigationTest {

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
		Double expected = new Double(1.0 / 10); // implicit Double operation
		Double d = new Double(1 / 10); // implicit cast to int before the
										// convertion to
		// Double

		Assert.assertTrue(0.0 == d); // Is no the right way to evaluate doubles
										// or assert them, but is useful to
										// express the dimension of the error.
										// Because this assert is true in this
										// example.

		Assert.assertEquals(expected, d);
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
		Double expected = new Double((double) 1 / 10); // implicit Double
														// operation
		Double d = new Double((int) 1.0 / 10); // explicit cast to int before
												// the convertion
		// to Double

		Assert.assertTrue(0.0 == d); // Is no the right way to evaluate doubles
										// or assert them, but is useful to
										// express the dimension of the error.
										// Because this assert is true in this
										// example

		Assert.assertEquals(expected, d);
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
		Double expected = new Double((double) 1 / 10); // implicit Double
														// operation
		Double d = new Double((double)( 1 / 10)); // erroneus explicit cast
												// because the
												// implicit int cast

		Assert.assertTrue(0.0 == d); // Is no the right way to evaluate doubles
										// or assert them, but is useful to
										// express the dimension of the error.
										// Because this assert is true in this
										// example.

		Assert.assertEquals(expected, d);

	}
	
	@Test
	public void ZeroFloatingPointInteger_NP() {
		Double expected=new Double(-0.0);
		Double actual=new Double(0);
		
		Assert.assertEquals(expected, actual); // Fails
	}
	@Test
	public void ZeroFloatingPointInteger_NN() {
		Double expected=new Double(-0.0);
		Double actual=new Double(-0); // loose the sign !!
		
		Assert.assertEquals(expected, actual); // fail
	}
	@Test
	public void ZeroFloatingPointInteger_PN_01() {
		Double expected=new Double(0.0);
		Double actual=new Double(-0); // loose the sign !!
		
		Assert.assertEquals(expected, actual); // OK
	}
	@Test
	public void ZeroFloatingPointInteger_PN_02() {
		Double expected=new Double(0.0);
		Double actual=new Double(0)*-1; // Don't loose the sign
		
		Assert.assertEquals(expected, actual); // fail !!
	}
	@Test
	public void ZeroFloatingPointInteger_PP() {
		Double expected=new Double(0.0);
		Double actual=new Double(0);
		
		Assert.assertEquals(expected, actual); // OK
	}
	@Test
	public void ZeroIntegerInteger_PN_01() {
		Double expected=new Double(0);
		Double actual=new Double(-0); // loose the sign !!
		
		Assert.assertEquals(expected, actual); // OK
		Assert.assertTrue( actual == -0 ); // OK
		Assert.assertTrue(0 == (-0)); // OK, but is a False Positive, the internal int cast loose the negative of -0
		Assert.assertEquals(new Double(0*-1), actual); // OK -> But is a False Positive
		Assert.assertEquals(new Double(0*-1.0), actual); // fail
	}
	@Test
	public void ZeroIntegerInteger_PN_02() {
		Double expected=new Double(0);
		Double actual=new Double(0)*-1; // don't loose the sign
		
		Assert.assertEquals(expected, actual); // fail ! 0.0 != -0.0
	}
	@Test
	public void ZeroIntegerInteger_PN_03() {
		Double expected=new Double(0);
		Double actual=new Double(-0); // loose the sign !!
		
		Assert.assertEquals(expected, actual); // OK
		Assert.assertEquals(new Double(new Double(0)*-1), actual); // fail
	}
	
}
