/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core.withpatterns.model;

import java.math.MathContext;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;

/**
 * <p>
 * This interface implements the pattern State, so the system state can be moved
 * between stateleess components.
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 2
 */
//FIX: eliminar metodos inecesarios or repetidos 
public interface SpatialShapeContext {
	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 2
	 * @return
	 */
	public  ImmutableMap<Integer, Position> getPositions();
	
	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param i
	 * @return
	 */
	public Position getPosition(int order);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param directPosition2D
	 */
	public void setPoint(Position point);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param includeVertices
	 */
	public void setIncludeVertices(boolean includeVertices);

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @return
	 */
	public Position getPoint();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @return
	 */
	public boolean getIncludeVertices();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @return
	 */
	public MathContext getScale();

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 2
	 * @param scale
	 */
	public void setScale(MathContext scale);

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param day
	 */
	public void setDay(Integer day);

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param type
	 */
	public void setType(SpatialShapeType type);

	/**
	 * <p>
	 * </p>
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param perimeter
	 */
	public void setPerimeter(double perimeter);

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param positions
	 */
	public void setPositions(ImmutableCollection<? extends Position> positions);

	/**
	 * <p>
	 * </p>
	 * @author user
	 * @since Version: 1
	 * @param order
	 * @param spatialPosition
	 */
	void addPosition(int order, Position spatialPosition);

}
