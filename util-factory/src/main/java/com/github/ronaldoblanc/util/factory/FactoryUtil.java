package com.github.ronaldoblanc.util.factory;

/**
 * Utilitary class to help avoid javac compile bug.<br/>
 * <a href="http://bugs.sun.com/view_bug.do?bug_id=6724345">http://bugs.sun.com/
 * view_bug.do?bug_id=6724345</a>
 * 
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public final class FactoryUtil {

	private FactoryUtil() {
	}

	public static <R> R getInstance(Factory<R> factory) throws Exception {
		return factory.newInstance();
	}
}
