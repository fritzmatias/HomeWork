/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.patterns.buider.imp;

import fritzmatias.patterns.buider.Builder;
import fritzmatias.patterns.buider.BuilderConfigurable;
import fritzmatias.patterns.buider.BuilderConfiguration;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public abstract class ImmutableBuilderConfigurableAbstract<T, C extends BuilderConfiguration<T>>
		extends ImmutableBuilderAbstract<T> implements BuilderConfigurable<T, C> {
	private C config;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fritzmatias.core.withpatterns.builder.ConfigurableBuilder#setConfig(java.
	 * lang.Object)
	 */

	@Override
	public Builder<T> setConfig(C config) {
		throwExceptionifisLock();
		this.config = config;
		return this;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @return the config
	 */
	protected C getConfig() {
		return config;
	}

}