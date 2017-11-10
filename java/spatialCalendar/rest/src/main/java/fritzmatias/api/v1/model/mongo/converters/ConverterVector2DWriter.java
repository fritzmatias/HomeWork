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
public class ConverterVector2DWriter implements Converter<org.apache.commons.math3.geometry.euclidean.twod.Vector2D, fritzmatias.api.v1.model.mongo.converters.Vector> {

		@Override
		public fritzmatias.api.v1.model.mongo.converters.Vector convert(org.apache.commons.math3.geometry.euclidean.twod.Vector2D source) {
			return (fritzmatias.api.v1.model.mongo.converters.Vector) source;
		}

}
