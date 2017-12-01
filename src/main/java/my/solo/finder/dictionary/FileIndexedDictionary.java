package my.solo.finder.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class representing a dictionary in which the words are indexed by their length
 * 
 * @author marumjr
 */
public class FileIndexedDictionary implements IndexedDictionary<Integer> {

	private Set<String> words;
	private Map<Integer, Set<String>> indexMap;

	/**
	 * Build a dictionary from the word list contained in file
	 * 
	 * @param file
	 *            File containing the word list
	 * @param encoding
	 *            Encoding used in the file
	 */
	public FileIndexedDictionary(File file, String encoding) {
		super();
		this.loadWordList(file, encoding);
	}

	@Override
	public Set<String> getWords() {
		return this.words;
	}

	@Override
	public boolean contains(String word) {
		return this.words.contains(word);
	}

	@Override
	public Set<String> retrieveWordsByIndex(Integer index) {
		return this.indexMap.get(index);
	}

	/**
	 * Initializes this dictionary with the word list found in file
	 * 
	 * @param file
	 *            File containing the word list
	 * @param encoding
	 *            Encoding used in the file
	 */
	private void loadWordList(File file, String encoding) {
		this.words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		this.indexMap = new TreeMap<Integer, Set<String>>();

		try {
			// Load the wordlist file...
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));

			// ... and store each read line in this dictionary's collection of words
			String str;
			while ((str = in.readLine()) != null) {
				int length = str.length();

				// Stores the words in an indexed manner, to easily access them later
				if (!this.indexMap.containsKey(length)) {
					this.indexMap.put(length, new TreeSet<String>());
				}

				// Together with the collection of words, which will be loaded from a file...
				this.words.add(str);
				// ... it also stores a map with words according to their lengths
				this.indexMap.get(length).add(str);
			}
			in.close();

			System.out.println("DICTIONARY COUNT: " + this.words.size());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
