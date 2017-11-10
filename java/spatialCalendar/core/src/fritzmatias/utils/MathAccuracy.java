/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public class MathAccuracy {

	private static final int DEFAULT_BIGDECIMAL_SCALE = 0;

	public static MathContext createScale(int scale, RoundingMode mode) {
		return new MathContext(scale, mode);
	}

	public static MathContext minScaleOf(double a, double b, RoundingMode roundingMode) {
		BigDecimal ba = BigDecimal.valueOf(a);
		BigDecimal bb = BigDecimal.valueOf(b);
		int scale = Math.min(ba.scale(), bb.scale());
		return createScale(scale, roundingMode);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param context
	 * @param near
	 * @param p
	 * @return
	 */
	public static boolean equals(double a, double to, MathContext scale) {
		return compare(a, to, scale) == 0;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param context
	 * @param near
	 * @param p
	 * @return
	 */
	public static boolean equals(BigDecimal a, BigDecimal to, MathContext scale) {
		return compare(a, to, scale) == 0;
	}
	public static boolean equals(BigDecimal a, double to, MathContext scale) {
		return compare(a, BigDecimal.valueOf(to), scale) == 0;
	}
	public static boolean equals(double a, BigDecimal to, MathContext scale) {
		return compare(to, BigDecimal.valueOf(a), scale) == 0;
	}

	public static int compare(BigDecimal a, BigDecimal b, MathContext scale) {
		BigDecimal s1 = a.setScale(scale.getPrecision(), scale.getRoundingMode());
		BigDecimal s2 = b.setScale(scale.getPrecision(), scale.getRoundingMode());
		return s1.compareTo(s2);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param a
	 * @param to
	 * @param scale
	 * @return
	 */
	public static boolean notEquals(double a, double to, MathContext scale) {
		return compare(a, to, scale) != 0;
	}
	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param a
	 * @param to
	 * @param scale
	 * @return
	 */
	public static boolean notEquals(BigDecimal a, BigDecimal to, MathContext scale) {
		return compare(a, to, scale) != 0;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param a
	 * @param to
	 * @param scale
	 * @return
	 */
	public static int compare(double a, double to, MathContext scale) {
		BigDecimal ba = BigDecimal.valueOf(a).setScale(scale.getPrecision(), scale.getRoundingMode());
		BigDecimal bb = BigDecimal.valueOf(to).setScale(scale.getPrecision(), scale.getRoundingMode());

		return ba.compareTo(bb);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param a
	 * @param to
	 * @param scale
	 *            defines the presicion of equality for the comparision
	 * @return
	 */
	public static boolean lessThan(double a, double to, MathContext scale) {
		return compare(a, to, scale) < 0;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param a
	 * @param to
	 * @param scale
	 *            defines the presicion of equality for the comparision
	 * @return
	 */
	public static boolean lessThanEquals(double a, double to, MathContext scale) {
		return compare(a, to, scale) <= 0;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param d
	 * @param scale
	 * @return the double value whith the scale
	 */
	public static double round(double d, MathContext scale) {
		return BigDecimal.valueOf(d).setScale(scale.getPrecision(), scale.getRoundingMode()).doubleValue();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @return
	 */
	public static MathContext doubleScale() {
		return createScale(15, RoundingMode.DOWN);
	}

	public static BigDecimal bigDecimal(double d) {
		return BigDecimal.valueOf(d).setScale(DEFAULT_BIGDECIMAL_SCALE);
	}

	public static BigDecimal bigDecimal(long l) {
		return BigDecimal.valueOf(l).setScale(DEFAULT_BIGDECIMAL_SCALE);
	}

	public static BigDecimal bigDecimal(int i) {
		return BigDecimal.valueOf(i).setScale(DEFAULT_BIGDECIMAL_SCALE);
	}

	public static BigDecimal fractionOf(int scale, BigDecimal a, BigDecimal b) {
		return a.setScale(scale).divide(b, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal fractionOf(BigDecimal a, BigDecimal b) {
		return a.setScale(DEFAULT_BIGDECIMAL_SCALE).divide(b, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal fractionOf(int a, int b) {
		return fractionOf(bigDecimal(a), bigDecimal(b));
	}

	public static BigDecimal fractionOf(long a, long b) {
		return fractionOf(bigDecimal(a), bigDecimal(b));
	}
}
