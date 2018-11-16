package me.peak;

import java.io.*;

public class FileSnippet {
	public static void main(String[] args) throws IOException {
		lookLog();
	}

	public static void lookLog() throws IOException {
		FileReader reader = new FileReader(new File("e:/stdout.log"));
		BufferedReader reader1 = new BufferedReader(reader);
		String line = null;
		while ((line=reader1.readLine()) != null) {
			if (line.contains("queryStuListForExport")) {
				System.out.println(line);
			}
		}
	}
}
