package fritzmatias.core.stateless.processors.spatial.impl;

import fritzmatias.core.stateless.processors.spatial.SpatialSpeed;

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
	public void setSpeed(int degreeSped) {
		this.degreeSpeed = degreeSped % 360;
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
