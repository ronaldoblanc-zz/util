package com.github.ronaldoblanc.util.io.file.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.github.ronaldoblanc.util.io.file.api.DataReader;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
@SuppressWarnings({ "rawtypes" })
public class SimpleDelimitedFileDataReader implements DataReader {

	private static final DataReader INSTANCE = new SimpleDelimitedFileDataReader();
	private static final String DEFAULT_DELIMITER = ";";
	private static final Logger LOGGER = Logger
			.getLogger(SimpleDelimitedFileDataReader.class.getCanonicalName());

	private SimpleDelimitedFileDataReader() {
	}

	public static DataReader getInstance() {
		LOGGER.fine(SimpleDelimitedFileDataReader.class.getCanonicalName()
				+ " instanced");
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.github.ronaldoblanc.util.io.file.api.DataReadergetDataAsMap(java
	 * .io.InputStream, java.lang.Class, java.lang.String, boolean)
	 */
	@Override
	public List<Map<String, String>> getDataAsMap(InputStream is,
			Class<? extends Enum> clazz, String delimiter, boolean hasHeader)
			throws IOException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		if (hasHeader) {
			br.readLine();
		}
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				continue;
			}
			result.add(populate(clazz, line, delimiter));
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	private Map<String, String> populate(Class<? extends Enum> clazz,
			String line, String delimiter) {
		final String[] fields = lineToArray(line, delimiter);
		LOGGER.finest("Fields :" + Arrays.asList(fields));
		Map<String, String> result = new HashMap<String, String>();
		for (Object field : EnumSet.allOf(clazz)) {
			if ((fields.length - 1) < ((Enum<?>) field).ordinal()) {
				LOGGER.warning("The expected record fields count is: ["
						+ EnumSet.allOf(clazz).size() + "], the actual is : ["
						+ fields.length + "]");
				result = Collections.EMPTY_MAP;
				break;
			}
			LOGGER.finest("Field: [" + ((Enum<?>) field).name() + " -> "
					+ fields[((Enum<?>) field).ordinal()] + "]");
			result.put(((Enum<?>) field).name(),
					fields[((Enum<?>) field).ordinal()]);
		}
		LOGGER.finer("Record: " + result);
		return result;
	}

	private String[] lineToArray(String line, String delimiter) {
		if (delimiter == null) {
			delimiter = DEFAULT_DELIMITER;
		}
		LOGGER.finest("Delimiter: [" + delimiter + "]");
		return line.split(delimiter);
	}
}
