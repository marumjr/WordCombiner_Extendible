package my.solo.finder;

import java.io.File;
import java.util.Set;

import my.solo.finder.combiner.OptimizedPairWordCombiner;
import my.solo.finder.combiner.WordCombiner;
import my.solo.finder.dictionary.FileIndexedDictionary;
import my.solo.finder.dictionary.IndexedDictionary;
import my.solo.finder.result.Result;
import my.solo.finder.utils.FileUtils;

/**
 * Class responsible for running the application
 * 
 * @author marumjr
 */
public class Runner {

	/**
	 * Main method.
	 * <p>
	 * It loads a word list into a dictionary and then starts combining words, trying to found the ones with an exactly
	 * certain number of characters and printing it when it also exists in the dictionary
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) throws Exception {
		int fixLength = 6;

		// Loads the word list file...
		File file = FileUtils.retrieveResourceFile(Runner.class, "wordlist.txt");
		// ... and stores it in a Dictionary
		IndexedDictionary<Integer> dictionary = new FileIndexedDictionary(file, FileUtils.ENCODING_ISO_8859_1);

		// Then we can start combining words into words with a certain number of characters
		WordCombiner<IndexedDictionary<Integer>> wordCombiner = new OptimizedPairWordCombiner(dictionary);
		Set<Result> results = wordCombiner.combineIntoNLetterWords(fixLength);

		// And we print out the solution
		for (Result result : results) {
			System.out.println(result);
		}
		System.out.println("Number of Results:");
		System.out.println(results.size());
	}

}
