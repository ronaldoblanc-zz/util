package com.github.ronaldoblanc.util.config.exception;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class InvalidConfigurationException extends Exception {

	private static final long serialVersionUID = 6753529828398136275L;

	public InvalidConfigurationException() {
		super();
	}

	public InvalidConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidConfigurationException(String message) {
		super(message);
	}

	public InvalidConfigurationException(Throwable cause) {
		super(cause);
	}
}
