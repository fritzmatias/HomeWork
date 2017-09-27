/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package investigation.immutability.java;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public class MutableInvestigationTest {

	@Test
	public void mutableObjectHashCode() {
		try {
			Date expected = new Date(1L);
			Date mutableDate = new Date(1L);

			assertEquals(expected.hashCode(), mutableDate.hashCode());

			mutableDate.setTime(2L);
			assertEquals("When a mutable object changes, his hashcode change (and should)", expected.hashCode(),
					mutableDate.hashCode()); // fail
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hashCollectionOfMutableObjects() {
		Set<Date> set = new HashSet<Date>();
		Date date1 = new Date(1L);
		Date sameDate = new Date(1L);
		Date date2 = new Date(2L);
		Date date3 = new Date(3L);
		set.add(date1); // first insert -> hashcode related to internal data 1L
		set.add(date2);
		set.add(date3);

		date1.setTime(4L); // internal state modification
		Assert.assertTrue("This will be true, because the modification do not update the collection",
				set.add(sameDate)); // true
		// second insert -> hashcode related to internal data 4L
		Assert.assertFalse("Hash Problem, add the same object twice because different hashcode", 
				set.add(date1)); // fail
	}

	@Test
	public void sortingProblems1() {
		SortedSet<Date> sortedSet = new TreeSet<Date>();
		Date date1 = new Date(1L);
		Date date2 = new Date(2L);
		Date date3 = new Date(3L);
		sortedSet.add(date1);
		sortedSet.add(date2);
		sortedSet.add(date3);

		Assert.assertEquals(date1, sortedSet.first()); // true
		Assert.assertEquals(date3, sortedSet.last()); // true

		date1.setTime(4L);
		Assert.assertEquals("This will be true, because the modification do not update the collection", date3,
				sortedSet.last()); // true
		Assert.assertEquals("Sorting Problem on mutable objects", date1, sortedSet.last()); // fail
	}

	@Test
	public void sortingProblems2() {
		SortedSet<Date> sortedSet = new TreeSet<Date>();
		Date date1 = new Date(1L);
		Date date2 = new Date(2L);
		Date date3 = new Date(3L);
		sortedSet.add(date1);
		sortedSet.add(date2);
		sortedSet.add(date3);

		Assert.assertEquals(date1, sortedSet.first()); // true
		Assert.assertEquals(date3, sortedSet.last()); // true;

		date1.setTime(4L);
		Assert.assertEquals("This will be true, because the modification do not update the collection", date1,
				sortedSet.first()); // true
		Assert.assertEquals("Sorting Problem on mutable objects", date2, sortedSet.first()); // fail
	}
}
