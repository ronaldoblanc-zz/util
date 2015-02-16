package com.github.ronaldoblanc.util.io.file.api;

import java.io.IOException;

/**
 * Writes out data.
 * 
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public interface DataWritter {

	/**
	 * Writes the <code>data</code> to the destination.
	 * 
	 * @param data
	 * @throws IOException
	 */
	public void write(String data) throws IOException;
}
