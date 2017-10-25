package wordcounter;
/**
 * <p>
 *
 * </p>
 * @author matias
 * @version 1
 */

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * <p>
 * </p>
 * 
 * @author matias
 * @since Version: 1
 */
public final class WordCounterDocumentRequestImp implements WordCounterDocumentRequest {
	private File document;
	private Pattern wordDelimiterPattern;
	private int maxTopWords;

	public WordCounterDocumentRequestImp(File document, Pattern wordDelimeterPattern, int maxTopWords) throws IOException {
		if (document == null) {
			throw new IllegalArgumentException("The parameter 1 is null.");
		}
		if (wordDelimeterPattern== null) {
			throw new IllegalArgumentException("The parameter 2 is null.");
		}
		
		if (!document.isFile() || !document.exists()) {
			throw new IOException("Is not a file.");
		}
		this.document = document;
		this.wordDelimiterPattern = wordDelimeterPattern;
		this.maxTopWords = maxTopWords;
	}

	public WordCounterDocumentRequestImp(File document, Pattern wordDelimeterPattern) throws IOException {
		this(document,wordDelimeterPattern,0);
	}

	public File getDocument() {
		return document;
	}

	public Pattern getWordDelimiterPattern() {
		return wordDelimiterPattern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((wordDelimiterPattern == null) ? 0 : wordDelimiterPattern.toString().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordCounterDocumentRequestImp other = (WordCounterDocumentRequestImp) obj;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (wordDelimiterPattern == null) {
			if (other.wordDelimiterPattern != null)
				return false;
		} else if (!wordDelimiterPattern.equals(other.wordDelimiterPattern))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wordcounter.DocumentReaderRequestI#getMaxTopWords()
	 */
	@Override
	public int getMaxTopWords() {
		return this.maxTopWords;
	}

}
