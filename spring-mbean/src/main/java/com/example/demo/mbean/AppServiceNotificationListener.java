package com.example.demo.mbean;

import javax.management.Notification;
import javax.management.NotificationListener;

public class AppServiceNotificationListener implements NotificationListener {

    @Override
    public void handleNotification(Notification notification, Object handback) {
        System.out.println(notification);
        System.out.println(handback);
    }
}
