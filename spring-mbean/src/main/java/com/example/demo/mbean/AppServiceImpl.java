package com.example.demo.mbean;

public class AppServiceImpl implements AppService{

    public void init() {
        System.out.println("App service init.");
    }

    @Override
    public String sendRequest(String requestType) {
        return "Received request for: " + requestType + ", wait a moment please!";
    }

    public void destroy() {
        System.out.println("App service destroy.");
    }
}
