/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.shapes.imp;

import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.SpatialShapeContext;

/**
 * <p>
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialShapeTypeImpUndefined extends SpatialShapeType {

	public SpatialShapeTypeImpUndefined() {
		super("Undefined");
	}

	@Override
	public boolean test(SpatialShapeContext positions) {		
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public SpatialShapeContext apply(SpatialShapeContext t) {
		return t;
	}

}
