/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.stateless.processors;

import static fritzmatias.core.TBuilder.calculateCircularPosition;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Planet;
import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.stateless.processors.spatial.SpatialPlanetPositionCalculator;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RunWith(Parameterized.class)
public class SpatialPlanetPositionCalculatorTest {
	protected final double aceptableError = 0.0000001;
	@Parameter(0)
	public Planet planet;
	@Parameter(1)
	public Integer day;
	@Parameter(2)
	public Vector2D expected;
	private static Vector2D center;

	static {
		center = SpatialObjectFactory.createVector2D(0, 0);
	}

	private static Object[] n(String name, int day, AngularDirection direcction, int radio, int speed) {

		return new Object[] { SpatialObjectFactory.createPlanet(name, day, direcction, radio), speed,
				calculateCircularPosition(center, radio, day * speed * direcction.mathModificator()) };
	}

	@Parameters(name = "{0}")
	public static Collection<Object[]> getData() {
		Collection<Object[]> testDataQueue = new ArrayList<Object[]>();

		/**
		 * WARNING: the anglurspeed is related to the time t to produce the
		 * specific expected position. And de default AngularDirection for a
		 * mathematical degree movement is AntiHour.
		 */

		testDataQueue.add(n("basic degree vel 1", 1, AngularDirection.AntiHour, 1, 45));
		testDataQueue.add(n("basic degree vel 0", 1, AngularDirection.AntiHour, 1, 0));
		testDataQueue.add(n("basic degree vel 360", 1, AngularDirection.AntiHour, 1, 360));

		// Distance data Test
		testDataQueue.add(n("radio 100", 1, AngularDirection.AntiHour, 100, 45));
		testDataQueue.add(n("radio Int.MaxValue", 1, AngularDirection.AntiHour, Integer.MAX_VALUE, 45));
		testDataQueue.add(n("radio 0", 1, AngularDirection.AntiHour, 0, 45));

		// AngularVelocity data Test
		testDataQueue.add(n("velocity 45*9", 1, AngularDirection.AntiHour, 100, 45 * 9));
		testDataQueue.add(n("days 360", 360, AngularDirection.AntiHour, 1, 1));
		testDataQueue.add(n("P-reverseSpeed 45", 1, AngularDirection.Hour, 1, 45));
		testDataQueue.add(n("P-reverseSpeed 5", 1, AngularDirection.Hour, 2000, 5));
		testDataQueue.add(n("P-reverseSpeed 7 (360/7 = double)", 1, AngularDirection.Hour, 2000, 7));

		// Day datatest
		testDataQueue.add(n("day 0", 0, AngularDirection.AntiHour, Integer.MAX_VALUE, 1));
		testDataQueue.add(n("P-yearTrip", 360, AngularDirection.AntiHour, 1000, 1));
		testDataQueue.add(n("P-longTripEquastoSmallOne", 361, AngularDirection.AntiHour, 1000, 1));

		testDataQueue.add(n("radio 0, vel 0", 1, AngularDirection.AntiHour, 0, 0));

		return testDataQueue;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	@Test
	public void test() {
		BiFunction<Integer, Planet, Vector2D> f = new SpatialPlanetPositionCalculator(
				MathAccuracy.createScale(7, RoundingMode.DOWN));

		Vector2D actual = f.apply(day, planet);

		Assert.assertEquals(expected.getX(), actual.getX(), aceptableError);
		Assert.assertEquals(expected.getY(), actual.getY(), aceptableError);
	}

}
