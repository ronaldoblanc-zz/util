package com.github.ronaldoblanc.util.spring;

import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bauer spring framework utility class.
 * 
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public final class SpringUtil {

	/**
	 * Default application Context
	 */
	public static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	/**
	 * Default data source spring bean
	 */
	public static final String DATA_SOURCE = "dataSource";
	private static final SpringUtil INSTANCE = new SpringUtil();
	private static final Logger LOGGER = Logger.getLogger(SpringUtil.class
			.getCanonicalName());

	private SpringUtil() {
	}

	public static SpringUtil getInstance() {
		LOGGER.finer(SpringUtil.class.getCanonicalName() + " instanced");
		return INSTANCE;
	}

	/**
	 * Starts up a spring app context and dynamically configure the desired bean<br/>
	 * with data by <code>config</code> parameter.<br/>
	 * Default <code>applicationContextXml</code> is "aplicationContext.xml",<br/>
	 * so calling <code>startApp(null, config)</code> will try to find the<br/>
	 * <code>applicationContextXml</code> in the classpath.<br/>
	 * ***IMPORTANT***<br/>
	 * This API does not support multiple beans yet.
	 * 
	 * @param applicationContextXml
	 * @return
	 */
	public ApplicationContext startApp(String applicationContextXml,
			Properties config) {
		checkAndSetApplicationContext(applicationContextXml);
		LOGGER.finest("Application Context: [" + applicationContextXml + "]");
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { applicationContextXml }, false);
		if (config == null) {
			throw new RuntimeException("No config data informed");
		}
		context.addBeanFactoryPostProcessor(new CustomPropertyConfigurer(config));
		try {
			context.refresh();
		} catch (BeanCreationException e) {
			LOGGER.severe(e.getRootCause().getMessage());
			throw new RuntimeException(e);
		}
		return context;
	}

	public ApplicationContext startApp(String applicationContextXml) {
		checkAndSetApplicationContext(applicationContextXml);
		LOGGER.finest("Application Context: [" + applicationContextXml + "]");
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { applicationContextXml }, true);
		return context;
	}

	private void checkAndSetApplicationContext(String applicationContextXml) {
		if (applicationContextXml == null) {
			applicationContextXml = APPLICATION_CONTEXT_XML;
		}
	}
}
