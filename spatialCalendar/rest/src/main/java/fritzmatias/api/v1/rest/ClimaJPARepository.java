/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RepositoryRestResource(collectionResourceRel = "climarepo", path = "clima")
public interface ClimaJPARepository extends PagingAndSortingRepository<ClimaJPAPersistence, Long> {

	List<ClimaJPAPersistence> findByDia(@Param("dia") Long dia);
}
