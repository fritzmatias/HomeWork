package fritzmatias.core.stateless.processors.spatial.impl;

import fritzmatias.core.model.AngularDirection;
import fritzmatias.core.model.Orbit;

/**
 * <p>
 * Implementation of the circular orbit, with identical radio for each point in the orbit.
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class CircularOrbitImp implements Orbit {
	private int radio;
	
	/**
	 * Distancia al origen de coordenadas
	 * @param radio
	 */
	public void setRadio(int radio) {
		this.radio = radio;
	}


	@Override
	public double getStreightDistanceTo0(double t) {
		// in a circular Orbit the distance to 0,0 is constant.
		return radio;
	}

	
	private AngularDirection direction=AngularDirection.AntiHour;
	public void setDirection(AngularDirection direction) {
		this.direction=direction;
	}

	public AngularDirection getDirection() {
		return direction;
	};
	
}
