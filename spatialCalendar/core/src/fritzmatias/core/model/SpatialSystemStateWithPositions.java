/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.model;

import java.util.Arrays;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class SpatialSystemStateWithPositions extends SpatialSystemState {
	private Vector2D[] planetPosition;

	public Vector2D[] getPlanetPosition() {
		return planetPosition;
	}

	public void setPlanetPosition(Vector2D[] planetPosition) {
		this.planetPosition = planetPosition;
	}

	@Override
	public String toString() {
		String parent = super.toString();
		StringBuilder sb = new StringBuilder(parent.substring(0, parent.length() - 1));
		sb.append("[ planetPosition=" + Arrays.toString(planetPosition) + "]");
		return sb.toString();
	}
}
