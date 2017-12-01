package my.solo.finder.combiner;

import java.util.Set;

import my.solo.finder.dictionary.Dictionary;
import my.solo.finder.result.Result;

/**
 * Class that represents a combinator of words - it's function is combining words from a dictionary into other existing
 * words present in the dictionary
 * 
 * @param <T>
 *            Type of the dictionary utilized by this implementation of WordCombiner
 * 
 * @author marumjr
 */
public abstract class WordCombiner<T extends Dictionary> {

	protected T dictionary;

	/**
	 * Builds an instance of {@link WordCombiner} with the passed dictionary
	 * 
	 * @param dictionary
	 *            Dictionary used by this {@link WordCombiner}
	 */
	public WordCombiner(T dictionary) {
		super();
		this.dictionary = dictionary;
	}

	/**
	 * Starts combining words from the dictionary into other existing words in the dictionary with a certain amount of
	 * characters
	 * 
	 * @param fixLength
	 *            The number of characters the resulting words must have
	 * @return a collection of {@link Result}s found
	 */
	public abstract Set<Result> combineIntoNLetterWords(int fixLength);

}
