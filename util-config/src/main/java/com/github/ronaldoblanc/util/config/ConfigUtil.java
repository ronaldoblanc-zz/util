package com.github.ronaldoblanc.util.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * ConfigUtil class.
 * 
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
// TODO improve
public final class ConfigUtil {
	private static final ConfigUtil INSTANCE = new ConfigUtil();
	private static final Properties properties = new Properties();
	private static final String DEFAULT_CONFIG_FILE = "config.properties";
	private static final Logger LOGGER = Logger.getLogger(ConfigUtil.class
			.getCanonicalName());

	private ConfigUtil() {
	}

	/**
	 * @param filename
	 *            The configuration data file path.
	 * @return An unique instance of the configuration util class.
	 */
	public static ConfigUtil getInstance(String filename) {
		init(filename);
		LOGGER.finest(ConfigUtil.class.getCanonicalName() + " instanced");
		return INSTANCE;
	}

	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

	public void putProperty(String property, Object value) {
		properties.put(property, value);
	}

	public <T> T getPropertyAs(Class<T> clazz, String property) {
		if (Boolean.class.equals(clazz)) {
			return clazz.cast(Boolean.valueOf(property));
		}
		return clazz.cast(properties.get(property));
	}

	/**
	 * Reads the configuration data file.
	 * 
	 * @param filename
	 */
	private static void init(String filename) {
		if (filename == null) {
			filename = DEFAULT_CONFIG_FILE;
		}
		getProperties(filename);
	}

	/**
	 * Reads the configuration data file.<br/>
	 * Returns the data as <code>Properties</>.
	 * 
	 * @param filename
	 * @return Configuration data as <code>Properties</>.
	 */
	public static Properties getProperties(String filename) {
		if (filename == null || filename.isEmpty()) {
			filename = DEFAULT_CONFIG_FILE;
		}
		InputStream is = null;
		try {
			File config = new File(filename);
			if (config.exists()) {
				is = new FileInputStream(config);
			} else {
				is = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(filename);
			}
			properties.load(is);
		} catch (Exception e) {
			LOGGER.severe("Missing configuration data. Please, check the ["
					+ filename + "] file.");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					/* Ignored */
				}
			}

		}
		return properties;
	}

}
