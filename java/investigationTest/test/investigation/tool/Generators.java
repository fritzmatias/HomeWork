package investigation.tool;

import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author matias
 *
 */
public final class Generator {
	/**
	 * Not Deterministic generator. Uses Math.random to generate the values
	 * 
	 * 
	 * @author matias
	 *
	 */
	static public class NoDeterministic {
		public static Supplier<String> stringSupplier() {
			return () -> Long.toHexString(Double.doubleToLongBits(Math.random()));
		}

		public static Supplier<Long> longSupplier() {
			return () -> Long.valueOf(Double.doubleToLongBits(Math.random()));
		}

		public static Supplier<Integer> intSupplier() {
			return () -> Integer.valueOf((int) Double.doubleToLongBits(Math.random()));
		}

		public static Supplier<Double> doubleSupplier() {
			return () -> Math.random();
		}
	}

	static public class Deterministic {

	}

}
