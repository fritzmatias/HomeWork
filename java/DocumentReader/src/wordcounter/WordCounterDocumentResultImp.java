package wordcounter;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableSortedSet;

public final class WordCounterDocumentResultImp implements WordCounterDocumentRequest, WordCounterDocumentResult {

	private ImmutableSortedSet<Map.Entry<String, Integer>> wordSet;
	private WordCounterDocumentRequest request;
	private int totalWords;
	private long totalChars;

	public WordCounterDocumentResultImp(WordCounterDocumentRequest request,
			ImmutableSortedSet<Entry<String, Integer>> wordSet, int totalWords, long totalChars) {
		this.request = request;
		this.wordSet = wordSet;
		this.totalWords=totalWords;
		this.totalChars=totalChars;
		
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param document
	 * @param wordDelimeterPattern
	 * @param wordSet
	 */
	public ImmutableSortedSet<Map.Entry<String, Integer>> getWordSet() {
		return wordSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wordcounter.DocumentReaderRequestI#getDocument()
	 */
	@Override
	public File getDocument() {
		return request.getDocument();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wordcounter.DocumentReaderRequestI#getWordDelimiterPattern()
	 */
	@Override
	public Pattern getWordDelimiterPattern() {
		return request.getWordDelimiterPattern();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wordcounter.DocumentReaderRequestI#getMaxTopWords()
	 */
	@Override
	public int getMaxTopWords() {
		return request.getMaxTopWords();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + (int) (totalChars ^ (totalChars >>> 32));
		result = prime * result + totalWords;
		result = prime * result + ((wordSet == null) ? 0 : wordSet.hashCode());
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
		WordCounterDocumentResultImp other = (WordCounterDocumentResultImp) obj;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (totalChars != other.totalChars)
			return false;
		if (totalWords != other.totalWords)
			return false;
		if (wordSet == null) {
			if (other.wordSet != null)
				return false;
		} else if (!wordSet.equals(other.wordSet))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wordcounter.WordCounterDocumentResult#getTotalWords()
	 */
	@Override
	public int getTotalWords() {
		return this.totalWords;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wordcounter.WordCounterDocumentResult#getTotalChars()
	 */
	@Override
	public long getTotalChars() {
		return this.totalChars;
	}

}
