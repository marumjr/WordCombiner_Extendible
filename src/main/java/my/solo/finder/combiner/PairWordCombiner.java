package my.solo.finder.combiner;

import java.util.Set;
import java.util.TreeSet;

import my.solo.finder.dictionary.Dictionary;
import my.solo.finder.result.Result;

/**
 * Implementation of {@link WordCombiner} that combines pairs of smaller words into longer words
 * 
 * @author marumjr
 */
public class PairWordCombiner extends WordCombiner<Dictionary> {

	public PairWordCombiner(Dictionary dictionary) {
		super(dictionary);
	}

	@Override
	public Set<Result> combineIntoNLetterWords(int fixLength) {
		Set<String> allDictionaryWords = this.dictionary.getWords();

		Set<Result> results = new TreeSet<Result>();

		// Loop through the dictionary...
		for (String wordA : allDictionaryWords) {
			// ... twice...
			for (String wordB : allDictionaryWords) {
				// ... in order to combine the words there
				String combinedWord = wordA + wordB;

				// If it has exactly the certain number of characters and exists in the dictionary...
				if (combinedWord.length() == fixLength && this.dictionary.contains(combinedWord)) {
					// ... then it is part of the solution, and add it to the results found
					Result result = new Result(wordA, wordB);
					results.add(result);
				}
			}
		}

		return results;
	}

}
