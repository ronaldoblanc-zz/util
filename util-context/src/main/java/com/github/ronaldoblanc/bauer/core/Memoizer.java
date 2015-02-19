package com.github.ronaldoblanc.bauer.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public final class Memoizer {

	private static final int MAX_ELEMENTS = 100000;
	private static final Memoizer INSTANCE = new Memoizer();
	private static final ConcurrentMap<Object, Object> results = new ConcurrentHashMap<Object, Object>();
	private static final Logger LOGGER = Logger.getLogger(Memoizer.class
			.getCanonicalName());

	private Memoizer() {
	}

	public static Memoizer getInstance() {
		LOGGER.finest(Memoizer.class.getCanonicalName() + " instanced");
		return INSTANCE;
	}

	public void put(Object key, Object result) {
		LOGGER.finest("[Key: " + key + ", Value: " + result + "] added");
		if (results.size() >= MAX_ELEMENTS) {
			results.clear();
		}
		results.put(key, result);
	}

	public <T> T getResultAs(Object key, Class<T> clazz) {
		return clazz.cast(results.get(key));
	}
}
