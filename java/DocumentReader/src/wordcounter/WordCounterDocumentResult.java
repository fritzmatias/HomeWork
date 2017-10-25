/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package wordcounter;

import java.util.Map;

import com.google.common.collect.ImmutableSortedSet;

/**
 * <p>
 * </p>
 * @author matias
 * @since Version: 1
 */
public interface WordCounterDocumentResult {
	
	public ImmutableSortedSet<Map.Entry<String, Integer>> getWordSet();
	
	public int getTotalWords();
	public long getTotalChars();

}
