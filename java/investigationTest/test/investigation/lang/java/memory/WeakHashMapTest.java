package investigation.lang.java.memory;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import investigation.tool.Generator;
import investigationTest.Something;

public class WeakHashMapTest {
	static final private Supplier<String> stringGenerator = Generator.NoDeterministic.stringSupplier();

	@Test
	public void weakReference() {
		// https://blogs.vmware.com/apps/2011/06/taking-a-closer-look-at-sizing-the-java-process.html
		// reduce the jvm heap size to fast gc interaction
		// -ea -Xms32m -Xmx32m
		Something a = new Something(new String("a"), 1); // Forcing to loose the constant reference to "a"
		// Something a = new Something("a", 1); // keeps the key strong referenced.
		WeakReference<Something> wr = new WeakReference<Something>(a);

		Something r = wr.get();
		Assert.assertEquals(a, r); // Ok
		Assert.assertTrue(a == r); // Ok

		WeakHashMap<String, WeakReference<Something>> weakmap = new WeakHashMap<String, WeakReference<Something>>();
		weakmap.put(a.key, new WeakReference<Something>(a));
		Assert.assertEquals(a, weakmap.get(a.key).get());
		int added = weakmap.size();

		String key = new String(a.key); // Forcing to use a different object to a.key but equals.
		Assert.assertEquals(key, a.key);
		Assert.assertFalse(key == a.key);
		Assert.assertFalse(key == a.key.trim());
		// force to loose a&r
		a = null;
		r = null;

		while (weakmap.get(key) != null && weakmap.get(key).get() != null) {
			try {
				for (int i = 10000; i > 0; i--) {
					Something t = new Something(stringGenerator.get(), 1);
					weakmap.put(t.key, new WeakReference<Something>(t));
				}
				added += 10000;
				System.out.println("weakmap size:" + weakmap.size());
			} catch (Exception e) {
				System.out.println("weakmap size:" + weakmap.size());
				System.out.println(e.getMessage());
			}
			System.gc();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("weakmap size:" + weakmap.size());
		Assert.assertEquals(0, weakmap.size()); // true
		if (weakmap.get(key) != null) {
			Assert.assertNull(weakmap.get(key).get()); // true
			Assert.fail("Only gets here if the key is strong referenced, like by a constant \"a\".");
			// i.e: Something a = new Something("a", 1); // keeps the key strong referenced.
		}
		Assert.assertNull(weakmap.get(key)); // true

	}

}