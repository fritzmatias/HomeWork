package wordcounter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public class WordCounterDocumentExecutorTest {
	@Test
	public void test() {
		File file = new File(this.getClass().getClassLoader().getResource("text.txt").getFile());
		Pattern pattern = Pattern.compile("[ ]|^$");
		int maxTopWords = 0;
		WordCounterDocumentRequest request = null;
		try {
			request = WordCounterDocumentExecutor.request(file, pattern, maxTopWords);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}
		FutureTask<WordCounterDocumentResult> futureTask1;
		futureTask1 = WordCounterDocumentExecutor.createFutureTask(request);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(futureTask1);

		while (!futureTask1.isDone()) {
			try {
				if (futureTask1.isDone()) {
					System.out.println("Done");
					// shut down executor service
					executor.shutdown();
					return;
				}

				if (!futureTask1.isDone()) {
					// wait indefinitely for future task to complete
					System.out.println("FutureTask1 output=" + futureTask1.get());
				}

				System.out.println("Waiting for FutureTask2 to complete");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				Assert.fail();
			}
		}

		try {
			WordCounterDocumentResult result = futureTask1.get();
			System.out.println("Chars: " + result.getTotalChars());
			System.out.println("Words: " + result.getTotalWords());
			System.out.println("Set: " + result.getWordSet());
			Assert.assertEquals(7, result.getTotalWords());
			Assert.assertEquals(28, result.getTotalChars());
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSinchonous() {
		File file = new File(this.getClass().getClassLoader().getResource("text.txt").getFile());
		Pattern pattern = Pattern.compile("[ ]|^$");
		int maxTopWords = 3;

		WordCounterDocumentRequest request = null;
		try {
			// decupling constructor
			request = WordCounterDocumentExecutor.request(file, pattern, maxTopWords); 
			
			// instantiation & call
			WordCounterDocumentResult result = WordCounterDocumentExecutor.createInstance(request).call(); 
			
			System.out.println("Chars: " + result.getTotalChars());
			System.out.println("Words: " + result.getTotalWords());
			System.out.println("Set: " + result.getWordSet());
			Assert.assertEquals(7, result.getTotalWords());
			Assert.assertEquals(28, result.getTotalChars());
			Assert.assertEquals(3, result.getWordSet().size());

		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	
	}
}
