package com.example.demo.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStartup implements ServerStartupMBean {

    private ClassPathXmlApplicationContext applicationContext;

    @Override
    public void start() throws Exception {
        System.out.println("Jms server starting...");
        long before = System.currentTimeMillis();
        applicationContext = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        long after = System.currentTimeMillis();
        System.out.println("Jms server started. spend : " + (after - before) + " ms");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Jms server stopping...");
        long before = System.currentTimeMillis();
        if (applicationContext != null) {
            applicationContext.close();
            System.out.println("Application context closing...");
        }
        long after = System.currentTimeMillis();
        System.out.println("Jms server stopped. spend : " + (after - before) + " ms");
    }
}
