/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.withpatterns.builder;

import java.util.Iterator;
import java.util.Set;

import fritzmatias.core.withpatterns.model.CartesianPosition;
import fritzmatias.core.withpatterns.model.Position;

/**
 * <p>
 * </p>
 * 
 * @author user
 * @since Version: 1
 */
public class CartesianLineBuilder extends LineBuilder<CartesianLine> {

	public static CartesianLineBuilder builder() {
		return new CartesianLineBuilder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fritzmatias.core.BuilderAbstract#create()
	 */
	@Override
	public CartesianLine create() {
		if(super.getInclination() != null) {
			return new CartesianLine(super.getInclination(), super.getIndepTerm(), getScale());
		}
		Set<Position> positions = this.getPositions();
		if(positions.size() == 1) {
			Iterator<Position> iterator = positions.iterator();
			CartesianPosition p1=iterator.next().cartesian();
			return new CartesianLine(p1,getScale());
		}else 
		if(positions.size()<2){
			throw new IllegalArgumentException("Not enoght positions");
		}
		Iterator<Position> iterator = positions.iterator();
		CartesianPosition p1=iterator.next().cartesian();
		CartesianPosition p2=iterator.next().cartesian();
		return new CartesianLine(p1,p2,getScale());
	}



}
