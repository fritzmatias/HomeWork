/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@Data
@Entity
@Table(name="Clima")
public class ClimaJPAPersistence extends Clima {
	Long intensity;
}
