package com.example.demo.resteasy;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;

@Path("/")
public class LocatingResource {

    @Autowired
    HelloSpringResource helloSpringResource;

    @Path("locating")
    public HelloSpringResource getLocating() {
        System.out.println("Locating Resource...");
        return helloSpringResource;
    }
}
