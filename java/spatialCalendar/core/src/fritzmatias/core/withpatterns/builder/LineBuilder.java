/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.withpatterns.builder;

import java.math.MathContext;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.patterns.buider.imp.ImmutableBuilderAbstract;

/**
 * <p>
 * Poligono construido desde multiples ecuaciones d	e rectas.
 * </p>
 * http://www.robertobigoni.eu/Matematica/Lines/lines08/lines08.html
 *
 * @author user
 * @since Version: 1
 */
public abstract class LineBuilder<T> extends ImmutableBuilderAbstract<T> {
	private static final String INCLINATION = "incline";
	private static final String INDEP_TERM = "independentTerm";
	private static final String SUPPLIER = "supplier";
	private static final String POSITIONS = "positions";
	private static final String MATH_CONTEXT = "math_context";

	public LineBuilder<T> setSupplier(Supplier<T> t) {
		set(SUPPLIER, t);
		return this;
	}

	public LineBuilder<T> setInclination(Double inclinaiton) {
		set(INCLINATION, inclinaiton);
		return this;
	}
	public LineBuilder<T> andOf(final Position p1){
		return of(p1);
	}
	/**
	 * <p>
	 * Set the points to define the line implicitly
	 * </p>
	 * @author matias
	 * @since Version: 1
	 * @param p1
	 * @param p2
	 * @return
	 */
	public LineBuilder<T> of(final Position p1){
		@SuppressWarnings("unchecked")
		Set<Position> l = (Set<Position>) getOptional(POSITIONS);
		if (l == null) {
			l = new HashSet<Position>();
			set(POSITIONS, l);
		}
		if(! l.add(p1)) {
			throw new IllegalArgumentException("The position is equals to another one.");
		};
		return this;
	}

	@SuppressWarnings("unchecked")
	protected Set<Position> getPositions() {
		return (Set<Position>) getOptional(POSITIONS);

	}

	public LineBuilder<T> setIndependentTerm(final double b) {
		set(INDEP_TERM, b);
		return this;
	}

	protected Double getIndepTerm() {
		return super.getOptionalOrDefault(INDEP_TERM, Double.valueOf(0.0));
	}

	protected Double getInclination() {
		return (Double) getOptional(INCLINATION);
	}

	@SuppressWarnings("unchecked")
	protected Supplier<T> getSupplier() {
		return (Supplier<T>) get(SUPPLIER);
	}
	
	/**
	 * <p>
	 * </p>
	 * @author matias
	 * @since Version: 1
	 * @param s
	 * @return
	 */
	public LineBuilder<T> setScale(MathContext s) {
		set(MATH_CONTEXT, s);
		return this;
	}
	
	protected MathContext getScale() {
		return (MathContext) get(MATH_CONTEXT);
	}
	
}
