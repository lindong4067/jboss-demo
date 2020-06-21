package com.example.demo.mbean.webapp.service;

public class HelloService {
    public String createHelloMessage(String welcome, String name) {
        return welcome + " " + name + "!";
    }
}
