package fritzmatias.api.v1.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.convert.CustomConversions.StoreConversions;

import fritzmatias.api.v1.model.mongo.converters.ConverterVector2DReader;
import fritzmatias.api.v1.model.mongo.converters.ConverterVector2DWriter;
import fritzmatias.core.SpatialCalendarBuilder;
import fritzmatias.core.model.Planet;


//@EnableJpaRepositories
@SpringBootApplication
// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlDemoApplication.class, args);
	}

//	@Bean
//	public MongoTemplate mongoTemplate() throws IOException {
//		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
//		mongo.setBindIp("127.0.0.1");
//		MongoClient mongoClient = mongo.getObject();
//		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "some_database");
//		return mongoTemplate;
//	}

	@Bean
	public Set<Planet> createPlanets() {
		return SpatialCalendarBuilder.buildPlanets();
	}
	
	@Bean
	public CustomConversions customMongoConversions() {
		List<Converter<? extends Vector2D, ? extends Vector2D>> list = Arrays.asList(new ConverterVector2DReader(), new ConverterVector2DWriter());
		return new CustomConversions(CustomConversions.StoreConversions.NONE, list );
	}
	
}
