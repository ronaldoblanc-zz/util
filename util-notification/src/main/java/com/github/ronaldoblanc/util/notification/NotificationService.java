package com.github.ronaldoblanc.util.notification;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Ronaldo Blanc <ronaldoblanc at gmail.com>
 */
public class NotificationService {

	private static final LinkedBlockingQueue<String> notifications = new LinkedBlockingQueue<String>();
	private static final NotificationService INSTANCE = new NotificationService();

	private NotificationService() {
	}

	public NotificationService getInstance() {
		return INSTANCE;
	}

	public void addNotification(String notification) {
		notifications.add(notification);
	}

	public String getNotification() {
		return notifications.poll();
	}
}
