package jadrop;

import java.io.File;

import jadrop.lang.Path;
import jadrop.util.DirectoryRoot;

public class Main {
	public static void main(String[] args) {
		DirectoryRoot dir = new DirectoryRoot(new File("/Users/djp/"));
		for(Path.Entry entry : dir) {
			System.out.println("FILE: " + entry.id() + ", " + entry.size() + "bytes");
		}
	}
}
