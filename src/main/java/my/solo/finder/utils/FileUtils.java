package my.solo.finder.utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Class with utilitarian methods for manipulating files
 * 
 * @author marumjr
 */
public class FileUtils {

	/** The ISO-8859-1 encoding */
	public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";

	/** The UTF-8 encoding */
	public static final String ENCODING_UTF_8 = "UTF-8";

	/**
	 * Retrieve the resource file
	 * 
	 * @param filename
	 *            Name of the resource file to retrieve
	 * @return The resource file
	 * @throws URISyntaxException
	 */
	public static File retrieveResourceFile(Class<?> clazz, String filename) throws URISyntaxException {
		String pkgName = clazz.getPackage().getName().replace(".", "/") + "/";
		URI uri = clazz.getClassLoader().getResource(pkgName + filename).toURI();
		File file = new File(uri);

		return file;
	}

}
