/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeImpUndefined;
import fritzmatias.patterns.type.imp.TypeManagerAbstract;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialShapeManagerImp extends TypeManagerAbstract<String, SpatialShapeType> {

	public SpatialShapeManagerImp() {
		super();
		super.setDefaultType(new SpatialShapeTypeImpUndefined());
	}

	/* (non-Javadoc)
	 * @see fritzmatias.core.withpatterns.model.imp.TypeManagerAbstract#getType(java.lang.Object)
	 */
	@Override
	public String getType(SpatialShapeType type) {
		return type.getFormalStatusName();
	}

}
