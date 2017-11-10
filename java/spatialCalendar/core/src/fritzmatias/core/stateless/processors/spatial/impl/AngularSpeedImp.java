package fritzmatias.core.stateless.processors.spatial.impl;

import fritzmatias.core.stateless.processors.spatial.SpatialSpeed;
import fritzmatias.utils.Math;

/**
 * <p>
 * Defines the angular speed.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class AngularSpeedImp implements SpatialSpeed<Double> {
	private double degreeSpeed = 0;

	public AngularSpeedImp() {
		setSpeed(0);
	}

	/**
	 * <p>
	 * The degree speed is processed as a value mod 360 degrees.
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param degreeSped
	 */
	public SpatialSpeed setSpeed(double degreeSped) {
		this.degreeSpeed = Math.normalizeMod(degreeSped, 360);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.stateless.processors.geospatial.GeoSpatialSpeed#
	 * getDegreSpeed()
	 */
	@Override
	public Double getDegreesSpeed() {
		return degreeSpeed;
	}

}
