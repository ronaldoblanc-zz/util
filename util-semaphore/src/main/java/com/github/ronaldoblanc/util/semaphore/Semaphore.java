package com.github.ronaldoblanc.util.semaphore;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public abstract class Semaphore {

	private boolean red;
	private boolean yellow;
	private boolean green;

	protected Semaphore() {
	}

	public boolean isRed() {
		return red;
	}

	public boolean isYellow() {
		return yellow;
	}

	public boolean isGreen() {
		return green;
	}

	public void setRed() {
		this.red = true;
		this.yellow = false;
		this.green = false;
	}

	public void setYellow() {
		this.red = false;
		this.yellow = true;
		this.green = false;

	}

	public void setGreen() {
		this.red = false;
		this.yellow = false;
		this.green = true;
	}
}
