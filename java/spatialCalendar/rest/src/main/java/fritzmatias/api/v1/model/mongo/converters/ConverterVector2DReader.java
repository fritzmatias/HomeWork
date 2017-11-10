/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.api.v1.model.mongo.converters;

import org.springframework.core.convert.converter.Converter;

/**
 * <p>
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class ConverterVector2DReader implements Converter<fritzmatias.api.v1.model.mongo.converters.Vector, org.apache.commons.math3.geometry.euclidean.twod.Vector2D> {

		@Override
		public org.apache.commons.math3.geometry.euclidean.twod.Vector2D convert(fritzmatias.api.v1.model.mongo.converters.Vector source) {
			return (org.apache.commons.math3.geometry.euclidean.twod.Vector2D) source;
		}

}
