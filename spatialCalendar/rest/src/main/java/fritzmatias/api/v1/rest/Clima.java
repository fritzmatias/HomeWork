/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.rest;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Embeddable
public class Clima {
	@Id
	@Column(nullable=false)
	private Long dia;
	@Column(nullable=false)
	private String clima;

}
