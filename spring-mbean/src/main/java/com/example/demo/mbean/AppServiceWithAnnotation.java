package com.example.demo.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource(objectName = "app.server:service=AppServiceWithAnnotation", description = "AppServiceWithAnnotation")
@Component
public class AppServiceWithAnnotation implements AppService{

    private String requestType;

    public String getRequestType() {
        return requestType;
    }

    @ManagedAttribute
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void init() {
        System.out.println("App service with annotation init.");
    }

    @ManagedOperation
    @Override
    public String sendRequest(String requestType) {
        return "Received request for: " + requestType + ", wait a moment please!";
    }

    public void destroy() {
        System.out.println("App service with annotation destroy.");
    }
}
