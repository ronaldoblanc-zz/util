package com.github.ronaldoblanc.util.semaphore;

import com.github.ronaldoblanc.util.factory.Factory;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public enum SemaphoreFactory implements Factory<Semaphore> {

	STOP("Red") {
		public Semaphore newInstance() {
			return new RedSemaphore();
		}
	},
	WAIT("Yellow") {
		public Semaphore newInstance() {
			return new YellowSemaphore();
		}
	},
	GO("Green") {
		public Semaphore newInstance() {
			return new GreenSemaphore();
		}
	};

	private String state;

	private SemaphoreFactory(String color) {
		state = color;
	}

	public String getState() {
		return state;
	}
}
