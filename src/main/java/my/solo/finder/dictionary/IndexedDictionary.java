package my.solo.finder.dictionary;

import java.util.Set;

/**
 * Represents a dictionary in which the words can be accessed through some kind of index
 * 
 * @param <T>
 *            The type of the hash used to index the words in this dictionary
 * 
 * @author marumjr
 */
public interface IndexedDictionary<T> extends Dictionary {

	/**
	 * Retrieves all words in this dictionary under an index
	 * 
	 * @param index
	 *            Index in which we'll look for words
	 * @return all words under said index
	 */
	public Set<String> retrieveWordsByIndex(T index);

}
