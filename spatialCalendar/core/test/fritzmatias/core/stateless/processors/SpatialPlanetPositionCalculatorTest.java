/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.stateless.processors;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RunWith(Parameterized.class)
public class SpatialPlanetPositionCalculatorTest {
	@Parameter(0)
	public Planet planet;
	@Parameter(1)
	public Integer day;
	@Parameter(2)
	public Vector2D expected;
	private static Vector2D center;

	static {
		center = SpatialObjectFactory.createVector2D(0,0);
	}

	private static Vector2D getPosition(Vector2D center, double radius, double angle) {
		Vector2D p = new Vector2D(
				(double) (center.getX() + radius * Math.cos(Math.toRadians(angle))),
				(double) (center.getY() + radius * Math.sin(Math.toRadians(angle))));

		return p;
	}

	@Parameters(name = "{index}:{0}")
	public static Collection<Object[]> getData() {
		Set<Object[]> testDataQueue = new HashSet<Object[]>();

		/**
		 * WARNING: the anglurspeed is related to the time t to produce the
		 * specific expected position. And de default AngularDirection for a
		 * mathematical degree movement is AntiHour.
		 */

		testDataQueue
				.add(new Object[] { SpatialObjectFactory.createPlanet("P-basic", 1, AngularDirection.AntiHour, 1),
						45, getPosition(center, 1, 45) });

		// Distance data Test
		testDataQueue.add(
				new Object[] { SpatialObjectFactory.createPlanet("P-distance", 1, AngularDirection.AntiHour, 100),
						45, getPosition(center, 100, 45) });
		testDataQueue.add(new Object[] {
				SpatialObjectFactory.createPlanet("P-farAway", 1, AngularDirection.AntiHour, Integer.MAX_VALUE), 45,
				getPosition(center, Integer.MAX_VALUE, 45) });
		testDataQueue.add(new Object[] {
				SpatialObjectFactory.createPlanet("P-toNear", 1, AngularDirection.AntiHour, 0), 45, center });

		testDataQueue
				.add(new Object[] { SpatialObjectFactory.createPlanet("P-near2", 1, AngularDirection.AntiHour, 0), 0,
						getPosition(center, 0, 0) });

		
		// AngularVelocity data Test
		testDataQueue
				.add(new Object[] { SpatialObjectFactory.createPlanet("P-fast", 1, AngularDirection.AntiHour, 100),
						45, getPosition(center, 100, 45) });
		testDataQueue.add(
				new Object[] { SpatialObjectFactory.createPlanet("P-fastest", 360, AngularDirection.AntiHour, 1), 1,
						getPosition(center, 1, 0) });
		testDataQueue
		.add(new Object[] { SpatialObjectFactory.createPlanet("P-nomove", 0, AngularDirection.AntiHour, 1),
				1, getPosition(center, 1, 0) });
		testDataQueue
				.add(new Object[] { SpatialObjectFactory.createPlanet("P-reverseSpeed", 1, AngularDirection.Hour, 1),
						45, getPosition(center, 1, 360 - 45) });


		// Day datatest
		testDataQueue.add(new Object[] {
				SpatialObjectFactory.createPlanet("P-ZeroDay", 1, AngularDirection.AntiHour, Integer.MAX_VALUE), 0,
				getPosition(center, Integer.MAX_VALUE, 0) });
		testDataQueue.add(
				new Object[] { SpatialObjectFactory.createPlanet("P-yearTrip", 1, AngularDirection.AntiHour, 1),
						360, getPosition(center, 1, 360*360*2) });
		testDataQueue.add(
				new Object[] { SpatialObjectFactory.createPlanet("P-longTripEquastoSmallOne", 1, AngularDirection.AntiHour, 1),
						(360 * 2)+1, getPosition(center, 1, 1) });


		
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
		BiFunction<Integer, Planet, Vector2D> f = new SpatialPlanetPositionCalculator(7);
		Vector2D actual = f.apply(day, planet);

		double aceptableError = 0.0000001;

			Assert.assertEquals(expected.getX(), actual.getX(), aceptableError);
			Assert.assertEquals(expected.getY(), actual.getY(), aceptableError);
	}

}
