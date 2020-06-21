package com.example.demo.mbean.webapp.mbean;

import javax.management.MXBean;

@MXBean
public interface AnnotatedHelloWorldMBean {
    long getCount();
    void setWelcomeMessage(String message);
    String getWelcomeMessage();
    String sayHello(String name);
}
