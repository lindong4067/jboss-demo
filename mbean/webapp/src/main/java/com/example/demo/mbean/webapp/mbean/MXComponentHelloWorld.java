package com.example.demo.mbean.webapp.mbean;

import com.example.demo.mbean.webapp.service.HelloService;
import jakarta.inject.Inject;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
@Startup
public class MXComponentHelloWorld extends AbstractComponentMBean implements AnnotatedHelloWorldMBean {
    private String welcomeMessage = "Hello";
    private AtomicLong count = new AtomicLong(0);

    @Inject
    HelloService helloService;

    public MXComponentHelloWorld() {
        super("jboss-demo.webapp");
    }

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
        return helloService.createHelloMessage(welcomeMessage, name);
    }

}
