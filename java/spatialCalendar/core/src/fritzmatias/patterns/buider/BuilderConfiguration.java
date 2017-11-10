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
 * This interface establish the contract required to identify the delegated
 * configuration object related to a {@link BuilderConfigurable}.<br>
 * Why a configuration will be a {@link Builder} ?<br>
 * Well, to work as a bridge,
 * encapsulating the building delegation, allowing refactoring.
 * </p>
 * <p>
 * The proper way to establish the relationship between the two objects is
 * extending {@link ImmutableBuilderConfigurationAbstract} and implementing the specific
 * configuration interface, and {@link BuilderConfigurable} as a proper builder
 * instance, which will return a new instance of &lt;T&gt; <br>
 * Normally the builder which extends
 * {@link ImmutableBuilderConfigurableAbstract} will be the responsible to
 * return the implementation of {@link BuilderConfigurable}
 * </p>
 * 
 * @author user
 * @since Version: 1
 * @param <T> the type to be builded
 * @see {@link BuilderConfigurable} {@link ImmutableBuilderConfigurationAbstract}
 */
public interface BuilderConfiguration<T> extends Builder<T> {

}
