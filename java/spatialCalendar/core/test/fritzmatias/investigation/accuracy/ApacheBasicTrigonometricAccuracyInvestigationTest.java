/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.investigation.accuracy;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.analysis.function.Acos;
import org.apache.commons.math3.analysis.function.Asin;
import org.apache.commons.math3.analysis.function.Cos;
import org.apache.commons.math3.analysis.function.Sin;
import org.apache.commons.math3.util.FastMath;

import org.junit.Test;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public class ApacheBasicTrigonometricAccuracyInvestigationTest extends BasicTrigonometricAccuracyInvestigationTest {

	@Test
	public void equivalenceApacheSinASinTest() {
		double radians = Math.toRadians(degree);
		Sin sin = new Sin();
		Asin asin = new Asin();
		assertEquals(0.0, sin.value(radians) - sin.value(asin.value(sin.value(radians))), presicion);
	}

	@Test
	public void equivalenceApacheCosACosTest() {
		double radians = Math.toRadians(degree);
		Cos cos = new Cos();
		Acos acos = new Acos();
		assertEquals(0.0, cos.value(radians) - cos.value(acos.value(cos.value(radians))), presicion);
	}

	@Test
	public void equivalenceFastMathSinASinTest() {
		double radians = Math.toRadians(degree);		
		assertEquals(0.0, FastMath.sin(radians) - FastMath.sin(FastMath.asin(FastMath.sin(radians))), presicion);
	}

	@Test
	public void equivalenceApacheFastMathCosACosTest() {
		double radians = Math.toRadians(degree);
		assertEquals(0.0, FastMath.cos(radians) - FastMath.cos(FastMath.acos(FastMath.cos(radians))), presicion);
	}

}
