package com.example.demo.mbean;

import javax.management.Notification;
import javax.management.NotificationFilter;

public class AppServiceNotificationFilter implements NotificationFilter {
    @Override
    public boolean isNotificationEnabled(Notification notification) {
        return notification.getType().startsWith("Resource");
    }
}
