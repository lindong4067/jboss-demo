package com.example.demo.resteasy;

public class GreetingBean {
    private String greeting;

    private final String DEFAULT_NAME = "I'm sorry I don't know your name";

    public GreetingBean(String greeting) {
        this.greeting = greeting;
    }

    public String greet(String name) {
        if (name == null) {
            name = DEFAULT_NAME;
        }
        return greeting + " " + name + ".";
    }
}
