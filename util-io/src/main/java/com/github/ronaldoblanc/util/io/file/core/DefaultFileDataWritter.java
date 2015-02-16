package com.github.ronaldoblanc.util.io.file.core;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.github.ronaldoblanc.util.io.file.api.DataWritter;
import com.github.ronaldoblanc.util.io.file.api.NoSuchFileException;

/**
 * Default implementation of <code>DataWritter</code>.<br/>
 * It uses <code>java.io.DataOuput</code> interface.
 * 
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class DefaultFileDataWritter implements DataWritter {
	private static final Logger LOGGER = Logger
			.getLogger(DefaultFileDataWritter.class.getCanonicalName());
	private DataOutput dataOutput;

	public DefaultFileDataWritter(String filename) throws NoSuchFileException {
		LOGGER.finer("File [" + filename + "] is ready.");
		try {
			dataOutput = new DataOutputStream(new FileOutputStream(new File(
					filename)));
		} catch (FileNotFoundException e) {
			String[] filenameParts = filename.split("/");
			String dirName = "";

			for (int index = 0; index < filenameParts.length - 1; index++) {
				dirName = dirName.concat(filenameParts[index]).concat("/");
			}

			File dir = new File(dirName);
			boolean exists = dir.exists();
			boolean created = false;

			if (!exists) {
				created = dir.mkdirs();
			}

			if (!exists && !created) {
				throw new NoSuchFileException(filename, e);
			}

			try {
				dataOutput = new DataOutputStream(new FileOutputStream(
						new File(filename)));
			} catch (FileNotFoundException e1) {
				throw new NoSuchFileException(filename, e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.io.file.api.DataWritter#write(java.lang
	 * .String)
	 */
	public void write(String data) throws IOException {
		LOGGER.finest("Writing data: [" + data + "]");
		dataOutput.writeBytes(data);
	}
}
