package com.example.demo.cdi.servlet;

/**
 * A simple CDI service which is able to say hello to someone
 */
public class HelloService {
    String createHelloMessage(String name) {
        return "Hello " + name + "!";
    }
}
