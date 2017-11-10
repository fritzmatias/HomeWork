/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.Position;

@RunWith(Parameterized.class) 
@SpringBootTest
@AutoConfigureMockMvc
public class RESTParametrizedTest {

	// Manually config for spring to use Parameterised
	private TestContextManager testContextManager;

	@Parameter(0)
	public String name;
	@Parameter(1)
	public Integer dia;
	@Parameter(2)
	public String climaExpected;
	@Parameter(3)
	public Position position1Expected;
	@Parameter(4)
	public Position position2Expected;
	@Parameter(5)
	public Position position3Expected;

	@Autowired
	private MockMvc mockMvc;

	@Parameters(name="{0}")
	public static Collection<Object[]> data() {
		Collection<Object[]> params = new ArrayList<Object[]>();
		params.add(n("Inicial 0 sin valores", 0, "sequia", p1(0, 0), p2(0, 0), p3(0, 0)));
		return params;
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param x
	 * @param y
	 * @return
	 */
	private static Position p1(double x, double y) {
		return SpatialFactoryImp.createCartesianPosition(x, y);
	}
	private static Position p2(double x, double y) {
		return SpatialFactoryImp.createCartesianPosition(x, y);
	}
	private static Position p3(double x, double y) {
		return SpatialFactoryImp.createCartesianPosition(x, y);
	}

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param name
	 * @param dia
	 * @param climaExpected
	 * @param position1Expected
	 * @param position2Expected
	 * @param position3Expected
	 * @return
	 */
	private static Object[] n(String name, Integer dia, String climaExpected, Position position1Expected,
			Position position2Expected, Position position3Expected) {
		return new Object[] {name, dia, climaExpected, position1Expected, position2Expected, position3Expected};
	}

	public void populate() throws Exception {
		mockMvc.perform(delete(ApplicationTests.REST_SYSTEM_PATH).param("dia", "360"));
		mockMvc.perform(post(ApplicationTests.REST_POPULATE_PATH).param("dia", "360"));
	}

	@Before 
	public void setUp() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);
		populate();
	}

	@Test
	public void getClimaTest() throws Exception {
		populate();
		Integer dia = this.dia;
		String clima = this.climaExpected;
		
		mockMvc.perform(get(ApplicationTests.REST_CLIMA_PATH).param("dia", dia.toString())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.dia", is(equalTo(dia)))).andExpect(jsonPath("$.clima", is(equalTo(clima))));
	}

}