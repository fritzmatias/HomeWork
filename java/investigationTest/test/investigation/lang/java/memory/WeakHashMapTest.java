package investigation.lang.java.memory;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import investigation.tool.Generators;
import investigationTest.Something;

public class WeakHashMapTest {
	private static final int gcwait = 100;
	static final private Supplier<String> stringGenerator = Generators.NoDeterministic.stringSupplier();
	private final static int maxCreations=10000;

	@Test
	public void weakHashMapWeakValue() {
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
		// force to loose a & r
		a = null;
		r = null;

		while (weakmap.get(key) != null && weakmap.get(key).get() != null) {
			try {
				for (int i = maxCreations; i > 0; i--) {
					Something t = new Something(stringGenerator.get(), 1);
					weakmap.put(t.key, new WeakReference<Something>(t));
				}
				added += maxCreations;
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
			} catch (Exception e) {
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
				System.out.println(e.getMessage());
			}
			System.gc();
			try {
				Thread.sleep(gcwait);
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
	@Test
	public void weakHashMapStrongValue() {
		// https://blogs.vmware.com/apps/2011/06/taking-a-closer-look-at-sizing-the-java-process.html
		// reduce the jvm heap size to fast gc interaction
		// -ea -Xms32m -Xmx32m
		Something a = new Something(new String("a"), 1); // Forcing to loose the constant reference to "a"
		// Something a = new Something("a", 1); // keeps the key strong referenced.

		WeakHashMap<String, Something> weakmap = new WeakHashMap<String, Something>();
		weakmap.put(a.key, a);
		Assert.assertEquals(a, weakmap.get(a.key));
		int added = weakmap.size();

		String key = new String(a.key); // Forcing to use a different object to a.key but equals.
		Assert.assertEquals(key, a.key);
		Assert.assertFalse(key == a.key);
		Assert.assertFalse(key == a.key.trim());
		// force to loose a
		a = null;

		while (weakmap.get(key) != null && added > 5*maxCreations) {
			try {
				for (int i = maxCreations; i > 0; i--) {
					Something t = new Something(stringGenerator.get(), 1);
					weakmap.put(t.key, t);
				}
				added += maxCreations;
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
			} catch (Exception e) {
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
				System.out.println(e.getMessage());
			}
			System.gc();
			try {
				Thread.sleep(gcwait);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("weakmap size:" + weakmap.size());
		Assert.assertEquals(1, weakmap.size()); // true
		if (weakmap.get(key) != null) {
			Assert.assertNull(weakmap.get(key)); // true
			Assert.fail("Only gets here if the key is strong referenced, like by a constant \"a\".");
			// i.e: Something a = new Something("a", 1); // keeps the key strong referenced.
		}
		Assert.assertNull(weakmap.get(key)); // true
	}

	
	@Test
	public void weakHashMapWeakValueObjectWithConstant() {
		Something a = new Something("a", 1); // keeps the key strong referenced because the constant "a".
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
		// force to loose a & r
		a = null;
		r = null;

		while (weakmap.get(key) != null && weakmap.get(key).get() != null) {
			try {
				for (int i = maxCreations; i > 0; i--) {
					Something t = new Something(stringGenerator.get(), 1);
					weakmap.put(t.key, new WeakReference<Something>(t));
				}
				added += maxCreations;
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
			} catch (Exception e) {
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
				System.out.println(e.getMessage());
			}
			System.gc();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("weakmap size:" + weakmap.size());
		Assert.assertEquals(1, weakmap.size()); // true
		if (weakmap.get(key) != null) {
			Assert.assertNull(weakmap.get(key).get()); // true
			Assert.fail("Only gets here if the key is strong referenced, like by a constant \"a\".");
			// i.e: Something a = new Something("a", 1); // keeps the key strong referenced.
		}
		Assert.assertNull(weakmap.get(key)); // never comes here

	}

	@Test
	public void weakHashMapWeakValueConstantKey() {
		Something a = new Something(new String("a"), 1); // keeps the key strong referenced because the constant "a".
		WeakReference<Something> wr = new WeakReference<Something>(a);

		Something r = wr.get();
		Assert.assertEquals(a, r); // Ok
		Assert.assertTrue(a == r); // Ok

		WeakHashMap<String, WeakReference<Something>> weakmap = new WeakHashMap<String, WeakReference<Something>>();
		weakmap.put("a", new WeakReference<Something>(a)); // Key by constant
		Assert.assertEquals(a, weakmap.get(a.key).get());
		int added = weakmap.size();

		String key = new String(a.key); // Forcing to use a different object to a.key but equals.
		Assert.assertEquals(key, a.key);
		Assert.assertFalse(key == a.key);
		Assert.assertFalse(key == a.key.trim());
		// force to loose a & r
		a = null;
		r = null;

		while (weakmap.get(key) != null && weakmap.get(key).get() != null) {
			try {
				for (int i = maxCreations; i > 0; i--) {
					Something t = new Something(stringGenerator.get(), 1);
					weakmap.put(t.key, new WeakReference<Something>(t));
				}
				added += maxCreations;
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
			} catch (Exception e) {
				System.out.println("weakmap size:" + weakmap.size()+ " Added:"+added);
				System.out.println(e.getMessage());
			}
			System.gc();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("weakmap size:" + weakmap.size());
		Assert.assertEquals(1, weakmap.size()); // true
		if (weakmap.get(key) != null) {
			Assert.assertNull(weakmap.get(key).get()); // true
			Assert.fail("Only gets here if the key is strong referenced, like by a constant \"a\".");
			// i.e: Something a = new Something("a", 1); // keeps the key strong referenced.
		}
		Assert.assertNull(weakmap.get(key)); // never comes here

	}

	
}