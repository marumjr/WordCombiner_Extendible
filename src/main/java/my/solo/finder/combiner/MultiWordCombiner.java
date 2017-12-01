package my.solo.finder.combiner;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import my.solo.finder.dictionary.Dictionary;
import my.solo.finder.result.Result;

/**
 * Implementation of {@link WordCombiner} that combines multiple smaller words into longer words
 * 
 * @author marumjr
 */
public class MultiWordCombiner extends WordCombiner<Dictionary> {

	public MultiWordCombiner(Dictionary dictionary) {
		super(dictionary);
	}

	@Override
	public Set<Result> combineIntoNLetterWords(int fixLength) {
		Set<Result> results = new TreeSet<Result>();
		this.combineIntoNLetterWords(fixLength, new Stack<String>(), results);

		return results;
	}

	/**
	 * Auxiliary function used for combining words recursively
	 * 
	 * @param remainingLength
	 *            How many letter we still have in our original word
	 * @param stack
	 *            Stack of words used while mounting the resulting word
	 * @param results
	 *            Collection of results found
	 */
	private void combineIntoNLetterWords(int remainingLength, Stack<String> stack, Set<Result> results) {
		// For each word in the dictionary...
		for (String word : this.dictionary.getWords()) {
			int wordLength = word.length();

			// ... check if the word fits into our solution
			if (wordLength <= remainingLength) {
				// If it does, push into into our solution stack...
				stack.push(word);
				// ... and removes it from the beginning of our original word
				int newRemainingLength = remainingLength - wordLength;

				// Either we reached the end and found a solution or we keep on searching
				if (newRemainingLength == 0 && stack.size() > 1) {
					// Create the result from the stack of other words
					Result result = new Result(stack.subList(0, stack.size()));
					if (this.dictionary.contains(result.getCombinedWord())) {
						results.add(result);
					}

				} else {
					this.combineIntoNLetterWords(newRemainingLength, stack, results);
				}

				// Resets these variables to their original states
				newRemainingLength = remainingLength + wordLength;
				stack.pop();
			}
		}
	}

}
