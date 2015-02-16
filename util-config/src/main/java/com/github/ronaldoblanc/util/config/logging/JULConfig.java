package com.github.ronaldoblanc.util.config.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
// TODO util project
public class JULConfig {

	private static final String JAVA_UTIL_LOGGING_CONFIG_FILE = "java.util.logging.config.file";

	public static void config(InputStream resourceAsStream)
			throws SecurityException, IOException {
		String logFile = System.getProperty(JAVA_UTIL_LOGGING_CONFIG_FILE);
		if (logFile == null) {
			if (resourceAsStream != null) {
				LogManager.getLogManager().readConfiguration(resourceAsStream);
			} else {
				throw new RuntimeException(
						"No java util logging configuration file found");
			}
		}
	}
}
