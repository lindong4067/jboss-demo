package com.example.demo.mbean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppStartup extends AbstractAppStartup implements AppStartupMBean{

    private ClassPathXmlApplicationContext applicationContext;

    public void startService() {
        System.out.println("App server starting...");
        long before = System.currentTimeMillis();
        applicationContext = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        //TODO
        long after = System.currentTimeMillis();
        System.out.println("App server started. spend : " + (after - before) + " ms");
    }

    public void stopService() {
        System.out.println("App server stopping...");
        long before = System.currentTimeMillis();
        if (applicationContext != null) {
            applicationContext.close();
            System.out.println("Application context closing...");
        }
        //TODO
        long after = System.currentTimeMillis();
        System.out.println("App server stopped. spend : " + (after - before) + " ms");
    }
}
