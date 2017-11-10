/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.model.mongo.converters;

import org.apache.commons.math3.exception.DimensionMismatchException;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */

public class Vector extends org.apache.commons.math3.geometry.euclidean.twod.Vector2D {

	public Vector() {
		super(0, 0);
	}

	public Vector(double x, double y) {
		super(x, y);
	}

	public Vector(double a1, org.apache.commons.math3.geometry.euclidean.twod.Vector2D u1, double a2,
			org.apache.commons.math3.geometry.euclidean.twod.Vector2D u2, double a3,
			org.apache.commons.math3.geometry.euclidean.twod.Vector2D u3, double a4,
			org.apache.commons.math3.geometry.euclidean.twod.Vector2D u4) {
		super(a1, u1, a2, u2, a3, u3, a4, u4);
	}

	public Vector(double a1, org.apache.commons.math3.geometry.euclidean.twod.Vector2D u1, double a2,
			org.apache.commons.math3.geometry.euclidean.twod.Vector2D u2, double a3,
			org.apache.commons.math3.geometry.euclidean.twod.Vector2D u3) {
		super(a1, u1, a2, u2, a3, u3);
	}

	public Vector(double a1, org.apache.commons.math3.geometry.euclidean.twod.Vector2D u1, double a2,
			org.apache.commons.math3.geometry.euclidean.twod.Vector2D u2) {
		super(a1, u1, a2, u2);
	}

	public Vector(double a, org.apache.commons.math3.geometry.euclidean.twod.Vector2D u) {
		super(a, u);
	}

	public Vector(double[] v) throws DimensionMismatchException {
		super(v);
		// TODO Auto-generated constructor stub
	}

}
