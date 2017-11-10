/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.patterns.type.imp;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import fritzmatias.patterns.type.TypeManager;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @param <K>
 *            the type to evaluate equality of managed objects
 * @param <T>
 *            the type of the managed objects
 * @since Version: 2
 */
public abstract class TypeManagerAbstract<K, T> implements TypeManager<T> {

	private Map<K, T> typeStorage;
	private T defaultType = null;

	public TypeManagerAbstract() {
		super();
		typeStorage = Maps.newConcurrentMap();
	}

	TypeManagerAbstract(Map<K, T> map) {
		super();
		typeStorage = map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SpatialSystemStateTypeChainManager#
	 * register(fritzmatias.core.withpatterns.model.SpatialSystemStateType)
	 */
	@Override
	public TypeManager<T> register(T type) {

		if (type == null) {
			throw new IllegalArgumentException("Is not valid register null type.");
		}

		if (this.getDefaultType() != null && this.getDefaultType().equals(type)) {
			throw new IllegalArgumentException("Is not valid register a type equals to the default type.");
		}

		if (this.typeStorage.put(getType(type), type) != null) {
			throw new IllegalArgumentException("Is not valid register more than one class for the same type.");
		}
		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param type
	 * @return the value used to compare equality of types
	 * @see #equals(Object) {@link #hashCode()}
	 */
	public abstract K getType(T type);

	/* (non-Javadoc)
	 * @see fritzmatias.core.withpatterns.model.TypeManager#getTypes()
	 */
	public ImmutableSet<? extends T> getTypes() {
		return ImmutableSet.<T>builder().addAll(typeStorage.values()).build();
	}

	/**
	 * <p>
	 * Set the types of storage for thread safety.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param typeStorage
	 */
	public void setTypeStorage(Map<K, T> typeStorage) {
		this.typeStorage = typeStorage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SpatialSystemStateTypeChainManager#
	 * getDefaultType()
	 */
	@Override
	public T getDefaultType() {
		return this.defaultType;
	}

	public TypeManager<T> setDefaultType(T type) {
		this.defaultType = type;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.model.SpatialShapeManager#register(java.
	 * util.Collection)
	 */
	@Override
	public TypeManager<T> register(Collection<? extends T> types) {
		types.stream().forEach(t -> this.register(t));
		return this;
	}

}
