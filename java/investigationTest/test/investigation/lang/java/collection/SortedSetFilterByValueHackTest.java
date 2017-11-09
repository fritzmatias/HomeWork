/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package investigation.lang.java.collection;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import investigationTest.Something;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public class SortedSetFilterByValueHackTest {

	/**
	 * Hacking the sorting by values. Only useful to filter by value, and fill other
	 * set sorted by key.
	 */
	@Test
	public void getFirstFilterByValuesHack() {
		Comparator<? super Something> comparatorByKey = (o1, o2) -> o1.key.compareTo(o2.key);
		Comparator<? super Something> comparatorByNoKey = (o1, o2) -> o1.value == o2.value ? 1 : o1.value - o2.value;
		Something a = new Something("a", 1); // value are equals
		Something b = new Something("b", 1); // value are equals
		SortedSet<Something> set = new TreeSet<Something>(comparatorByKey);
		Assert.assertTrue(set.add(a));
		Assert.assertTrue(set.add(b));
		Assert.assertTrue(set.add(new Something("c", 1)));
		Assert.assertTrue(set.add(new Something("d", 2)));

		Set<Something> sortedSetByNoKeyValueHack = new TreeSet<Something>(comparatorByNoKey); // hack, objects never are
																								// equals.
		Assert.assertTrue(sortedSetByNoKeyValueHack.add(a));
		Assert.assertTrue(sortedSetByNoKeyValueHack.add(b));

		Assert.assertEquals(2, sortedSetByNoKeyValueHack.size());
		Assert.assertTrue(set.contains(a));
		System.out.println(sortedSetByNoKeyValueHack.toString());

		// Can't modify the temporal SortedSet, hack.
		// Assert.assertTrue(valueSortedSet.contains(a)); // fail because the comparator
		// never return 0
		// Assert.assertTrue(valueSortedSet.remove(a)); // fail because the comparator
		// never return 0

		// A O(n) complexity should be made, to apply the filter.
		Set<Something> result = sortedSetByNoKeyValueHack.stream().limit(2).filter(x -> x.value == 1)
				.sorted(comparatorByKey).collect(Collectors.toSet());
		// The result set is a new sorted by key
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.contains(a));

		System.out.println(result.toString());
	}

	@Test
	public void checkHackSetContainsValidObject() {
		SortedSet<Something> set = new TreeSet<Something>((o1, o2) -> o1.key.compareTo(o2.key));
		Something a = new Something("a", 1);
		Something b = new Something("b", 1);
		Assert.assertTrue(set.add(a));
		Assert.assertTrue(set.add(b));
		Assert.assertEquals(2, set.size());

		Set<Something> valueSortedSet = new TreeSet<Something>(
				(o1, o2) -> o1.value == o2.value ? 1 : a.value - b.value); // hack, objects never are equals.
		Assert.assertTrue(valueSortedSet.add(a));
		Assert.assertTrue(valueSortedSet.add(b));

		Assert.assertEquals(2, valueSortedSet.size());
		Assert.assertTrue(set.contains(a));
		System.out.println(valueSortedSet.toString());

		Assert.assertTrue(valueSortedSet.contains(a)); // fail because the comparator never return 0

		Set<Something> result = valueSortedSet.stream().filter(x -> x.value == 1).collect(Collectors.toSet());
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.contains(a));

		System.out.println(result.toString());
	}

	@Test
	public void checkHackSetDeleteValidObject() {
		SortedSet<Something> set = new TreeSet<Something>((o1, o2) -> o1.key.compareTo(o2.key));
		Something a = new Something("a", 1);
		Something b = new Something("b", 1);
		Assert.assertTrue(set.add(a));
		Assert.assertTrue(set.add(b));
		Assert.assertEquals(2, set.size());

		Set<Something> valueSortedSet = new TreeSet<Something>(
				(o1, o2) -> o1.value == o2.value ? 1 : a.value - b.value); // hack, objects never are equals.
		Assert.assertTrue(valueSortedSet.add(a));
		Assert.assertTrue(valueSortedSet.add(b));

		Assert.assertEquals(2, valueSortedSet.size());
		Assert.assertTrue(set.contains(a));
		System.out.println(valueSortedSet.toString());

		Assert.assertTrue(valueSortedSet.remove(a)); // fail because the comparator never return 0

		Set<Something> result = valueSortedSet.stream().filter(x -> x.value == 1).collect(Collectors.toSet());
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.contains(a));

		System.out.println(result.toString());
	}

}
