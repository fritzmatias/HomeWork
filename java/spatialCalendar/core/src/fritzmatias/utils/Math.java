/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

import org.apache.commons.math3.util.FastMath;

/**
 * <p>
 * Pattern Proxy for Math functions.
 * </p>
 * 
 * @author user
 * @since Version: 2
 */
public class Math {

	public static double log10(double x) {
		return FastMath.log10(x);
	}

	public static double max(double a, double b) {
		return FastMath.max(a, b);
	}
	public static float max(float a, float b) {
		return FastMath.max(a, b);
	}

	public static long max(long a, long b) {
		return FastMath.max(a, b);
	}

	public static int max(int a, int b) {
		return FastMath.max(a, b);
	}

	public static float min(float a, float b) {
		return FastMath.min(a, b);
	}

	public static long min(long a, long b) {
		return FastMath.min(a, b);
	}

	public static int min(int a, int b) {
		return FastMath.min(a, b);
	}
	
	public static <T> T min(Comparator<T> c, T a,T b) {
		return (c.compare(a, b) <= 0) ? a:b;
	}
	@SuppressWarnings("unchecked")
	public static <T> T min(Comparable<T> a, Comparable<T> b) {
		return (a.compareTo((T)b) <= 0) ? (T)a:(T)b;
	}
	public static <T> T max(Comparator<T> c, T a,T b) {
		return (c.compare(a, b) >= 0) ? a:b;
	}
	@SuppressWarnings("unchecked")
	public static <T> T max(Comparable<T> a, Comparable<T> b) {
		return (a.compareTo((T)b) >= 0) ? (T)a:(T)b;
	}

	public static double sin(double radians) {
		return FastMath.sin(radians);
	}

	public static double asin(double radians) {
		return FastMath.asin(radians);
	}

	public static double cos(double radians) {
		// return FastMath.cos(radians);
		return (FastMath.cos(radians));
	}

	/**
	 * <p>
	 * </p>
	 * @author matias
	 * @since Version: 1
	 * @param d
	 * @param fractionalPlaces
	 * @return
	 */
	private static BigDecimal roundBigDecimal(final double d, int fractionalPlaces) {
		BigDecimal bd = BigDecimal.valueOf(d);
		if (bd.precision() == 0) {
			bd.setScale(fractionalPlaces, RoundingMode.DOWN);
		}
		return bd;
	}

	public static double acos(double radians) {
		// return FastMath.acos(radians);
		return (FastMath.acos(radians));
	}

	/** (non-Javadoc)
	 * @see java.lang.Math#atan2()
	 **/
	public static double atan2(double y, double x) {
		// return FastMath.atan2(y, x);
		return java.lang.Math.atan2(y, x);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param degrees
	 * @return precision of 15 decimal places
	 */
	public static double toRadians(double degrees) {
		 return FastMath.toRadians(degrees);
		// return java.lang.Math.toRadians(degrees);
	}

	public static double toDegrees(double radians) {
		return FastMath.toDegrees(radians);
		// return java.lang.Math.toDegrees(radians);
	}

	public static double signum(double a) {
		return FastMath.signum(a);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param distance
	 * @return
	 */
	public static double abs(double x) {
		return FastMath.abs(x);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param x
	 * @return
	 */
	public static double sqrt(double x) {
		return FastMath.sqrt(x);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param degree
	 * @return
	 */
	public static int floor(double x) {
		return BigDecimal.valueOf(x).intValue();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param y
	 * @param x
	 * @return
	 */
	public static double atan(double x) {
		return FastMath.atan(x);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param angDegree
	 * @return a positive value x where 0<= x < mod
	 */
	public static double normalizeMod(double degree, int mod) {
		// (360degrees + (-) movement )%360 is a positive value. 0<= x < 360
		int floor = (int) Math.floor(degree);
		if (degree == floor) {
			return normalizeMod(floor, mod);
		} else {
			double decimals = (degree - floor);
			return normalizeMod(floor, mod) + decimals;
		}
	}

	/**
	 * <p>
	 * Admits negative values, which are normalized to the equivalent positive.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param value
	 *            is a positive or negative value.
	 * @return positive equivalent module value of mod
	 */
	public static double normalizeMod(int value, int mod) {
		return (mod + (value)) % mod;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param cp1
	 * @param cp2
	 * @return
	 */
	public static double min(double a, double b) {
		return FastMath.min(a, b);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param d
	 * @param scale
	 * @return
	 */
	public static double pow(double d, double scale) {
		return FastMath.pow(d, scale);
	}

	/**
	 * <p>
	 * </p>
	 * @author matias
	 * @since Version: 1
	 * @param radians
	 * @return
	 */
	public static double tan(double radians) {
		return FastMath.tan(radians);
	}
}
