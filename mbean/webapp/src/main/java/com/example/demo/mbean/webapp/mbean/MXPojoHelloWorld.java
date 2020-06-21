package com.example.demo.mbean.webapp.mbean;

import com.example.demo.mbean.webapp.service.HelloService;
import com.example.demo.mbean.webapp.util.CDIExtension;

import java.util.concurrent.atomic.AtomicLong;

public class MXPojoHelloWorld implements HelloWorldMXBean {
    private String welcomeMessage = "Hello";
    private AtomicLong count = new AtomicLong(0);

    @Override
    public long getCount() {
        return count.get();
    }

    @Override
    public void setWelcomeMessage(String message) {
        if (message != null && message.trim().length() > 0)
            welcomeMessage = message;
    }

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @Override
    public String sayHello(String name) {
        count.incrementAndGet();
        HelloService helloService = CDIExtension.getBean(HelloService.class);
        return helloService.createHelloMessage(welcomeMessage, name);
    }
}
