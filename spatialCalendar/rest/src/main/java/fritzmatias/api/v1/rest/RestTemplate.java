/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;



/**
 * <p>
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class RestTemplate {

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param converters
	 */
	public RestTemplate(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated constructor stub
	}

	@Bean
	public RestTemplate restTemplate() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//	    mapper.registerModule(new Jackson2HalModule());
//	    mapper.registerModule(new JodaModule());
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
//	    converter.setObjectMapper(mapper);
	    StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
	    stringConverter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/json"));
	    List<HttpMessageConverter<?>> converters = new ArrayList<>();
	    converters.add(converter);
	    converters.add(stringConverter);
	    return new RestTemplate(converters);
	}
}

