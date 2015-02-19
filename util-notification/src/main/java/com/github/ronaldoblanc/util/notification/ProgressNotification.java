package com.github.ronaldoblanc.util.notification;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.ronaldoblanc.util.notification.api.Notification;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class ProgressNotification implements Notification {
	private static final BigDecimal HUNDRED = new BigDecimal(100);
	private final int progress;

	public ProgressNotification(final int itemId, final int totalItens) {
		progress = totalItens > 0 ? new BigDecimal(itemId)
				.divide(new BigDecimal(totalItens), 2, RoundingMode.HALF_UP)
				.multiply(HUNDRED).intValue() : 0;
	}

	@Override
	public String getEventMessage() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSemaphoreStop() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSemaphoreWait() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSemaphoreGo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getProgress() {
		return progress;
	}

	@Override
	public String getRawMessage() {
		throw new UnsupportedOperationException();
	}
}
