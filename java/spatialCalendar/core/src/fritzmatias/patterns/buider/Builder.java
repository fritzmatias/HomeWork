/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.patterns.buider;

import fritzmatias.patterns.buider.imp.BuilderAbstract;
import fritzmatias.patterns.buider.imp.ImmutableBuilderAbstract;

/**
 * <p>
 * This interface defines a simple builder contract.<br>
 * For a more complex builder check {@link BuilderConfigurable}
 * {@link BuilderConfiguration}
 * </p>
 * <p>
 * Known implementatinons: {@link BuilderAbstract}
 * {@link ImmutableBuilderAbstract}
 * </p>
 * 
 * @see {@link BuilderAbstract} {@link ImmutableBuilderAbstract}
 *      {@link BuilderConfigurable} {@link BuilderConfiguration}
 * @author user
 * @param <T>
 *            type to be builded
 * @since Version: 1
 */
public interface Builder<T> {
	/**
	 * <p>
	 * The class -or some child- who implements this interface should have a
	 * method <br>
	 * <code>public static Builder&lt;T&gt; builder();</code> <br>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return one instance.
	 */
	public T build();

}
