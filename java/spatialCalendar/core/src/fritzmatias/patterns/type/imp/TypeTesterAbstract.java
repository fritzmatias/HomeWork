package fritzmatias.patterns.type.imp;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import fritzmatias.patterns.type.TypeManager;

/**
 * <p>
 * This class test all the implementations of types T registered into the
 * {@link TypeManager }.<br>
 * The type identification is exclusive, so an exception will be thrown if more
 * than one type matches the input.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @param <I>
 *            input value to be qualified as a type.
 * @param <T>
 *            type of the {@link TypeManager }
 * @since Version: 2
 */
public abstract class TypeTesterAbstract<I, T> implements Function<I, T> {
	private TypeManager<T> manager;
	private BiFunction<ImmutableSet<? extends T>, I, ImmutableMap<? extends T, Boolean>> tester;

	public TypeTesterAbstract() {
		super();
	}

	public TypeTesterAbstract(TypeManager<T> m) {
		super();
		this.manager = m;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public T apply(I context) {

		Map<? extends T, Boolean> results = tester.apply(manager.getTypes(), context);

		results = reduce(results);

		if (results.size() > 1) {
			throw new IllegalStateException(
					"The chain of resposability defining types is dirty:" + results + ", Context:" + context);
		} else if (results.size() == 0) {
			return manager.getDefaultType();
		} else {
			return results.keySet().iterator().next();
		}

	}

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param results
	 * @return
	 */
	protected Map<? extends T, Boolean> reduce(Map<? extends T, Boolean> results) {
		results = results.entrySet().stream().filter(e -> e.getValue() == true)
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		return results;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @return the manager
	 */
	public TypeManager<T> getManager() {
		return manager;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @since Version: 1
	 * @param manager
	 *            the manager to set
	 * @return this
	 */
	public TypeTesterAbstract<I, T> setManager(TypeManager<T> manager) {
		this.manager = manager;
		return this;
	}

	/**
	 * <p>
	 * It is a proxy to the {@link TypeManager#register(Collection)}
	 * </p>
	 * 
	 * @author user
	 * @param types
	 * @since Version: 1
	 * @return this
	 */
	public TypeTesterAbstract<I, T> register(Collection<? extends T> types) {
		this.getManager().register(types);
		return this;
	}

	/**
	 * <p>
	 * Evaluate the types inside the {@link TypeManager} and returns a map with
	 * the evaluated status of each element type.<br>
	 * Non checking of multiple validation is required into the tester; that
	 * check is made after and an exception is thrown if that occurs.<br>
	 * The return should be {@link Map<Type, Boolean>} with the status
	 * of each evaluation.
	 * </p>
	 * <p>
	 * Complexity: The complexity of this method is O(n), and all the types are
	 * evaluated.<br>
	 * Surety: an exclusivity check is made after the evaluation of the tester,
	 * throwing an exception if more than one retsults is true. If all the
	 * evaluations are false, the default is returned.
	 * </p>
	 * 
	 * @since Version: 1
	 * @param tester
	 *            the tester to set
	 * @return this
	 */
	public TypeTesterAbstract<I, T> setTester(
			BiFunction<ImmutableSet<? extends T>, I, ImmutableMap<? extends T, Boolean>> tester) {
		this.tester = tester;
		return this;
	}

}
