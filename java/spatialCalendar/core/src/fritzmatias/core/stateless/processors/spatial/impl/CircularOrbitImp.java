package fritzmatias.core.stateless.processors.spatial.impl;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Orbit;
import fritzmatias.core.stateless.processors.spatial.SpatialSpeed;
import fritzmatias.core.withpatterns.SpatialFactoryImp;
import fritzmatias.core.withpatterns.model.Position;

/**
 * <p>
 * Implementation of the circular orbit, with identical radio for each point in
 * the orbit.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class CircularOrbitImp implements Orbit {
	private double radio;

	/**
	 * Distancia al origen de coordenadas
	 * 
	 * @param radio
	 */
	public void setRadio(double radio) {
		this.radio = radio;
	}

	@Override
	public double getStreightDistanceTo0(double t) {
		// in a circular Orbit the distance to 0,0 is constant.
		return radio;
	}

	private AngularDirection direction = AngularDirection.AntiHour;

	public void setDirection(AngularDirection direction) {
		this.direction = direction;
	}

	public AngularDirection getDirection() {
		return direction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.model.Orbit#calculatePosition(int,
	 * fritzmatias.core.stateless.processors.spatial.SpatialSpeed)
	 */
	@Override
	public Position calculatePosition(double time, SpatialSpeed<Double> speed) {
		return SpatialFactoryImp.createPolarPosition(1, time*speed.getDegreesSpeed());
	};

}
