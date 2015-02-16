package com.github.ronaldoblanc.util.io.file.core;

import java.io.IOException;
import java.util.logging.Logger;

import com.github.ronaldoblanc.util.io.file.api.NoSuchFileException;

/**
 * Writes out data on the desired file.<br/>
 * Very useful to write data on a new file.<br/>
 * The <code>init(filename)</code> method creates a file named
 * <code>filename</code> and opens an output stream to it.<br/>
 * The <code>writeData(data) method writes out data on the opened file.
 * 
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class SimpleFileDataWriter {
	private static DefaultFileDataWritter fileDataWritter;
	private static final Logger LOGGER = Logger
			.getLogger(SimpleFileDataWriter.class.getCanonicalName());

	/**
	 * Opens a file for write.
	 * 
	 * @param filename
	 * @throws NoSuchFileException
	 */
	public static void init(String filename) throws NoSuchFileException {
		LOGGER.fine(SimpleFileDataWriter.class.getCanonicalName()
				+ " initialized");
		fileDataWritter = new DefaultFileDataWritter(filename);
	}

	/**
	 * Writes data on file.
	 * 
	 * @param data
	 * @throws IOException
	 */
	public static void write(String data) throws IOException {
		LOGGER.finest("Output data: [" + data + "]");
		fileDataWritter.write(data);
	}
}
