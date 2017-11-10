/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import static fritzmatias.core.TBuilder.planet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import fritzmatias.core.imp.SpatialCalendarBuilderImp1;
import fritzmatias.core.imp.SpatialCalendarBuilderImp2;
import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.withpatterns.builder.CartesianLine;
import fritzmatias.core.withpatterns.model.PolarPosition;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.imp.Positions;
import fritzmatias.utils.Math;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RunWith(Parameterized.class)
public class CalendarTest {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Parameter(0)
	public String name;
	@Parameter(1)
	public int expectedDay = 0;
	@Parameter(2)
	public SpatialSystemStateType expectedState;
	@Parameter(3)
	public Object expectedIntensity;
	@Parameter(4)
	public ImmutableCollection<Planet> planetArray;
	@Parameter(5)
	public MathContext scale;

	@Parameters(name = "{0}-{5}")
	public static Collection<Object[]> getData() {
		Collection<Object[]> data = new ArrayList<Object[]>();
//		 int maxDegree = 360;
		int startDegree=0;
		int maxDegree = 360;
		double radio = 400; // FIX: The tests pass for this radio, but not for 500
		// int pres = 14;
//		int pres = 5;
//		int pres = 15 - (int) (Math.log10(radio) +7);
		int pres = 14 - (int) (Math.log10(radio) +6);
		int minScale = pres , maxScale = minScale;
//		int minScale = pres < 0 ? 0 : pres, maxScale = minScale;
		for (int scale = minScale; scale <= maxScale; scale++) {
			for (int degree = startDegree; degree <= maxDegree; degree++) {
				data.add(n("1-", degree, SpatialSystemStateType.RectaConPunto, 0L, setOfRectaPuntoInside(radio, 1),
						MathAccuracy.createScale(scale, RoundingMode.DOWN)));
				data.add(n("11 -", degree, SpatialSystemStateType.RectaConPunto, 0L, setOfRectaPuntoInside(radio, 11),
						MathAccuracy.createScale(scale, RoundingMode.DOWN)));
				data.add(n("1.1 -", degree, SpatialSystemStateType.RectaConPunto, 0L, setOfRectaPuntoInside(radio, 1.1),
						MathAccuracy.createScale(scale, RoundingMode.DOWN)));
				data.add(n("a -"+degree, 1, SpatialSystemStateType.RectaSinPunto, 0L, setOfRectaPuntoOutside(radio, degree),
						MathAccuracy.createScale(scale, RoundingMode.DOWN)));
				// fijo al dìa uno para que no cambie el shape. los angulos se
				// prueban por i
				// Al variar la i varìa la rotacion de la figura
				if (degree == 1) {
					int dia = 1;
					data.add(n("triangle inside -", dia, SpatialSystemStateType.TrianguloEncierraOrigen, 0L,
							setOfTrianglePointInside(radio, degree),
							MathAccuracy.createScale(scale, RoundingMode.DOWN)));
					data.add(n("TO -", dia, SpatialSystemStateType.TrianguloVacio, 0L,
							setOfTrianglePointOutside(radio, degree),
							MathAccuracy.createScale(scale, RoundingMode.DOWN)));
				}
			}
		}

		return data;
	}

	private static Object[] n(String testName, int dia, SpatialSystemStateType expectedType, long expectedIntensity,
			ImmutableCollection<Planet> planets, MathContext scale) {
		return new Object[] { testName + " - " + dia + " - " + expectedType, dia, expectedType, expectedIntensity,
				planets, scale };
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @return
	 * @since Version: 1
	 */
	private static ImmutableCollection<Planet> setOfRectaPuntoInside(double radio, double angleDegree) {
		ImmutableCollection<Planet> planets = ImmutableList.<Planet>builder()
				.add(planet("planetsRectaPuntoInside 1", angleDegree, AngularDirection.AntiHour, 1 * radio))
				.add(planet("planetsRectaPuntoInside 2", angleDegree, AngularDirection.AntiHour, 2 * radio))
				.add(planet("planetsRectaPuntoInside 3", angleDegree, AngularDirection.AntiHour, 3 * radio)).build();
		return planets;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @return
	 * @since Version: 1
	 */
	private static ImmutableCollection<Planet> setOfRectaPuntoOutside(double radio, double degree) {
//		double scale = 15 - Math.log10(radio) - 3; // FIX: this test works with radio <100 but if the radio is 1000 fails. Accuracy problems
		double scale = 8; // FIX: this test works with radio <100 but if the radio is 1000 fails. Accuracy problems
		double precision = Math.pow(0.1, scale);
		MathContext s = new MathContext((int) scale, RoundingMode.DOWN); 
		MathContext d = new MathContext((int) scale, RoundingMode.HALF_EVEN); 

		System.out.println(degree+" - "+scale);
		CartesianLine line= TBuilder.cartesianPoligonBuilder()
				.setScale(d)
				.of(Positions.polar(radio, degree).cartesian())
				.of(Positions.polar(radio*2, (degree+42)).cartesian())
				.build();

		Position point0=line.apply(0.0);
		Position point1=line.apply(1.0);
		Position point2=line.apply(1.12);
		

		ImmutableCollection<Planet> planetsRectaPuntoOutside = ImmutableList.<Planet>builder()
				.add(planet("planetsRectaPuntoOutside 1", point0.polar().getDegrees(),
								AngularDirection.AntiHour, point0.polar().getRadio()))
				.add(planet("planetsRectaPuntoOutside 2", point1.polar().getDegrees(),
						AngularDirection.AntiHour, point1.polar().getRadio()))
				.add(planet("planetsRectaPuntoOutside 3", point2.polar().getDegrees(),
						AngularDirection.AntiHour, (point2.polar().getRadio())))
				.build();

		return planetsRectaPuntoOutside;
	}

	private static ImmutableCollection<Planet> setOfTrianglePointOutside(double radio, double angleDegree) {
		ImmutableCollection<Planet> planets = ImmutableList.<Planet>builder()
				.add(planet("planetsRectaPuntoInside 1", 1 + angleDegree, AngularDirection.AntiHour, 1 * radio))
				.add(planet("planetsRectaPuntoInside 2", 2 + angleDegree, AngularDirection.AntiHour, 2 * radio))
				.add(planet("planetsRectaPuntoInside 3", 5 + angleDegree, AngularDirection.AntiHour, 3 * radio))
				.build();
		return planets;
	}

	private static ImmutableCollection<Planet> setOfTrianglePointInside(double radio, double degree) {
		PolarPosition p1 = TBuilder.polarPosition(radio * 1, degree); // 1er
																		// cuad
		PolarPosition p2 = TBuilder.polarPosition(radio * 2, degree + 180); // 3er
		PolarPosition p3 = TBuilder.polarPosition(radio * 3, degree + 270); // 4to
																			// cuad

		ImmutableCollection<Planet> planets = ImmutableList.<Planet>builder()
				.add(planet("planetsTrianglePointInside 1", p1.polar().getDegrees(), AngularDirection.AntiHour,
						p1.polar().getRadians()))
				.add(planet("planetsTrianglePointInside 2", p2.polar().getDegrees(), AngularDirection.AntiHour,
						p2.polar().getRadians()))
				.add(planet("planetsTrianglePointInside 3", p3.polar().getDegrees(), AngularDirection.AntiHour,
						p3.polar().getRadians()))
				.build();

		return planets;
	}

	@Test
	public void testAlgebraicImp2() {
		SpatialCalendarConfiguration conf = SpatialCalendarBuilder.builder();
		conf.setPlanets(this.planetArray).setScale(scale).setIncludeVertices(true).setRangeToBuild(this.expectedDay,
				this.expectedDay);

		SpatialCalendarSystem calendar = conf.build();

		calendar.register(SpatialCalendarBuilderImp2.knownAlgebraicShapeTypes());

		log.info("reportDays: {}", conf.getMaxRangeDays());

		Map<Integer, SpatialSystemState> states = calendar.calculateStates();

		assertEquals(conf.getMaxRangeDays() - conf.getMinRangeDays() + 1, states.size());

		SpatialSystemState state = states.get(expectedDay);

		assertNotNull(state.getShape());
		assertEquals(expectedDay, state.getDay());
		assertEquals(expectedState, state.getType());
		assertTrue(state.getShape().equivalent(expectedState));
		assertEquals(expectedIntensity, state.getIntensity());

		Map<SpatialShapeType, List<SpatialSystemState>> periods = calendar.periodShapeCalculator(states);

		// calendar.reportYearlyStatus(conf.getMaxRangeDays(), periods);
		// calendar.reportMaxIntensity(states);
	}

	// @Test
	public void testLogicalImp2() {
		SpatialCalendarConfiguration conf = SpatialCalendarBuilderImp2.builder();
		conf.setPlanets(this.planetArray).setScale(scale).setIncludeVertices(true).setRangeToBuild(this.expectedDay,
				this.expectedDay);

		SpatialCalendarSystem calendar = conf.build();

		calendar.register(SpatialCalendarBuilderImp2.knownLogicalShapeTypes());

		log.info("reportDays: {}", conf.getMaxRangeDays());

		Map<Integer, SpatialSystemState> states = calendar.calculateStates();

		assertEquals(conf.getMaxRangeDays() - conf.getMinRangeDays() + 1, states.size());
		// Map<Integer, SpatialSystemState> states =
		// calendar.buildOneYearOfStates(conf.getPlanets(),
		// conf.getMinRangeDays(), conf.getMaxRangeDays());

		SpatialSystemState state = states.get(expectedDay);

		assertNotNull(state.getShape());
		assertEquals(expectedDay, state.getDay());
		assertEquals(expectedState, state.getType());
		assertTrue(state.getShape().equals(expectedState));
		assertEquals(expectedIntensity, state.getIntensity());

		Map<SpatialSystemStateType, List<SpatialSystemState>> periods = calendar.periodCalculator(states);

		// calendar.reportYearlyStatus(conf.getMaxRangeDays(), periods);
		// calendar.reportMaxIntensity(states);
	}

	// @Test
	public void testAlgebraicImp1() {
		SpatialCalendarConfiguration conf = SpatialCalendarBuilderImp1.builder();

		SpatialCalendarSystem calendar = SpatialCalendarBuilder.builder().setPlanets(this.planetArray).setScale(scale)
				.setIncludeVertices(true).setRangeToBuild(this.expectedDay, this.expectedDay).build();

		log.info("reportDays: {}", conf.getMaxRangeDays());

		Map<Integer, SpatialSystemState> states = calendar.buildOneYearOfStates(conf.getPlanets(),
				conf.getMinRangeDays(), conf.getMaxRangeDays());

		SpatialSystemState state = states.get(expectedDay);

		assertNull(state.getShape());
		assertEquals(expectedDay, state.getDay());
		assertEquals(expectedState, state.getType());
		assertTrue(state.getType().equals(expectedState));
		assertEquals(expectedIntensity, state.getIntensity());

		Map<SpatialSystemStateType, List<SpatialSystemState>> periods = calendar.periodCalculator(states);

		// calendar.reportYearlyStatus(conf.getMaxRangeDays(), periods);
		// calendar.reportMaxIntensity(states);
		List<SpatialSystemState> switchs = calendar.buildSwitchStatusList(states);
		log.info("{}", switchs);

	}

}
