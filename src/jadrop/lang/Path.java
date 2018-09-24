package jadrop.lang;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.*;

public class Path {

	/**
	 * Represents a sequence of zero or more names which describe a path through
	 * the namespace for a given project. For example, "whiley/lang/Math" is a
	 * valid ID with three components: "whiley","lang","Math".
	 *
	 * @author David J. Pearce
	 *
	 */
	public interface ID extends Iterable<String>, Comparable<ID> {

		/**
		 * Get the number of components that make up this ID.
		 * @return
		 */
		public int size();

		/**
		 * Return the component at a given index.
		 * @param index
		 * @return
		 */
		public String get(int index);

		/**
		 * A convenience function that gets the last component of this path.
		 *
		 * @return
		 */
		public String last();

		/**
		 * Get the parent of this path.
		 *
		 * @return
		 */
		public ID parent();

		/**
		 * Get a sub ID from this id, which consists of those components between
		 * start and end (exclusive).
		 *
		 * @param start
		 *            --- starting component index
		 * @param start
		 *            --- one past last component index
		 * @return
		 */
		public ID subpath(int start, int end);

		/**
		 * Append a component onto the end of this id.
		 *
		 * @param component
		 *            --- to be appended
		 * @return
		 */
		public ID append(String component);
	}

	/**
	 * Represents an abstract or physical item of some sort which is reachable
	 * from a <code>Root</code>. Valid instances of <code>Item</code> include
	 * those valid instances of <code>Entry</code> and <code>Folder</code>.
	 */
	public interface Item {

	}

	/**
	 * Represents a physical item of some sort which is reachable from a
	 * <code>Root</code>. Valid instances of <code>Entry</code> may correspond
	 * to files on the file system, entries in a Jar file, or abstractions from
	 * other tools (e.g. eclipse's <code>IFile</code>).
	 *
	 * @author David J. Pearce
	 *
	 */
	public interface Entry extends Item {
		/**
		 * Return the identify of this item.
		 *
		 * @return
		 */
		public ID id();

		/**
		 * Get the size of this entry in bytes.
		 *
		 * @return
		 */
		public long size();

		/**
		 * Open a generic input stream to this entry. This is used for downloading the
		 * contents of the entry.
		 *
		 * @return
		 * @throws IOException
		 */
		public InputStream inputStream() throws IOException;

		/**
		 * Open a generic output stream to the entry. This is used for uploading the
		 * contents of the entry.
		 *
		 * @return
		 * @throws IOException
		 */
		public OutputStream outputStream() throws IOException;
	}

	/**
	 * Represents the root of a hierarchy of named entries. A instance of root
	 * may correspond to a file system directory, a Jar file, or some other
	 * abstraction representings a collection of files (e.g. eclipse's
	 * <code>IContainer</code>).
	 *
	 * @author David J. Pearce
	 *
	 */
	public interface Root {

	}

}
