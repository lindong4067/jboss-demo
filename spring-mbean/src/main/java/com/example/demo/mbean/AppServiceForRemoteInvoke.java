package com.example.demo.mbean;

import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

import javax.management.Notification;
import java.util.concurrent.atomic.AtomicInteger;

public class AppServiceForRemoteInvoke implements AppService, NotificationPublisherAware {

    private AtomicInteger index = new AtomicInteger(1);

    private NotificationPublisher notificationPublisher;

    public void init() {
        System.out.println("App service for remote invoke init.");
    }

    @Override
    public String sendRequest(String requestType) {
        this.notificationPublisher.sendNotification(new Notification(requestType, this,
                index.getAndIncrement(), "Please handle this request!"));
        return "Received request for: " + requestType + ", send notification!";
    }

    public void destroy() {
        System.out.println("App service for remote invoke destroy.");
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }
}
