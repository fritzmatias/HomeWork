/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.withpatterns.builder;

import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialShapeExclusiveTypeCalculator;
import fritzmatias.patterns.buider.imp.ImmutableBuilderAbstract;
import fritzmatias.patterns.type.TypeManager;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public class SpatialShapeClasificatorBuilder extends ImmutableBuilderAbstract<SpatialShapeExclusiveTypeCalculator> {
	private static final String MANAGER = "manager";

	public static SpatialShapeClasificatorBuilder builder() {
		return new SpatialShapeClasificatorBuilder();
	}
	
	public SpatialShapeClasificatorBuilder setManager(TypeManager<SpatialShapeType> manager) {
		set(MANAGER, manager);
		return this;
	}

	protected TypeManager<SpatialShapeType> getManager() {
		return (TypeManager<SpatialShapeType>) get(MANAGER);
	}

	@Override
	protected SpatialShapeExclusiveTypeCalculator create() {
		SpatialShapeExclusiveTypeCalculator o = new SpatialShapeExclusiveTypeCalculator();
		o.setManager(getManager());

		return o;
	}

}
