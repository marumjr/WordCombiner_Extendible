package my.solo.finder.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Class representing a result: a combination of short words into one longer word
 * 
 * @author marumjr
 */
public class Result implements Comparable<Result> {

	private List<String> usedWords;

	/**
	 * Builds this {@link Result} with the collection of words passed in the parameter
	 * 
	 * @param words
	 *            Collection of words that constitutes this Result
	 */
	public Result(Collection<String> words) {
		super();
		this.usedWords = new ArrayList<String>(words);
	}

	/**
	 * Builds this {@link Result} with the collection of words passed in the parameter
	 * 
	 * @param words
	 *            Collection of words that constitutes this Result
	 */
	public Result(String... words) {
		super();

		this.usedWords = new ArrayList<String>();
		for (String word : words) {
			this.usedWords.add(word);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> iterator = this.usedWords.iterator(); iterator.hasNext();) {
			String word = iterator.next();

			sb.append(word);
			if (iterator.hasNext()) {
				sb.append(" + ");
			}
		}

		sb.append(" = " + this.getCombinedWord());

		return sb.toString();
	}

	@Override
	public int compareTo(Result other) {
		return this.toString().compareToIgnoreCase(other.toString());
	}

	/**
	 * @return the collection of short words that compose the final Result
	 */
	public List<String> getUsedWords() {
		return this.usedWords;
	}

	/**
	 * @return the resulting word, after combining all the short words
	 */
	public String getCombinedWord() {
		StringBuilder sb = new StringBuilder();
		for (String word : this.usedWords) {
			sb.append(word);
		}

		return sb.toString();
	}

}
