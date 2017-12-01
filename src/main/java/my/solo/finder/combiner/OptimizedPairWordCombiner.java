package my.solo.finder.combiner;

import java.util.Set;
import java.util.TreeSet;

import my.solo.finder.dictionary.IndexedDictionary;
import my.solo.finder.result.Result;

/**
 * Implementation of {@link WordCombiner} that combines pairs of smaller words into longer words, using an optimized
 * algorithm for it
 * 
 * @author marumjr
 */
public class OptimizedPairWordCombiner extends WordCombiner<IndexedDictionary<Integer>> {

	public OptimizedPairWordCombiner(IndexedDictionary<Integer> dictionary) {
		super(dictionary);
	}

	@Override
	public Set<Result> combineIntoNLetterWords(int fixLength) {
		Set<Result> results = new TreeSet<Result>();

		// Loads a collection of words with the amount of characters we want to form
		Set<String> wordsWithNLetters = this.dictionary.retrieveWordsByIndex(fixLength);

		for (int i = 1; i < fixLength; i++) {
			int j = fixLength - i;

			// As we are combining words in pairs, we just need to combine a X lettered word...
			Set<String> wordsWithILetters = this.dictionary.retrieveWordsByIndex(i);
			// ... with a word containing the complimentary number of letters
			Set<String> wordsWithJLetters = this.dictionary.retrieveWordsByIndex(j);

			for (String iWord : wordsWithILetters) {
				for (String jWord : wordsWithJLetters) {
					String combinedWord = iWord + jWord;

					// If the resulting word exists, we can add it to the results
					if (wordsWithNLetters.contains(combinedWord)) {
						Result result = new Result(iWord, jWord);
						results.add(result);
					}
				}
			}
		}

		return results;
	}

}
