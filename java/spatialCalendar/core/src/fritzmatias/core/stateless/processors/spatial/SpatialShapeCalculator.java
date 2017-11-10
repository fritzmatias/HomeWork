package fritzmatias.core.stateless.processors.spatial;

import java.math.MathContext;
import java.util.function.BiFunction;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import fritzmatias.core.model.SpatialSystemStateType;
import fritzmatias.utils.MathAccuracy;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 * @deprecated in v2
 */
@Deprecated
public class SpatialShapeCalculator implements BiFunction<Vector2D, Vector2D[], SpatialSystemStateType> {
	private MathContext scale;
	private boolean includeVertices;

	public SpatialShapeCalculator() {
		super();
		this.includeVertices = false;
	}

	public SpatialShapeCalculator(boolean includeVertices) {
		super();
		this.includeVertices = includeVertices;
	}

	@Override
	public SpatialSystemStateType apply(Vector2D point, Vector2D[] v) {
		Vector2D A = v[0], B = v[1], C = v[2];
		SpatialSystemStateType actual = SpatialSystemStateType.indefinido;
		double abc = crossProduct(scale,A, B, C);
		double sgabc = Math.signum(abc);
		if (abc == 0) {
			// los segmentos AB AC son paralelos <=> recta
			if (crossProduct(scale,point, A, B) == 0) {
				// & PA PB == 0 => p pertenece a la recta
				actual = SpatialSystemStateType.RectaConPunto;
			} else {
				actual = SpatialSystemStateType.RectaSinPunto;
			}
		} else {
			double segmentA = crossProduct(scale,A, B, point);
			double segmentB = crossProduct(scale,B, C, point);
			double segmentC = crossProduct(scale,C, A, point);

			// (segmentA*segmentB*segmentC==0) corresponde a si el punto
			// pertenece a las aristas del triangulo
			if ((includeVertices && segmentA * segmentB * segmentC == 0) || sgabc == Math.signum(segmentA)
					&& sgabc == Math.signum(segmentB) && sgabc == Math.signum(segmentC)) {
				actual = SpatialSystemStateType.TrianguloEncierraOrigen;
			} else {
				actual = SpatialSystemStateType.TrianguloVacio;
			}
		}

		return actual;

	}

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @param scale 
	 * @since Version: 2
	 * @param A
	 * @param B
	 * @param C
	 * @return
	 */
	public double crossProduct(MathContext scale,Vector2D A, Vector2D B, Vector2D C) {
		return MathAccuracy.round(A.crossProduct(B, C),scale);
	}

	public MathContext getScale() {
		return scale;
	}

	public SpatialShapeCalculator setScale(MathContext scale) {
		this.scale = scale;
		return this;
	}

}
