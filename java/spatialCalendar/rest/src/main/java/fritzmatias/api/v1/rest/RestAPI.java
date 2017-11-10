/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ActuatorMetricWriter;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableCollection;

import fritzmatias.api.v1.rest.exceptions.RESTNotAllowOperation;
import fritzmatias.api.v1.rest.exceptions.RESTResourceNotFound;
import fritzmatias.core.SpatialCalendarBuilder;
import fritzmatias.core.SpatialCalendarConfiguration;
import fritzmatias.core.SpatialCalendarSystem;
import fritzmatias.core.model.Planet;
import fritzmatias.core.model.SpatialSystemState;
import fritzmatias.core.model.SpatialSystemStateType;

/**
 * <p>
 * </p>
 * 
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RestController
@RestResource(exported=true, path="test", rel="testrel")
@RequestMapping
public class RestAPI {
	@Autowired
	private ClimaJPARepository repo;
	@Autowired
	private SpatialSystemStateRepository repoStates;
	public ImmutableCollection<Planet> planets;

	@Resource(name = "createPlanets")
	public void setPlanets(ImmutableCollection<Planet> planets) {
		this.planets = planets;
	}

	private int maxBuildDay = 0;
	private SpatialCalendarConfiguration conf = SpatialCalendarBuilder.builder();
	private int minBuildDay = 0;

	@ActuatorMetricWriter
	@RequestMapping(method = RequestMethod.GET, value = "/clima")
	public ResponseEntity<Clima> getDia(@RequestParam(required = true, name = "dia") Long dia,
			HttpServletResponse response) {
		Clima c = new Clima();
		c.setDia(-1L);
		c.setClima("No Valid Value");		
		
		if (dia == null || dia < 0) {
			throw new IllegalArgumentException("dia should be set with values >= 0");
		}

		SpatialCalendarSystem calendar=SpatialCalendarBuilder.builder()
				.setPlanets(planets)
				.setIncludeVertices(true)
				.build();
		
		

		
		List<ClimaJPAPersistence> list = repo.findByDia(dia % conf.getDaysPerYear());
		if (list.size() == 0) {
			throw new RESTResourceNotFound("The dia required was not calculated. Try to build at least one year.");
		}
		Clima r = list.get(0);
		c.setDia(dia);

		c.setClima(r.getClima());
		return new ResponseEntity<Clima>(c, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clima2")
	public Clima getDia2(@RequestParam(required = true, name = "dia") Long dia,
			HttpServletResponse response) {
		Clima c = new Clima();
		c.setDia(-1L);
		c.setClima("No Valid Value");

		if (dia == null || dia < 0) {
			throw new IllegalArgumentException("dia should be set with values >= 0");
		}

		SpatialCalendarSystem calendar=SpatialCalendarBuilder.builder()
				.setPlanets(planets)
				.setIncludeVertices(true)
				.build();

		
		List<ClimaJPAPersistence> list = repo.findByDia(dia % conf.getDaysPerYear());
		if (list.size() == 0) {
			throw new RESTResourceNotFound("The dia required was not calculated. Try to build at least one year.");
		}
		Clima r = list.get(0);
		c.setDia(dia);

		c.setClima(r.getClima());
		return c;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clima3")
	public ClimaHATEOASResource getDia3(@RequestParam(required = true, name = "dia") Long dia,
			HttpServletResponse response) {
		Clima c = new Clima();
		c.setDia(-1L);
		c.setClima("No Valid Value");

		if (dia == null || dia < 0) {
			throw new IllegalArgumentException("dia should be set with values >= 0");
		}

		SpatialCalendarSystem calendar=SpatialCalendarBuilder.builder()
				.setPlanets(planets)
				.setIncludeVertices(true)
				.build();

		
		List<ClimaJPAPersistence> list = repo.findByDia(dia % conf.getDaysPerYear());
		if (list.size() == 0) {
			throw new RESTResourceNotFound("The dia required was not calculated. Try to build at least one year.");
		}
		Clima r = list.get(0);
		c.setDia(dia);
		
		c.setClima(r.getClima());
		return new ClimaHATEOASResource(r);
	}

	
	/**
	 * <p>
	 * No es muy feliz pero es la forma màs rápida de crear un job de generación
	 * y persistencia de datos.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param dia
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/populateJob")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<List<String>> job(@RequestParam(required = true) Long dia) {
		List<String> output = new ArrayList<String>(500);
		output.add(
				"Forma facil de emular el call a un job: a continuacion los logs resultantes del procesamiento a demanda.");

		if (conf.getPlanets() == null) {
			conf.setPlanets(planets);
		}
				
		if (dia == null || dia < 0 || dia >= Long.MAX_VALUE) {
			throw new IllegalArgumentException("The dia parameter should be >=0 < Long.MAX_VALUE");
		}
		if (((conf.getDaysPerYear() - maxBuildDay) <= 0)) {
			throw new RESTNotAllowOperation("All the possible values were generated.");
		}
		if (dia - maxBuildDay <= 0 && dia != 0) {
			throw new IllegalArgumentException("The required element was created before.");
		}

		SpatialCalendarSystem calendar=SpatialCalendarBuilder.builder()
				.setPlanets(planets)
				.setIncludeVertices(true)
				.build();
		
		
		// Local Serialization of the call. I am asuming that is only one endpoint offering this resource.
		maxBuildDay = Math.min(dia.intValue(), conf.getDaysPerYear());
		conf.setRangeToBuild(minBuildDay, maxBuildDay);
				
		Map<Integer, SpatialSystemState> states = calendar.buildOneYearOfStates(conf.getPlanets(),
				conf.getMinRangeDays(), conf.getMaxRangeDays());

		// Reporte REST
		states.values().forEach(x -> output.add(x.toString()));

		// Store on SQL DB with JPA, and NSQLDB MongoDB only for testing both.
		// TODO: parallelStream.
//		for (java.util.Map.Entry<Integer, SpatialSystemState> e : states.entrySet()) {
		states.entrySet().parallelStream().map(e-> {
			ClimaJPAPersistence c = new ClimaJPAPersistence();
			c.setClima(e.getValue().getType().getFormalStatusName());
			c.setDia(e.getKey().longValue());
			c.setIntensity(e.getValue().getIntensity());

			// No transactions used.
			repo.save(c);
			repoStates.save(e.getValue());
			return e;
		}).collect(Collectors.toList());
//		}

		minBuildDay = maxBuildDay;
		return new ResponseEntity<List<String>>(output, HttpStatus.CREATED);
		// }
		// return output;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/report")
	@ResponseBody
	public ResponseEntity<Map<String, List<String>>> report(@RequestParam Long dia) {

		if (dia == null || dia < 0 || dia >= Long.MAX_VALUE) {
			throw new IllegalArgumentException("The dia parameter should be >=0 < Long.MAX_VALUE");
		}

		Map<String, List<String>> response = new HashMap();
		
		Map<Integer, ClimaJPAPersistence> jpaStates = new HashMap<Integer, ClimaJPAPersistence>();
		repo.findAll().forEach(t -> jpaStates.put(t.getDia().intValue(), t));

		Map<Integer, SpatialSystemState> states = jpaStates.values().stream().map(x -> {
			SpatialSystemState state = new SpatialSystemState();
			state.setDay(x.getDia().intValue());
			state.setIntensity(x.getIntensity());
			for (SpatialSystemStateType s : SpatialSystemStateType.values()) {
				if (s.getFormalStatusName().equalsIgnoreCase(x.getClima())) {
					state.setType(s);
					return state;
				}
			}
			state.setType(SpatialSystemStateType.indefinido);
			return state;
		}).collect(Collectors.toConcurrentMap(state -> state.getDay(), Function.identity()));

		SpatialCalendarSystem calendar=SpatialCalendarBuilder.builder()
				.setPlanets(planets)
				.setIncludeVertices(true)
				.build();

		Map<SpatialSystemStateType, List<SpatialSystemState>> groups = calendar.periodCalculator(states);

		List<SpatialSystemState> maxintensity = calendar.reportMaxIntensity(states);

		if (maxintensity.size() == 0) {
			throw new RESTResourceNotFound(
					"Not enougth data builded to build the report. Please, build at least one year.");
		}
		// Reporte REST
		response.put("Maxima intensidad de " + SpatialSystemStateType.TrianguloEncierraOrigen.getFormalStatusName(),
				maxintensity.stream().map(s -> {
					Map<String, Long> map = new HashMap();
					map.put("dia", (long) s.getDay());
					map.put("intensity", s.getIntensity());
					return map.toString();
				}).collect(Collectors.toList()));

		response.put("Reporte de estado del clima de dìas:" + dia, calendar.reportYearlyStatus(dia.intValue(), groups));

		return new ResponseEntity<Map<String, List<String>>>(response, HttpStatus.OK);
	}

	// @ResponseStatus(code=HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE, value = "/system")
	public void reset() {
		repo.deleteAll();
		repoStates.deleteAll();
		conf.setRangeToBuild(0, 0);
		this.maxBuildDay = 0;
		this.minBuildDay = 0;
	}
}
