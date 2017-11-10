package fritzmatias.patterns.type;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 2
 * @param <T>
 */
public interface TypeManager<T> {
	/**
	 * <p>
	 * Add a new type to the manager.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param type
	 * @return this
	 */
	public TypeManager<T> register(T type);

	public TypeManager<T> register(Collection<? extends T> types);

	/**
	 * <p>
	 * Get the types available into the manager without the default type.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @return all the types registered. The default type is not included.
	 */
	public ImmutableSet<? extends T> getTypes();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @return the default type
	 */
	public T getDefaultType();

}
