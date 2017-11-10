/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.investigation;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import fritzmatias.patterns.buider.imp.ImmutableBuilderAbstract;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public class ConcurrencyInvestigationTest {
	class builder1 extends ImmutableBuilderAbstract<Integer> {

		public builder1(Map<String, Object> map) {
			super(map);
		}

		public int getInt() {
			return this.getOptional("int") == null ? 0 : ((int) this.getOptional("int"));
		}

		public builder1(String exceptionMessage) {
			super(exceptionMessage);
		}

		public void setInt(Integer i) {
			set("int", i);
		}

		@Override
		protected Integer create() {
			return Integer.valueOf((int) this.getOptional("int"));
		}

	}

	@Test
	public void java8WaitOnStream() {
		builder1 builder = new builder1(new ConcurrentHashMap<String, Object>());
		int maxRange = 10000;
		Map<Integer,Integer> o=IntStream.range(0, maxRange).parallel().boxed().collect(Collectors.toConcurrentMap(Function.identity(), i -> {
			sleep(i);
			// Mutable acction no thread safe
			builder.setInt(i + 1);
			return builder.getInt();
		}));
		builder.build();
		assertEquals(maxRange, o.size());
		sleep(30);
	}

	@Test
	public void lockConcurrentMapTest() {
		builder1 builder = new builder1(new ConcurrentHashMap<String, Object>());
		ExecutorService executor = Executors.newFixedThreadPool(4);

		int maxRange = 10000;
		IntStream.range(0, maxRange).forEach(i -> executor.submit(() -> {
			sleep(i);
			System.out.println(builder.getInt());
			// Mutable situation whitout syncronize
			builder.setInt(builder.getInt() + 1);
			if (builder.getInt() == 10) {
				builder.build();
			}
		}));
		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
		}
		System.out.println(builder.build());
		Assert.assertEquals(maxRange, (int) builder.build());
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param i
	 */
	public void sleep(int i) {
		try {
			Thread.sleep(1 * 1000 / (i + 1)); // linear decrement sleep, the
			// newest threads will end first.
		} catch (InterruptedException e) {
		}
	}

	@Test
	public void lockConcurrentMapAndSyncronizeTest() {
		builder1 builder = new builder1(new ConcurrentHashMap<String, Object>());
		ExecutorService executor = Executors.newFixedThreadPool(4);

		int maxRange = 10000;
		IntStream.range(0, maxRange).forEach(i -> executor.submit(() -> {
			sleep(i);
			System.out.println(builder.getInt());
			synchronized (builder) {
				// Mutable situation
				builder.setInt(builder.getInt() + 1);
			}
			if (builder.getInt() == 10) {
				builder.build();
			}

		}));
		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
		}
		System.out.println(builder.build());
		Assert.assertEquals(maxRange, (int) builder.build());
	}

	@Test
	public void lockFutureTest() {
		builder1 builder = new builder1(new ConcurrentHashMap<String, Object>());
		ExecutorService executor = Executors.newFixedThreadPool(4);

		int maxRange = 10000;
		IntStream.range(0, maxRange).forEach(i -> {
			Future<Integer> f = executor.submit(() -> {
				sleep(i);
				System.out.println(builder.getInt());
				return builder.getInt() + 1;

			});
			try {
				builder.setInt(f.get());
				if (builder.getInt() == 10) {
					builder.build();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
		}
		System.out.println(builder.build());
		Assert.assertEquals(maxRange, (int) builder.build());
	}

}
