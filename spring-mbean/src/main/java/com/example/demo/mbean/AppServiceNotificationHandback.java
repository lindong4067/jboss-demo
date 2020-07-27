package com.example.demo.mbean;

public class AppServiceNotificationHandback {
    private String message;

    public AppServiceNotificationHandback(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AppServiceNotificationHandback{" +
                "message='" + message + '\'' +
                '}';
    }
}
