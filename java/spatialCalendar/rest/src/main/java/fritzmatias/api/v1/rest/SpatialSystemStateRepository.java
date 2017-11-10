/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fritzmatias.core.model.SpatialSystemState;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RepositoryRestResource(exported=false,collectionResourceRel = "states", path = "state")
public interface SpatialSystemStateRepository extends MongoRepository<SpatialSystemState, Long> {
	
}
