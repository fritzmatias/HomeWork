package wordcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.UnmodifiableIterator;

public class WordCounterDocumentExecutor implements Callable<WordCounterDocumentResult> {
	private Logger log = Logger.getLogger(this.getClass());
	WordCounterDocumentRequest request;

	public WordCounterDocumentExecutor(WordCounterDocumentRequest request) {
		this.request = request;
	}

	private WordCounterDocumentResult identifyAndCountWords() {
		Reader reader = null;
		try {
			reader = new FileReader(request.getDocument());
			ImmutableSortedSet<Map.Entry<String, Integer>> iset = identifyAndCountWords(reader,
					request.getWordDelimiterPattern());
			int totalWords = 0;
			int totalChars = 0;

			for (UnmodifiableIterator<Entry<String, Integer>> it = iset.iterator(); it.hasNext();) {
				Entry<String, Integer> x = it.next();
				totalWords += x.getValue();
				totalChars += x.getValue() * x.getKey().length();
			}
			iset=subsetOfFirst(iset, request.getMaxTopWords());

			WordCounterDocumentResultImp result = new WordCounterDocumentResultImp(request, iset, totalWords,
					totalChars);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.info(e);
				}
			}
		}
	}

	private static ImmutableSortedSet<Map.Entry<String, Integer>> subsetOfFirst(
			ImmutableSortedSet<Entry<String, Integer>> iset, int maxTopWords) throws IOException {
		if (iset != null && iset.size() > maxTopWords) {
			UnmodifiableIterator<Map.Entry<String, Integer>> it = iset.iterator();
			Map.Entry<String, Integer> fromElement = it.next();
			Map.Entry<String, Integer> toElement = it.next();
			for (int i = 1; it.hasNext() && i < maxTopWords; i++) {
				toElement = it.next();
			}
			return iset.subSet(fromElement, true, toElement, true);
		}
		return iset;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param reader
	 * @param wordDelimeterPattern
	 * @return
	 * @throws IOException
	 */
	public static ImmutableSortedSet<Map.Entry<String, Integer>> identifyAndCountWords(Reader reader,
			Pattern wordDelimeterPattern) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(reader);
			String line;
			Map<String, Integer> map = new HashMap<String, Integer>();
			while ((line = br.readLine()) != null) {
				String[] words = wordDelimeterPattern.split(line);
				for (int i = 0; i < words.length; i++) {
					String key = words[i];
					Integer count = 0;
					if ((count = map.get(key)) == null) {
						map.put(key.trim(), 1);
					}
					count++;
					map.put(key.trim(), count);
				}
			}
			br.close();
			ImmutableSortedSet<Entry<String, Integer>> imap = ImmutableSortedSet.<Map.Entry<String, Integer>>copyOf(
					((a, b) -> a.getValue() - b.getValue()), map.entrySet());
			return imap;
		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null)
				br.close();
		}
	}

	public WordCounterDocumentResult call() throws Exception {
		return identifyAndCountWords();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param file
	 * @param pattern
	 * @param maxTopWords
	 * @return
	 * @throws IOException
	 */
	public static WordCounterDocumentRequest request(File file, Pattern pattern, int maxTopWords) throws IOException {
		return new WordCounterDocumentRequestImp(file, pattern, maxTopWords);
	}

}