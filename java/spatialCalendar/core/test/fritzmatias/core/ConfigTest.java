/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.RoundingMode;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import fritzmatias.core.imp.SpatialCalendarBuilderImp2;
import fritzmatias.core.model.Planet;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class ConfigTest {
	@Test
	public void configurationTest() {
		
		SpatialCalendarConfiguration conf2 = SpatialCalendarBuilderImp2.builder()
		.setPlanets(ImmutableList.<Planet>builder().build())
		.setScale(3,RoundingMode.DOWN)
		.setIncludeVertices(true)
		.setRangeToBuild(0, 10);

		assertSetAndGets(conf2);

	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author fritzmatias(at)gmail(dot)com
	 * @since Version: 1
	 * @param conf
	 */
	private void assertSetAndGets(SpatialCalendarConfiguration conf) {
		assertTrue(conf.isIncludeVertices());

		conf.setPlanets(ImmutableList.<Planet>builder().build());
		conf.setRangeToBuild(0, 10);
		assertEquals(0, conf.getMinRangeDays());
		assertEquals(10, conf.getMaxRangeDays());

		conf.setScale(2, RoundingMode.DOWN);
		assertEquals(2, conf.getScale().getPrecision());
	}

}
