package fritzmatias.patterns.buider.imp;

import java.util.Map;

import com.google.common.collect.Maps;

import fritzmatias.patterns.buider.Builder;

/**
 * <p>
 * Mutable, Builder<br>
 * This class is responsible to collect all the parameters to be used in the
 * {@link Builder#build()} implementation<br>
 * If the idea is to define a builder behavior to an abstract class, then, the
 * right way is using {@link ImmutableBuilderConfigurableAbstract} in the abstract.<br>
 * Multiple calls to {@link #build()}, should return a new instances of
 * &lt;T&gt, but is not guarantee of the equality between two objects builded by
 * {@link #build()}. This is because the mutability of this implementation, so a
 * modification on any property can be made at any point in time.
 * </p>
 * <p>
 * All the interactions are through {@link #set(String, Object)} and
 * {@link #get(String)} {@link #getOptional(String)} keeping a constant way to
 * change or extends the builder behavior.
 * </p>
 * 
 * @see {@link ImmutableBuilderAbstract} for immutable {@link #build()}
 * @author user
 * @since Version: 2
 * @param <T>
 *            the type to be build.
 */
public abstract class BuilderAbstract<T> extends PropertyMap implements Builder<T> {
	public BuilderAbstract() {
	}

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param map
	 */
	public BuilderAbstract(Map<String, Object> map) {
		super(map);
	}

	/**
	 * <p>
	 * Creates the specific instance once build() is called
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	protected abstract T create();

	@Override
	public T build() {
		return create();
	}
}
