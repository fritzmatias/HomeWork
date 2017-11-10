/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.imp;

import fritzmatias.core.SpatialCalendarConfiguration;
import fritzmatias.core.SpatialCalendarSystem;
import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.stateless.processors.spatial.SpatialShapeCalculator;
import fritzmatias.core.stateless.processors.spatial.SpatialSystemCenterdSunStateCalculator;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
// FIX: Eliminar
public class SpatialCalendarBuilderImp1 extends SpatialCalendarAbstractBuilder {

	public static SpatialCalendarConfiguration builder() {
		SpatialCalendarConfigurationImp config = new SpatialCalendarConfigurationImp(SpatialCalendarBuilderImp1::new);
		return config;
	}

	@Override
	public SpatialCalendarSystem create() {

		SpatialCalendarSystem calendar = new SpatialCalendarSystemImp1();

		SpatialShapeCalculator shapeCalculator = SpatialObjectFactory
				.createShapeCalculator(getConfig().isIncludeVertices());
		shapeCalculator.setScale(getConfig().getScale());

		SpatialSystemCenterdSunStateCalculator stateCalculator = SpatialObjectFactory
				.createSpatialSystemCenteredSunProcessor(getConfig().getScale()).setShapeCalculator(shapeCalculator);

		// calendar.setStateCalculator(stfateCalculator);
		return calendar;
	}

}
