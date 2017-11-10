package fritzmatias.core.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fritzmatias.core.SpatialCalendarConfiguration;
import fritzmatias.core.SpatialCalendarSystem;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpLineWithPointInside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpLineWithPointInside2;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpLineWithPointOutside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpLineWithPointOutside2;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpTriangleWithPointInside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpTriangleWithPointInside2;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpTriangleWithPointOutside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypeCartesianImpTriangleWithPointOutside2;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypePolarImpLineWithPointInside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypePolarImpLineWithPointOutside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypePolarImpTriangleWithPointInside;
import fritzmatias.core.withpatterns.model.shapes.imp.SpatialShapeTypePolarImpTriangleWithPointOutside;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialPlanetPositionAbstractCalculator;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialShapeExclusiveTypeCalculator;
import fritzmatias.core.withpatterns.stateless.processors.spatial.SpatialStateAbstractCalculator;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialCalendarBuilderImp2 extends SpatialCalendarAbstractBuilder {

	public static SpatialCalendarConfiguration builder() {
		SpatialCalendarConfigurationImp config = new SpatialCalendarConfigurationImp(SpatialCalendarBuilderImp2::new);
		return config;
	}

	public static Collection<SpatialShapeType> knownLogicalShapeTypes() {
		Collection<SpatialShapeType> list = new ArrayList<SpatialShapeType>();
		list.add(new SpatialShapeTypePolarImpLineWithPointInside());
		list.add(new SpatialShapeTypePolarImpLineWithPointOutside());
		list.add(new SpatialShapeTypePolarImpTriangleWithPointInside());
		list.add(new SpatialShapeTypePolarImpTriangleWithPointOutside());

		return Collections.unmodifiableCollection(list);
	}

	public static Collection<SpatialShapeType> knownAlgebraicShapeTypes() {
		return knownAlgebraicShapeTypes2();
	}

	public static Collection<SpatialShapeType> knownAlgebraicShapeTypes1() {
		Collection<SpatialShapeType> list = new ArrayList<SpatialShapeType>();
		list.add(new SpatialShapeTypeCartesianImpLineWithPointInside());
		list.add(new SpatialShapeTypeCartesianImpLineWithPointOutside());
		list.add(new SpatialShapeTypeCartesianImpTriangleWithPointInside());
		list.add(new SpatialShapeTypeCartesianImpTriangleWithPointOutside());

		return Collections.unmodifiableCollection(list);
	}

	public static Collection<SpatialShapeType> knownAlgebraicShapeTypes2() {
		Collection<SpatialShapeType> list = new ArrayList<SpatialShapeType>();
		list.add(new SpatialShapeTypeCartesianImpLineWithPointInside2());
		list.add(new SpatialShapeTypeCartesianImpLineWithPointOutside2());
		list.add(new SpatialShapeTypeCartesianImpTriangleWithPointInside2());
		list.add(new SpatialShapeTypeCartesianImpTriangleWithPointOutside2());

		return Collections.unmodifiableCollection(list);
	}

	@Override
	public SpatialCalendarSystem create() {
		return this.buildCartesian();
	}

	public SpatialCalendarSystem buildCartesian() {
		SpatialPlanetPositionAbstractCalculator positionCalculator = SpatialFactoryImp
				.createCircularPositionCalculator(getConfig().getScale())
				.setPolarSupplier(SpatialFactoryImp.createPolarPositionSupplier());

		SpatialShapeExclusiveTypeCalculator shapeCalulator = SpatialFactoryImp.shapeClasificatorBuilder()
				.setManager(SpatialFactoryImp.createShapeManager()).build();

		SpatialStateAbstractCalculator stateCalculator = SpatialFactoryImp
				.createSpatialSystemCenteredSunAlgebraicCalculator().setScale(getConfig().getScale())
				.setPositionCalculator(positionCalculator).setShapeCalculator(shapeCalulator);

		SpatialCalendarSystem calendar = new SpatialCalendarSystemImp2().setConfig(getConfig())
				.setStateCalculator(stateCalculator);

		return calendar;
	}

}
