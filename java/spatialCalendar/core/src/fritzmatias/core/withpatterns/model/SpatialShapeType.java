package fritzmatias.core.withpatterns.model;

import java.util.function.Function;
import java.util.function.Predicate;

import fritzmatias.core.model.SpatialSystemStateType;

/**
 * <p>
 * Refactor from enum {@link SpatialSystemStateType} to a extensible class (When
 * the childs implements the specific type). Implementing facade Pattern + Chain
 * of responsability Pattern.<br>
 * if an override between the type criteria definition a RuntimeExcepion should
 * be thrown. <br>
 * The utilization of {@link Position} interface allows extension from a simple
 * implementation to a more complex one, decupling implementation of a position.
 * <br>
 * This pattern allows define new types without affect the types defined
 * previously, that means decoupling for extension.
 * </p>
 * <p>
 * A shared map with other childs to get common calculations throw key,value. If
 * the expected entry is not found, the calculation should be made by each
 * child.
 * </p>
 * 
 * @see SpatialSystemStateType
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
public abstract class SpatialShapeType
		implements Predicate<SpatialShapeContext>, Function<SpatialShapeContext, SpatialShapeContext> {
	private String formalStatusName = "Not Implemented";

	public SpatialShapeType(String nombre) {
		this.formalStatusName = nombre;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 */
	public SpatialShapeType() {
	}

	public String getFormalStatusName() {
		return formalStatusName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		} else if (obj instanceof SpatialShapeType) {
			SpatialShapeType o = (SpatialShapeType) obj;
			return this.getFormalStatusName().equalsIgnoreCase(o.getFormalStatusName());
		}
		return false;
	}
	
	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param type
	 * @return the unidirectional equality between this and type
	 */
	public boolean equivalent(SpatialSystemStateType type) {
		return this.getFormalStatusName().equalsIgnoreCase(type.getFormalStatusName());		
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @return
	 */
	public SpatialSystemStateType getType() {
		return SpatialSystemStateType.indefinido;
	}

	@Override
	public int hashCode() {
		return this.getFormalStatusName().hashCode();
	}

}
