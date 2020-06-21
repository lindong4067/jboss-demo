package com.example.demo.mbean.service;

import org.jboss.logging.Logger;

import java.util.concurrent.atomic.AtomicLong;

public class MXPojoHelloWorld implements HelloWorldMXBean{
    private static final Logger log = Logger.getLogger(MXPojoHelloWorld.class.getName());

    private String welcomeMessage = "Hello";
    private AtomicLong count = new AtomicLong(0);

    @Override
    public long getCount() {
        return count.get();
    }

    @Override
    public void setWelcomeMessage(String message) {
        if (message != null && message.trim().length() > 0)
            this.welcomeMessage = message;
    }

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @Override
    public String sayHello(String name) {
        count.incrementAndGet();
        return welcomeMessage + " " + name + "!";
    }

    public void start() throws Exception {
        log.info(" >> MXPojoHelloWorld.start() invoked");
    }

    public void stop() throws Exception {
        log.info(" << MXPojoHelloWorld.stop()  invoked");
    }
}
