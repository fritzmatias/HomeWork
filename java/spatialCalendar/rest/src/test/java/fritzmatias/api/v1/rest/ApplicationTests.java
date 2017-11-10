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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

	public static final MediaType halJSON = new MediaType("application", "hal+json", Charset.forName("UTF-8"));
	public static final String REST_ROOT_PATH = "/";

	public static final String REST_CLIMA_PATH = REST_ROOT_PATH + "clima";
	public static final String REST_POPULATE_PATH = REST_ROOT_PATH + "populateJob";
	public static final String REST_REPORT_PATH = REST_ROOT_PATH + "report";
	public static final String REST_SYSTEM_PATH = REST_ROOT_PATH + "system";

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
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

	@Test
	public void jsonGetRespnse() throws Exception {
		populate();
		Long dia = 0L;
		String clima = "sequia";
		mockMvc.perform(get(REST_CLIMA_PATH).param("dia", dia.toString())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.dia", is(equalTo(0)))).andExpect(jsonPath("$.clima", is(equalTo(clima))));
		
//		.andExpect(jsonPath("$", hasSize(1)))
	}

	// public void addTodoWhenTitleAndDescriptionAreTooLong() throws Exception {
	// String title = TodoTestUtil.createStringWithLength(101);
	// String description = TodoTestUtil.createStringWithLength(501);
	// TodoDTO added = TodoTestUtil.createDTO(null, description, title);
	//
	// mockMvc.perform(post("/api/todo").contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
	// .body(IntegrationTestUtil.convertObjectToJsonBytes(added))
	// .with(userDetailsService(IntegrationTestUtil.CORRECT_USERNAME))).andExpect(status().isBadRequest())
	// .andExpect(content().mimeType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
	// .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
	// .andExpect(jsonPath("$.fieldErrors[*].path", containsInAnyOrder("title",
	// "description")))
	// .andExpect(jsonPath("$.fieldErrors[*].message",
	// containsInAnyOrder("The maximum length of the description is 500
	// characters.",
	// "The maximum length of the title is 100 characters.")));
	// }

}
