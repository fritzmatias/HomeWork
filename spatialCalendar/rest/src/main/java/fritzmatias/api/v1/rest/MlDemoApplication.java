package fritzmatias.api.v1.rest;

import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fritzmatias.core.SpatialCalendarBuilder;
import fritzmatias.core.model.Planet;


@SpringBootApplication
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
		
}
