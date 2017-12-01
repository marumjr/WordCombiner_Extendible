package my.solo.finder.dictionary;

import java.util.Set;

/**
 * Represents a collection of valid words found in a dictionary
 * 
 * @author marumjr
 */
public interface Dictionary {

	/**
	 * @return the collection of words contained by this dictionary
	 */
	public Set<String> getWords();

	/**
	 * Checks if a word can be found this dictionary
	 * 
	 * @param word
	 *            Word to look for
	 * @return <code>true</code> if it belongs to this dictionary, <code>false</code> otherwise
	 */
	public boolean contains(String word);

}
