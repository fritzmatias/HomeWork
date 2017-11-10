/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.stateless.processors.spatial;

import java.math.RoundingMode;
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;

import fritzmatias.core.model.Planet;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RunWith(Parameterized.class)
public class SpatialPlanetAlgebraicPositionCalculatorTest
		extends fritzmatias.core.stateless.processors.SpatialPlanetPositionCalculatorTest {
	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 */
	@Test
	public void testV2() {
		BiFunction<Integer, Planet, Position> f = SpatialFactoryImp
				.createCircularPositionCalculator(MathAccuracy.createScale(7, RoundingMode.DOWN));

		Position actual = f.apply(day, planet).cartesian();

		Assert.assertEquals(this.expected.getX(), actual.getDirectPosition().getOrdinate(0), aceptableError);
		Assert.assertEquals(this.expected.getY(), actual.getDirectPosition().getOrdinate(1), aceptableError);
	}
}
