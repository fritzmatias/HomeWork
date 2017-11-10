package fritzmatias.api.v1.rest;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Planet;
import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;


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
	public ImmutableCollection<Planet> createPlanets() {
		Collection<Planet> list=new ArrayList<Planet>();
		return ImmutableList.<Planet>builder()
				.add(SpatialObjectFactory.createPlanet("A",1,AngularDirection.Hour,500))
				.add(SpatialObjectFactory.createPlanet("B",3,AngularDirection.Hour,2000))
				.add(SpatialObjectFactory.createPlanet("C",5,AngularDirection.AntiHour,1000))
				.build();
	}
		
}
