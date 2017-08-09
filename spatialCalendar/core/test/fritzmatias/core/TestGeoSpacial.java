package fritzmatias.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.stateless.processors.spatial.SpatialSystemCenterdSunStateCalculator;
import fritzmatias.core.model.Planet;

public class TestGeoSpacial {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void restaPuntos() {

	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	@Test
	public void fullTest() {
		final int years = 2;
		final Set<Planet> planetset = createPlanets();
		SpatialSystemCenterdSunStateCalculator stateCalculator = SpatialObjectFactory.createSpatialSystemCenteredSunProcessor(5);
		
		// Define the size of the collection for big amount of data,is more
		// performant on memory request.
		int maxDias = planetset.stream().map(p -> {
			int d = p.getDaysPerYear();
//			log.info("Planet: {}, daysPerYear: {}", p, d);
			return d;
		}).max(Integer::compare).get();
		int diasReporte = maxDias * Math.max(1, years);

		log.info("dias: {}, reportDays: {}", maxDias, diasReporte);

		// ** FIN CONFIG

		Map<Integer, SpatialSystemState> states = buildSystemStates(planetset, stateCalculator, maxDias);

		/// FIN  Generator 		
		Map<SpatialSystemStateType, List<SpatialSystemState>> periods = periodCalculator(states);
		
		reportYearlyStatus(years, periods);
		reportMaxIntensity(states);

	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @return
	 */
	public Set<Planet> createPlanets() {
		final Set<Planet> planetset = new HashSet<Planet>(3);
		planetset.add(SpatialObjectFactory.createPlanet("F", 1, AngularDirection.Hour, 300));
		planetset.add(SpatialObjectFactory.createPlanet("V", 3, AngularDirection.Hour, 2000));
		planetset.add(SpatialObjectFactory.createPlanet("I", 5, AngularDirection.AntiHour, 1000));
		return planetset;
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 */
	public void debugByType(Map<Integer, SpatialSystemState> states) {
		Map<Integer, SpatialSystemState> rectasS = states.entrySet().stream()
				.filter(e -> (e.getValue().getType() == SpatialSystemStateType.RectaConPunto)).map(e -> {
					log.info("Recta con origen-- dia: {}", e.getKey());
					return e;
				}).collect(HashMap::new, (map, e) -> map.put(e.getKey(), e.getValue()),
						(map1, map2) -> map1.putAll(map2));
		Map<Integer, SpatialSystemState> rectasC = states.entrySet().stream()
				.filter(e -> (e.getValue().getType() == SpatialSystemStateType.RectaSinPunto)).map(e -> {
					log.info("Recta Sin origen-- dia: {}", e.getKey());
					return e;
				}).collect(HashMap::new, (map, e) -> map.put(e.getKey(), e.getValue()),
						(map1, map2) -> map1.putAll(map2));
		Map<Integer, SpatialSystemState> trianguloC = states.entrySet().stream()
				.filter(e -> (e.getValue().getType() == SpatialSystemStateType.TrianguloEncierraOrigen)).map(e -> {
					log.info("TrianguloConOrigen-- dia: {}", e.getKey());
					return e;
				}).collect(HashMap::new, (map, e) -> map.put(e.getKey(), e.getValue()),
						(map1, map2) -> map1.putAll(map2));
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 */
	public void reportMaxIntensity(Map<Integer, SpatialSystemState> states) {
		/*
		 * El perìodo de mayor intencidad se darà de manera periodica. Dicho
		 * periodo puere ser diferente a la cantidad de estados periòdicos del
		 * sistema. Tomar ese menor dìa del màx evaluar cuàl es el periodo menor
		 * que mayor que èl. => MAX(dia) < MIN(DIASxAño)
		 */
		// TODO:
		// https://stackoverflow.com/questions/29334404/how-to-force-max-to-return-all-maximum-values-in-a-java-stream
		Map<Long, List<SpatialSystemState>> r = states.values().stream()
				.filter(p -> p.getType() == SpatialSystemStateType.TrianguloEncierraOrigen)
				.collect(Collectors.groupingBy(p -> p.getIntensity()));
		Long max = r.keySet().stream().max(Long::compare).get();

		Collections.sort(r.get(max), (a, b) -> Integer.compare(a.getDay(), b.getDay()));
		log.info("Max :{}, List: {}", max, r.get(max));
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param years
	 * @param periods
	 */
	public void reportYearlyStatus(final int years,
			Map<SpatialSystemStateType, List<SpatialSystemState>> periods) {
		for (int y = 1; y <= years; y++) {
			log.info("Report Year {} ", y);
			periods.entrySet().forEach(e -> log.info("{} periods: {}, ", e.getValue().size(), e.getKey()));

			/// TODO: No se da el caso en que el ùltimo switch sea igual al
			/// primero del perìodo siguiente para esta estructura de planetas
			/// en particular. De cambiar, es necesario garantizar que el ùltimo
			/// elemento periodo del periodo calculado
			/// de ser igual al primero del perìodo siguiente, debe evitarse la
			/// contabilizaciòn del primer valor en el siguiente periodo.

			// if ( switchs.size() > 0 && switchs.get(0).getType() ==
			// switchs.get(switchs.size() - 1).getType() ) {
			// periods.entrySet().forEach(e -> log.info("{} periods: {}, ",
			// e.getValue().size(), e.getKey()));
			// }else {
			// periods.entrySet().forEach(e -> log.info("{} periods: {}, ",
			// e.getValue().size(), e.getKey()));
			// }
		}
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 * @return
	 */
	public Map<SpatialSystemStateType, List<SpatialSystemState>> periodCalculator(
			Map<Integer, SpatialSystemState> states) {
		List<SpatialSystemState> switchs = buildSwitchStatusList(states);
		switchs.forEach(x -> log.info("Clima Switched -- dia: {}, type: {}, intensity: {}", x.getDay(), x.getType(),
				x.getIntensity()));
		Map<SpatialSystemStateType, List<SpatialSystemState>> periods = switchs.stream()
				.collect(Collectors.groupingBy(s -> s.getType()));
		return periods;
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param planetset
	 * @param stateCalculator
	 * @param maxDias the required days >= 0
	 * @return
	 */
	public Map<Integer, SpatialSystemState> buildSystemStates(final Set<Planet> planetset,
			SpatialSystemCenterdSunStateCalculator stateCalculator, int maxDias) {
		Set<Integer> m = daysBuilder(maxDias);
		
		Map<Integer, SpatialSystemState> states = m.parallelStream()
				.collect(Collectors.toConcurrentMap(Function.identity(), day -> stateCalculator.apply(day, planetset)));
		return states;
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param maxDias
	 * @return
	 */
	public HashSet<Integer> daysBuilder(int maxDias) {
		return IntStream.range(0, maxDias).collect(HashSet<Integer>::new, (set, d) -> {
			set.add(d);
			log.info("add {}", d);
		}, (set1, set2) -> {
			set1.addAll(set2);
			log.info("merge");
		});
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 * @return
	 */
	public List<SpatialSystemState> buildSwitchStatusList(Map<Integer, SpatialSystemState> states) {
		// Aqui la secuencialidad es determinante, por lo que se evita el uso de
		// streams.
		Entry<Integer, SpatialSystemState> prev = null;
		List<SpatialSystemState> switches = new ArrayList<SpatialSystemState>();
		for (Entry<Integer, SpatialSystemState> e : states.entrySet()) {
			if (prev == null || prev.getValue().getType() != e.getValue().getType()) {
				switches.add(e.getValue());
			}
			prev = e;
		}
		return switches;
	}
}
