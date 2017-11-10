/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import fritzmatias.core.imp.SpatialCalendarBuilderImp2;

/**
 * <p>
 * Pattern: Proxy
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialCalendarBuilder {

	public static SpatialCalendarConfiguration builder() {
		return SpatialCalendarBuilderImp2.builder();
	}

}
