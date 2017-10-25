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
		Pattern pattern = Pattern.compile(" ");
		int maxTopWords = 0;
		WordCounterDocumentRequest request = null;
		try {
			request = WordCounterDocumentExecutor.request(file, pattern, maxTopWords);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}
		FutureTask<WordCounterDocumentResult> futureTask1;
		futureTask1 = new FutureTask<WordCounterDocumentResult>(new WordCounterDocumentExecutor(request));

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(futureTask1);

		while (true) {
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

	}

}
