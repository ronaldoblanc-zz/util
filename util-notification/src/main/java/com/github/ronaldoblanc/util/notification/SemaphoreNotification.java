package com.github.ronaldoblanc.util.notification;

import com.github.ronaldoblanc.util.semaphore.*;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class SemaphoreNotification extends MessageNotification {

	private final Semaphore semaphore;

	public SemaphoreNotification(final String message, final Semaphore semaphore) {
		super(message);
		this.semaphore = semaphore;
	}

	@Override
	public boolean isSemaphoreStop() {
		return semaphore.isRed();
	}

	@Override
	public boolean isSemaphoreWait() {
		return semaphore.isYellow();
	}

	@Override
	public boolean isSemaphoreGo() {
		return semaphore.isGreen();
	}

}
