package com.github.ronaldoblanc.util.notification;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.ronaldoblanc.util.notification.api.Notification;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class MessageNotification implements Notification {
	public static final String NEW_LINE = "\n";
	private final Date eventDate;
	private final String message;

	public MessageNotification(final String message) {
		this.eventDate = new Date();
		this.message = message;
	}

	@Override
	public String getEventMessage() {
		return eventDate == null ? message : new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.S").format(eventDate)
				+ " - "
				+ message
				+ NEW_LINE;
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
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRawMessage() {
		return message;
	}

}
