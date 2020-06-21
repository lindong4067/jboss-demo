package com.example.demo.mbean.webapp.mbean;

public interface HelloWorldMXBean {
    long getCount();
    void setWelcomeMessage(String message);
    String getWelcomeMessage();
    String sayHello(String name);
}
