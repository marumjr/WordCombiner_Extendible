package my.solo.finder.combiner;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import my.solo.finder.dictionary.IndexedDictionary;
import my.solo.finder.result.Result;

/**
 * Implementation of {@link WordCombiner} that combines multiple smaller words into longer words, using an optimized
 * algorithm
 * 
 * @author marumjr
 */
public class OptimizedMultiWordCombiner extends WordCombiner<IndexedDictionary<Integer>> {

	public OptimizedMultiWordCombiner(IndexedDictionary<Integer> dictionary) {
		super(dictionary);
	}

	@Override
	public Set<Result> combineIntoNLetterWords(int fixLength) {
		Set<Result> results = new TreeSet<Result>();

		// Loads every word with the amount of characters we are aiming for...
		Set<String> wordsWithNLetters = this.dictionary.retrieveWordsByIndex(fixLength);
		// ... loads a collection of shorter words...
		Set<String> shorterWords = this.getDictionaryWordsShorterThan(fixLength - 1);

		// ... and tries to combine them using the smaller words
		for (String nLengthedWord : wordsWithNLetters) {
			this.combineIntoNLetterWords(nLengthedWord, shorterWords, new Stack<String>(), results);
		}

		return results;
	}

	/**
	 * Auxiliary function used for combining words recursively
	 * 
	 * @param remainingWord
	 *            The remaining part of the word we are trying to build
	 * @param shorterWords
	 *            Collection of words we want to try to fit into the solution
	 * @param stack
	 *            Stack of words used in the solution
	 * @param results
	 *            All the results found
	 */
	private void combineIntoNLetterWords(String remainingWord, Set<String> shorterWords, Stack<String> stack,
			Set<Result> results) {
		// For each word in the collection of shorter words...
		for (String word : shorterWords) {

			// ... checks if it fits the word we are trying to form
			if (remainingWord.toLowerCase().startsWith(word.toLowerCase())) {
				// If it does, we push it into the solution stack...
				stack.push(word);
				// ... and subtract the prefix of that word
				String newRemainingWord = remainingWord.substring(word.length());

				// Either we have come to an end and have found a solution or we keep on searching
				if ("".equals(newRemainingWord)) {
					// Create the result from the stack of other words
					Result result = new Result(stack.subList(0, stack.size()));
					results.add(result);

				} else {
					// Retrieves all words shorter than the remaining part of the word we're trying to build
					Set<String> newShorterWords = this.getDictionaryWordsShorterThan(newRemainingWord.length());
					this.combineIntoNLetterWords(newRemainingWord, newShorterWords, stack, results);
				}

				// Resets these variables to their original states
				newRemainingWord = word + newRemainingWord;
				stack.pop();
			}
		}
	}

	/**
	 * Retrieves all dictionary words shorter than a certain number of characters
	 * 
	 * @param maxLength
	 *            The maximum number of characters a word may have to be considered part of the solution
	 * @return The collection of words found
	 */
	private Set<String> getDictionaryWordsShorterThan(int maxLength) {
		Set<String> shorterWords = new TreeSet<String>();
		for (int i = 1; i <= maxLength; i++) {
			shorterWords.addAll(this.dictionary.retrieveWordsByIndex(i));
		}

		return shorterWords;
	}

}
