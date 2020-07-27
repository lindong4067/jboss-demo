package com.example.demo.mbean;

public interface AppClient {
    String invokeLocal(String requestType);
    String invokeRemote(String requestType);
}
