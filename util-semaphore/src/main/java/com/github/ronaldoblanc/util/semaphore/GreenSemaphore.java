package com.github.ronaldoblanc.util.semaphore;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class GreenSemaphore extends Semaphore {

	public GreenSemaphore() {
		setGreen();
	}
}
