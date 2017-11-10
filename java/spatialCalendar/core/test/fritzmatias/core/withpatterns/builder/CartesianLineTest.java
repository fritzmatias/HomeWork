/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package fritzmatias.core.withpatterns.builder;

import org.junit.Assert;
import org.junit.Test;

import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.imp.Positions;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class CartesianLineTest {

	@Test
	public void containsTest() {
		CartesianPosition p1=Positions.cartesian(1, 1);
		CartesianPosition p2=Positions.cartesian(2, 2);
		CartesianPosition p3=Positions.cartesian(2, 2);
		
		CartesianLine line=new CartesianLine(p1, p2);
		
		Assert.assertTrue("Contains ",line.contains(p3));
	}

}
