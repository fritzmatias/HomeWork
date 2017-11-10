package fritzmatias.core.withpatterns.stateless.processors.spatial;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import fritzmatias.core.withpatterns.model.SpatialShapeContext;
import fritzmatias.core.withpatterns.model.SpatialShapeType;
import fritzmatias.patterns.type.TypeManager;
import fritzmatias.patterns.type.imp.FullScanTypeTesterAbstract;

/**
 * <p>
 * This class test all the {@link SpatialShapeType } registered into the
 * {@link TypeManager }.<br>
 * The type identification is exclusive, so an exception will be thrown if more
 * than one type matchs the input.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public class SpatialShapeExclusiveTypeCalculator
		extends FullScanTypeTesterAbstract<SpatialShapeContext, SpatialShapeType> {

	public SpatialShapeExclusiveTypeCalculator() {
		super();
	}

	public SpatialShapeExclusiveTypeCalculator(TypeManager<SpatialShapeType> m) {
		super(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.BiFunction#apply(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public ImmutableMap<? extends SpatialShapeType, Boolean> test(ImmutableSet<? extends SpatialShapeType> collection,
			SpatialShapeContext positions) {
		Map<SpatialShapeType, Boolean> results = collection.parallelStream()
				.collect(Collectors.toConcurrentMap(Function.identity(), x -> x.test(positions)));
		return ImmutableMap.<SpatialShapeType, Boolean>builder().putAll(results).build();
	}

}
