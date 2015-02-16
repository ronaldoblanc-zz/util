package com.github.ronaldoblanc.util.io.file.core;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import com.github.ronaldoblanc.util.io.file.api.MultiDataWriter;
import com.github.ronaldoblanc.util.io.file.api.NoSuchFileException;

/**
 * Singleton default implementation of <code>MultiFileDataWritter</code>.
 * 
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class SimpleMultiFileDataWritter implements MultiDataWriter {

	private static final ConcurrentMap<String, DefaultFileDataWritter> writters = new ConcurrentHashMap<String, DefaultFileDataWritter>();
	private static final Set<String> writtedData = new HashSet<String>();
	private static final MultiDataWriter INSTANCE = new SimpleMultiFileDataWritter();
	private static final Logger LOGGER = Logger
			.getLogger(SimpleMultiFileDataWritter.class.getCanonicalName());

	private SimpleMultiFileDataWritter() {
	}

	/**
	 * Singleton instance.
	 * 
	 * @return An unique instance of the MultiFileDataWritter
	 */
	public static MultiDataWriter getInstance() {
		LOGGER.fine(SimpleMultiFileDataWritter.class.getCanonicalName()
				+ " instanced");
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.io.file.api.MultiDataWriter#newWritter
	 * (java.lang.String)
	 */
	@Override
	public void newWriter(String filename) throws NoSuchFileException {
		LOGGER.finer("New writer: [" + filename + "]");
		writters.putIfAbsent(filename, new DefaultFileDataWritter(filename));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.io.file.api.MultiDataWriter#hasWritter
	 * (java.lang.String)
	 */
	@Override
	public boolean hasWriter(String filename) {
		LOGGER.finer("Has writer: [" + filename + "] ->"
				+ writters.containsKey(filename));
		return writters.containsKey(filename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.io.file.api.MultiDataWriter#write(java
	 * .lang.String, java.lang.String, boolean)
	 */
	@Override
	public void write(String filename, String data, boolean noDups)
			throws NoSuchFileException, IOException {
		DefaultFileDataWritter fileDataWritter = writters.get(filename);
		if (fileDataWritter != null) {
			if (!noDups) {
				fileDataWritter.write(data);
				writtedData.add(data);
			} else {
				if (!writtedData.contains(data)) {
					fileDataWritter.write(data);
					writtedData.add(data);
				} else {
					LOGGER.finest("Duplicate records is not allowed. Data: ["
							+ data + "]");
				}
			}
		} else {
			throw new NoSuchFileException(filename, new IllegalAccessException(
					"Writter not yet initalized."));
		}
	}
}
