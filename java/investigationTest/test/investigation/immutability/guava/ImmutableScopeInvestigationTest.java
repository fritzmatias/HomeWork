/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.immutability.guava;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public class ImmutableScopeInvestigationTest {

	@Test
	public void guavaImutableCollectionMutableObject() {
		try {
			Date mutableDate = new Date(1L);
			Collection<Date> c = ImmutableList.<Date>builder().add(mutableDate).build();
			mutableDate.setTime(2L);
			// The mutability of the object is not evitable by the use of
			// immutable collection.
			assertTrue(new Date(1L).equals(mutableDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
