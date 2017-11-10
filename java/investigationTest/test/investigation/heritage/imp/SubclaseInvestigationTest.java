/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.heritage.imp;

import org.junit.Assert;
import org.junit.Test;

import investigation.heritage.MixedInterface;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class SubclaseInvestigationTest {

	@Test
	public void getClassInterfaceTest() {
		MixedInterface o=new SubClassMixed();
		
		Assert.assertEquals(MixedInterface.class, o.getClass() );
	}
	@Test
	public void getClassTest() {
		MixedInterface o=new SubClassMixed();
		
		Assert.assertEquals(SubClassMixed.class, o.getClass() );
	}
	@Test
	public void instanceOfClassTypeTest() {
		MixedInterface o=new SubClassMixed();
		
		if(! (o instanceof SubClassMixed)) {
			Assert.fail("Not an instance of SubClassMixed");
		}
	}
	@Test
	public void instanceOfInterfaceTypeTest() {
		MixedInterface o=new SubClassMixed();
		
		if(! (o instanceof MixedInterface)) {
			Assert.fail("Not an instance of MixedInterface");
		}
	}
}
