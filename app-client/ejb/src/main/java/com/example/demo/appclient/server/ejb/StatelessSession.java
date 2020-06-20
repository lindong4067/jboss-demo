package com.example.demo.appclient.server.ejb;

import javax.ejb.Remote;

@Remote
public interface StatelessSession {
    void invokeWithClientContext();
    String getGreeting();
}
