/**
 * <p>
 *
 * </p>
 * @author user
 * @version 1
 */
package fritzmatias.core.withpatterns.model.imp;

import java.util.stream.IntStream;

import org.junit.Test;

import fritzmatias.core.withpatterns.model.Position;

/**
 * <p>
 * </p>
 * @author user
 * @since Version: 1
 */
public class PositionImmutabilityTest {

	private void printmemstat() {

		int mb = 1024*1024;

		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();

		System.out.println("##### Heap utilization statistics [MB] #####");

		//Print used memory
		System.out.println("Used Memory:"
			+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		//Print free memory
		System.out.println("Free Memory:"
			+ runtime.freeMemory() / mb);

		//Print total available memory
		System.out.println("Total Memory:" + runtime.totalMemory() / mb);

		//Print Maximum available memory
		System.out.println("Max Memory:" + runtime.maxMemory() / mb);	
	}
	
	@Test
	public void directPositiontest() {
		Position p=new PolarPosition2DImp(1,2);
		
		Runtime.getRuntime().gc();
		printmemstat();
		IntStream.range(0, 10000).parallel().boxed().forEach(i->p.cartesian());
		printmemstat();
		Runtime.getRuntime().gc();
		printmemstat();
		
	}

}
