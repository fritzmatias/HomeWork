/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Planet;
import fritzmatias.core.stateless.processors.spatial.SpatialObjectFactory;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.builder.CartesianLineBuilder;
import fritzmatias.core.withpatterns.model.PolarPosition;
import fritzmatias.core.withpatterns.model.Position;
import fritzmatias.core.withpatterns.model.imp.PolarPosition2DImp;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class TBuilder {
	
	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @return
	 */
	public static ImmutableCollection<Planet> createPlanets() {
		final ImmutableCollection<Planet> planetset = ImmutableList.<Planet>builder()
				.add(SpatialObjectFactory.createPlanet("F", 1, AngularDirection.Hour, 300))
				.add(SpatialObjectFactory.createPlanet("V", 3, AngularDirection.Hour, 2000))
				.add(SpatialObjectFactory.createPlanet("I", 5, AngularDirection.AntiHour, 1000)).build();
		return planetset;
	}

	public static Vector2D calculateCircularPosition(Vector2D center, double radius, double angle) {
		Vector2D p = new Vector2D((double) (center.getX() + radius * Math.cos(Math.toRadians(angle))),
				(double) (center.getY() + radius * Math.sin(Math.toRadians(angle))));

		return p;
	}

	/**
	 * <p>
	 * Create position.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param x
	 * @param y
	 * @return {@link Position}
	 */
	public static Position cartesian(int x, int y) {
		return SpatialFactoryImp.createCartesianPosition(x, y);
	}

	public static Planet planet(String name, double speed, AngularDirection direction, double distance) {
		return SpatialFactoryImp.createPlanet(name, speed, direction, distance);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @return
	 */
	public static CartesianLineBuilder cartesianPoligonBuilder() {
		return CartesianLineBuilder.builder();
	}

	/**
	 * <p>
	 * </p>
	 * @author matias
	 * @since Version: 1
	 * @param d
	 * @param degree
	 * @return
	 */
	public static PolarPosition polarPosition(double d, double degree) {
		return SpatialFactoryImp.createPolarPosition(d, degree);
	}

}
