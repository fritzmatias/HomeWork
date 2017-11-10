/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.ResourceSupport;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class ClimaHATEOASResource extends ResourceSupport {
	private final Clima clima;
	
	public ClimaHATEOASResource(Clima clima) {
		super();
		this.clima=clima;
		this.add(new Link(RestAPI.class.toString()));
	}

	@Bean
	public ResourceProcessor<Resource<Clima>> personProcessor() {

		return new ResourceProcessor<Resource<Clima>>() {

			@Override
			public Resource<Clima> process(Resource<Clima> resource) {

				resource.add(new Link("http://localhost:8080/clima", "added-link"));
				return resource;
			}
		};
	}

	@Bean
	public ResourceProcessor<ClimaHATEOASResource> climaProcessor() {

		return new ResourceProcessor<ClimaHATEOASResource>() {

			@Override
			public ClimaHATEOASResource process(ClimaHATEOASResource resource) {

				resource.add(new Link("http://localhost:8080/clima", "added-link"));
				return resource;
			}
		};
	}
	
	public Clima getClima() {
		return clima;
	}
}
