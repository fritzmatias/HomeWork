/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package test;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class HashEqualsTest<T> {
	T expected;
	T actual;

	/**
	 * <p>
	 * </p>
	 * @author matias
	 * @param o1 
	 * @param o2 
	 * @since Version: 1
	 */
	public HashEqualsTest(T o1, T o2) {
		this.expected=o1;
		this.actual=o2;
	}
	
	@Test
	public void hashCodeTest() {
		Assert.assertEquals(this.expected.hashCode(), this.actual.hashCode());
	}
	
	@Test
	public void equalsTest() {
		Assert.assertEquals(expected, actual);
	}
	
}
