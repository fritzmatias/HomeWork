/**
 * <p>
 *
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @version 1
 */
package fritzmatias.investigation;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * http://www.dma.fi.upm.es/personal/mabellanas/tfcs/kirkpatrick/Aplicacion/algoritmos.htm
 * </p>
 * @author fritzmatias(at)gmail(dot)com
 * @since Version: 1
 */
public class ApacheLinearAlgebraTest {
	Vector2D ab=new Vector2D(0, 1), AB=new Vector2D(0,5), rab=new Vector2D(0.1,0.9);
	Vector2D bc=new Vector2D(1,1), BC=new Vector2D(5,5);
	Vector2D ca=new Vector2D(1,0), CA=new Vector2D(5,0), rca=new Vector2D(0.9,0.1);
	Vector2D ad=new Vector2D(0,2);
	Vector2D ae=new Vector2D(0, 3);
	Vector2D af=new Vector2D(0, 4);
	Vector2D center=new Vector2D(0,0);
	double tolerance=0.000001d;

	@Test
	public void testCrossProduct() {
		
		Assert.assertEquals("inverse point comparasion", -1* ab.crossProduct(ca, bc), ab.crossProduct(bc, ca), tolerance);// cambio de posicion
		Assert.assertEquals("90 degrees +", -1.0d, center.crossProduct(ab, ca), tolerance); // 90º
		Assert.assertEquals("90 degrees -", 1.0d, center.crossProduct(ca, ab), tolerance); // 90º
		Assert.assertEquals("< 90 degrees +", -0.8d, center.crossProduct(rab, rca), tolerance); // 90º
		Assert.assertEquals("< 90 degrees -", 0.8d, center.crossProduct(rca, rab), tolerance); // 90º
		Assert.assertEquals("90 degrees big circle +", 25.0d, center.crossProduct(CA, AB), tolerance); // 90º
		Assert.assertEquals("90 degrees big circle -", -25.0d, BC.crossProduct(CA, AB), tolerance); // 90º
		Assert.assertEquals("90 degrees big circle normalized +", 1.0d, center.crossProduct(CA, AB)/(CA.getNorm()*AB.getNorm()), tolerance); // 90º
		Assert.assertEquals("parallel vectors", 0.0d, ca.crossProduct(ae, ca), tolerance); // parallelos
		Assert.assertTrue(ca.dotProduct(ae)==0);

	}
	@Test
	public void testLine() {
		Line l=new Line(ad, ae, tolerance);
		
		Assert.assertTrue(l.contains(ab));
		Assert.assertTrue(l.contains(center));
		Assert.assertTrue(l.contains(af));
		Assert.assertFalse(l.contains(bc));
	
	}
	
}
