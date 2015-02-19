package com.github.ronaldoblanc.util.io.file.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleTextFileReader {

	public static void printDataOut(final String filename) throws IOException {
		printDataOut(filename, false, System.out);
	}

	public static void printDataOut(final String filename,
			PrintStream printStream) throws IOException {
		printDataOut(filename, false, printStream);
	}

	public static void cleanPrintDataOut(final String filename)
			throws IOException {
		printDataOut(filename, true, System.out);
	}

	public static void cleanPrintDataOut(final String filename,
			PrintStream printStream) throws IOException {
		printDataOut(filename, true, printStream);
	}

	private static void printDataOut(final String filename, boolean clean,
			PrintStream printStream) throws IOException {
		if (!clean) {
			printStream.println("File content:");
			printStream.println("========================================");
		}
		for (String line : readLines(filename)) {
			if (line.isEmpty()) {
				continue;
			}
			printStream.println(line);
		}
		if (!clean) {
			printStream.println("========================================");
		}
	}

	public static List<String> readLines(final String filename)
			throws IOException {
		final File inputFile = new File(filename);
		final FileInputStream fis = new FileInputStream(inputFile);
		final BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		final List<String> result = new ArrayList<String>();
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				result.add(line);
			}
			return result;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}
}
