/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RunWith(SpringRunner.class)
@SpringBootTest 
@AutoConfigureMockMvc
public class ApplicationTests {

	private static final String REST_ROOT_PATH = "/";

	private static final String REST_CLIMA_PATH = REST_ROOT_PATH + "clima";
	private static final String REST_POPULATE_PATH = REST_ROOT_PATH + "populateJob";
	private static final String REST_REPORT_PATH = REST_ROOT_PATH + "report";
	private static final String REST_SYSTEM_PATH = REST_ROOT_PATH + "system";

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		populate();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @throws Exception
	 */
	public void populate() throws Exception {
		mockMvc.perform(delete(REST_SYSTEM_PATH).param("dia", "360"));
		mockMvc.perform(post(REST_POPULATE_PATH).param("dia", "360"));
	}

	@Test
	public void should01CreateRepositoryData() throws Exception {
		populate();
		mockMvc.perform(post(REST_POPULATE_PATH).param("dia", "360")).andDo(print()).andExpect(status().isForbidden());
		// .andExpect(jsonPath("$._links.people").exists());
	}

	@Test
	public void should02GetRepositoryData() throws Exception {
		populate();
		mockMvc.perform(get(REST_CLIMA_PATH).param("dia", "1")).andExpect(status().isOk());
	}

	@Test
	public void should03GetReportData() throws Exception {
		populate();
		mockMvc.perform(get(REST_REPORT_PATH).param("dia", "1")).andExpect(status().isOk());
	}

}
