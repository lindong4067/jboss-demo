package com.example.demo.mbean.service;

public interface HelloWorldMXBean {
    long getCount();
    void setWelcomeMessage(String message);
    String getWelcomeMessage();
    String sayHello(String name);
}
