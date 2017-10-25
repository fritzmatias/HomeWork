/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */
package wordcounter;

import java.io.File;
import java.util.regex.Pattern;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public interface WordCounterDocumentRequest {
	public File getDocument();

	public Pattern getWordDelimiterPattern();
	
	public int getMaxTopWords();

}
