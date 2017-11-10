package fritzmatias.core.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Sets;

import fritzmatias.core.SpatialCalendarConfiguration;
import fritzmatias.core.SpatialCalendarSystem;
import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialStateAbstractCalculator;
import fritzmatias.utils.Math;

/**
 * <p>
 * Refactored from class to interface, thanks to the existence of the Builder
 * previously.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialCalendarSystemImp1 implements SpatialCalendarSystem {
	private static Logger log = LoggerFactory.getLogger(SpatialCalendarSystemImp1.class);
	private SpatialCalendarConfiguration configuration;
	private SpatialStateAbstractCalculator stateCalculator;

	/**
	 * <p>
	 * </p>
	 * 
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
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param states
	 * @return
	 */
	public List<SpatialSystemState> reportMaxIntensity(Map<Integer, SpatialSystemState> states) {
		/*
		 * El perìodo de mayor intencidad se darà de manera periodica. Dicho
		 * periodo puere ser diferente a la cantidad de estados periòdicos del
		 * sistema. Tomar ese menor dìa del màx evaluar cuàl es el periodo menor
		 * que mayor que èl. => MAX(dia) < MIN(DIASxAño)
		 */
		Map<Long, List<SpatialSystemState>> r = states.values().stream()
				.filter(p -> p.getType() == SpatialSystemStateType.TrianguloEncierraOrigen)
				.collect(Collectors.groupingBy(p -> p.getIntensity()));
		if (r == null) {
			return Collections.emptyList();
		}

		Entry<Long, List<SpatialSystemState>> max = null;
		try {
			max = r.entrySet().stream().max((a, b) -> Long.compare(a.getKey(), b.getKey())).get();
			if (max != null) {
				Collections.sort(max.getValue(), (a, b) -> Integer.compare(a.getDay(), b.getDay()));
				log.info("Max :{}, List: {}", max.getKey(), max.getValue());
				return max.getValue();
			}
		} catch (NoSuchElementException e) {
		}

		return Collections.emptyList();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param days
	 * @param periods
	 * @return list of strings
	 */
	public List<String> reportYearlyStatus(final int days,
			Map<SpatialSystemStateType, List<SpatialSystemState>> periods) {
		int years = (days / configuration.getDaysPerYear()) + 1;
		List<String> list = new ArrayList<String>(years * periods.size());

		for (int y = 1; y <= years; y++) {
			log.info("Report Year {} ", years);
			list.add("Report Year " + y);
			periods.entrySet().forEach(e -> {
				log.info("{} periods: {}, ", e.getKey(), e.getValue().size());
				list.add(new StringBuilder(e.getKey().getFormalStatusName()).append(" periods: ")
						.append(e.getValue().size()).toString());
			});
		}
		return list;
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
	 * Builds a number of states related to the amount of days for each
	 * planet.<br>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param planetset
	 * @param stateCalculator
	 * @param maxRange
	 * @return
	 */
	public Map<Integer, SpatialSystemState> buildOneYearOfStates(ImmutableCollection<Planet> planetset, int minRange,
			int maxRange) {
		Set<Integer> m = builderStreamOfDays(minRange, maxRange);

		Map<Integer, SpatialSystemState> states = m.parallelStream()
				.collect(Collectors.toConcurrentMap(Function.identity(), day -> stateCalculator.apply(day, planetset)));
		return states;
	}

	/**
	 * <p>
	 * Build a stream of days from 0 up to maxDìas exclusive.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param maxDias
	 *            > 0
	 * @return a stream between 0 and Max(1,maxDays)
	 */
	private static Set<Integer> builderStreamOfDays(int minDias, int maxDias) {
		return IntStream.rangeClosed(Math.max(0, minDias), Math.max(1, maxDias)).parallel().boxed()
				.collect(Collectors.toCollection(() -> Sets.newConcurrentHashSet()));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.SpatialCalendarSystem#setStateCalculator(fritzmatias.
	 * core.withpatterns.stateless.processors.spatial.
	 * SpatialStateAbstractCalculator)
	 */
	@Override
	public SpatialCalendarSystem setStateCalculator(SpatialStateAbstractCalculator stateCalculator) {
		this.stateCalculator = stateCalculator;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.SpatialCalendarSystem#register(java.util.List)
	 */
	@Override
	public void register(Collection<SpatialShapeType> createShapeTypes) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Implemented.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.SpatialCalendarSystem#getStateCalculator()
	 */
	@Override
	public SpatialStateAbstractCalculator getStateCalculator() {
		return this.stateCalculator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.SpatialCalendarSystem#setStateCalculator(java.util.
	 * function.BiFunction)
	 */
	@Override
	public void setStateCalculator(
			BiFunction<Integer, ImmutableCollection<Planet>, SpatialSystemState> stateCalculator) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Implemented.");

	}

	/* (non-Javadoc)
	 * @see fritzmatias.core.SpatialCalendarSystem#calculateStates()
	 */
	@Override
	public Map<Integer, SpatialSystemState> calculateStates() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Implemented.");
	}

	/* (non-Javadoc)
	 * @see fritzmatias.core.SpatialCalendarSystem#periodShapeCalculator(java.util.Map)
	 */
	@Override
	public Map<SpatialShapeType, List<SpatialSystemState>> periodShapeCalculator(
			Map<Integer, SpatialSystemState> states) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Implemented.");
	}


}
