package com.example.demo.mbean;

public interface AppClient {
    void setRequestType(String requestType);
    String getRequestType();
    String invokeLocal(String requestType);
    String invokeRemote(String requestType);
}
