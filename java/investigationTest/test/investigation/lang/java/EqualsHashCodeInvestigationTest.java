/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.lang.java;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public class EqualsHashCodeInvestigationTest {
	@Test
	public void defaultEquals() {
		Assert.assertEquals(new A(), new A()); // fail
	}
	@Test
	public void defaultHashCode() {
		Assert.assertEquals(new A().hashCode(), new A().hashCode()); // fail
	}
	
	private class A {
	};

}
