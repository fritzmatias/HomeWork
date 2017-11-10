/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.patterns.buider.imp;

import java.util.function.Supplier;

import fritzmatias.patterns.buider.BuilderConfigurable;
import fritzmatias.patterns.buider.BuilderConfiguration;

/**
 * <p>
 * This class is the abstraction of the configuration delegated class for a
 * {@link BuilderConfigurable}<br>
 * </p>
 * 
 * @author user
 * @param <T> 
 * @param <C> 
 * @since Version: 1
 * @see {@link BuilderConfigurable} {@link BuilderConfiguration}
 */
public class ImmutableBuilderConfigurationAbstract<T,C extends BuilderConfiguration<T>> extends ImmutableBuilderAbstract<T>
		implements BuilderConfiguration<T> {
	private Supplier<BuilderConfigurable<T,C>> supplier;

	public ImmutableBuilderConfigurationAbstract(Supplier<BuilderConfigurable<T, C>> suppllier) {
		setSupplier(suppllier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.withpatterns.builder.BuilderAbstract#create()
	 */
	@Override
	protected T create() {
		return this.getSupplier().get().setConfig((C) this).build();
	}

	protected Supplier<BuilderConfigurable<T, C>> getSupplier() {
		return supplier;
	}

	protected BuilderConfiguration<T> setSupplier(
			Supplier<BuilderConfigurable<T, C>> supplier) {
		throwExceptionifisLock();
		this.supplier = supplier;
		return this;
	}

}
