/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.core;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fritzmatias.core.withpatterns.model.imp.AllModelTests;
import fritzmatias.core.withpatterns.model.imp.SpatialShapeTypeParametrizedTest;
import fritzmatias.core.withpatterns.stateless.processors.spatial.AllCalculatorsV2Tests;

/**
 * <p>
 * </p>
 * 
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
@RunWith(Suite.class)
@SuiteClasses({ CalendarTest.class, AllCalculatorsV2Tests.class, SpatialShapeTypeParametrizedTest.class,
		ConfigTest.class, AllModelTests.class, ConfigTest.class, MathTest.class })
public class AllTests {

}
