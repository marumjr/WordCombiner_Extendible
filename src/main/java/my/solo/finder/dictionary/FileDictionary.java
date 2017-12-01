package my.solo.finder.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class representing a dictionary loaded by a file
 * 
 * @author marumjr
 */
public class FileDictionary implements Dictionary {

	protected Set<String> words;

	/**
	 * Build a dictionary from the word list contained in file
	 * 
	 * @param file
	 *            File containing the word list
	 * @param encoding
	 *            Encoding used in the file
	 */
	public FileDictionary(File file, String encoding) {
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

		try {
			// Load the wordlist file...
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));

			// ... and store each read line in this dictionary's collection of words
			String str;
			while ((str = in.readLine()) != null) {
				this.words.add(str);
			}
			in.close();

			System.out.println("DICTIONARY COUNT: " + this.words.size());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
