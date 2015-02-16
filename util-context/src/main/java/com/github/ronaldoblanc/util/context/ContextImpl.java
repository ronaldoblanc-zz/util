package com.github.ronaldoblanc.util.context;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 * @see Context
 */
public class ContextImpl implements Context {

	private static final ContextImpl INSTANCE = new ContextImpl();
	private static final ConcurrentMap<String, Object> objects = new ConcurrentHashMap<String, Object>();
	private static final Logger LOGGER = Logger.getLogger(ContextImpl.class
			.getCanonicalName());

	private ContextImpl() {
	}

	/**
	 * @return Unique instance of this context.
	 */
	public static ContextImpl getInstance() {
		LOGGER.finest(ContextImpl.class.getCanonicalName() + " instanced");
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.context.Context#put(java.lang.Class,
	 * java.lang.Object)
	 */
	@Override
	public void put(Class<?> clazz, Object value) {
		objects.put(clazz.getCanonicalName(), value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.context.Context#put(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public void put(String name, Object value) {
		objects.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.context.Context#get(java.lang.Class)
	 */
	@Override
	public Object get(Class<?> clazz) {
		return get(clazz.getCanonicalName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.ronaldoblanc.util.context.Context#get(java.lang.String)
	 */
	@Override
	public Object get(String name) {
		return objects.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.com.github.ronaldoblanc.util.context.Context#getAs(java.lang.Class)
	 */
	@Override
	public <T> T getAs(Class<T> clazz) {
		return clazz.cast(get(clazz.getCanonicalName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.com.github.ronaldoblanc.util.context.Context#getAs(java.lang.String ,
	 * java.lang.Class)
	 */
	@Override
	public <T> T getAs(String name, Class<T> clazz) {
		return clazz.cast(get(name));
	}
}
