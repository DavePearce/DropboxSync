package jadrop.util;

import java.io.*;
import java.util.*;

import jadrop.lang.Path;
import jadrop.lang.Path.ID;

public class DirectoryRoot implements Iterable<Path.Entry>  {
	/**
	 * The root on the local file system of this directory root.
	 */
	private final File root;

	private  ArrayList<Path.Entry> entries;

	public DirectoryRoot(File root) {
		this.root = root;
	}

	@Override
	public Iterator<Path.Entry> iterator() {
		scan();
		return entries.iterator();
	}

	private void scan() {
		if (entries == null) {
			this.entries = new ArrayList<>();
			// Scan all files
			scan(root, Trie.ROOT, entries);
			// Sort files by ID
			Collections.sort(entries, new Comparator<Path.Entry>() {
				@Override
				public int compare(jadrop.lang.Path.Entry o1, jadrop.lang.Path.Entry o2) {
					return o1.id().compareTo(o2.id());
				}
			});
		}
	}

	private void scan(File dir, Path.ID path, ArrayList<Path.Entry> entries) {
		File[] files = dir.listFiles();
		if (files != null) {
			for (File f : files) {
				Path.ID id = path.append(f.getName());
				if (f.isDirectory()) {
					// recursive directory
					scan(f, id, entries);
				} else {
					entries.add(new Entry(id, f));
				}
			}
		}
	}

	private static class Entry implements Path.Entry {
		private final Path.ID id;
		private final File file;

		public Entry(Path.ID id, File file) {
			this.id = id;
			this.file = file;
		}

		@Override
		public ID id() {
			return id;
		}

		@Override
		public long size() {
			return file.length();
		}

		@Override
		public InputStream inputStream() throws IOException {
			return new FileInputStream(file);
		}

		@Override
		public OutputStream outputStream() throws IOException {
			return new FileOutputStream(file);
		}

	}
}
