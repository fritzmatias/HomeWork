/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.patterns.buider;

import fritzmatias.patterns.buider.imp.ImmutableBuilderConfigurationAbstract;
import fritzmatias.patterns.buider.imp.ImmutableBuilderConfigurableAbstract;

/**
 * <p>
 * This interface defines a delegation between the {@link Builder}&lt;T&gt; and
 * the configuration of &lt;T&gt;, {@link BuilderConfiguration}&lt;T&gt;<br>
 * So, if you want to share (or compose) the sets of some
 * {@link Builder}&lt;T&gt; and delegate the specific building process, the
 * proper way to do it is:
 * <li>Extend {@link BuilderConfiguration} in your configuration contract. And
 * extend {@link ImmutableBuilderConfigurationAbstract} for your configuration
 * implementation.</li>
 * <li>Your original {@link Builder} will be {@link BuilderConfigurable}. And
 * the implementation can extends from
 * {@link ImmutableBuilderConfigurableAbstract}</li>
 * <li>Define the builder() call to return the implementation of
 * {@link BuilderConfiguration}.<br>
 * Because {@link BuilderConfiguration} extends {@link Builder}, when the
 * {@link BuilderConfiguration#build()} method is called, it re call to the
 * specific {@link BuilderConfigurable#build()}</li>
 * </p>
 * <p>
 * At this moment, is time to define the
 * </p>
 * <code>&lt;YourConfigImp implements BuilderConfiguration&lt;T&gt;&gt; builder() </code>
 * <p>
 * in each specialized {@link BuilderConfigurable} implementation, because the
 * configuration is the common interface to the building process.
 * </p>
 * <p>
 * If the implementations extends {@link ImmutableBuilderConfigurationAbstract} and
 * {@link ImmutableBuilderConfigurableAbstract}, all is reduced to implement the
 * required methods, and the setters.
 * </p>
 * <p>
 * Pattern: bridge on C
 * </p>
 * 
 * @author user
 * @param <T>
 *            type of the final object to be builded
 * @param <C>
 *            the configuration builder to be used to build the object T.
 * @since Version: 1
 * @see {@link BuilderConfiguration} {@link Builder}
 *      {@link ImmutableBuilderConfigurationAbstract}
 *      {@linkplain ImmutableBuilderConfigurableAbstract}
 */
public interface BuilderConfigurable<T, C extends BuilderConfiguration<T>> extends Builder<T> {

	/**
	 * <p>
	 * Is used to set the configuration builded by the implementation of
	 * {@link BuilderConfiguration}.<br>
	 * The getter should be protected and be call into the context of
	 * {@link #build()} implementation.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param config
	 * @return
	 */
	public Builder<T> setConfig(C config);
}
