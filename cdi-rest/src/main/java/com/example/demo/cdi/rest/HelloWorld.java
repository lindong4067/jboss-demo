package com.example.demo.cdi.rest;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/")
public class HelloWorld {
    private static Logger logger = Logger.getLogger(HelloWorld.class);

    @Inject
    HelloService helloService;

    @POST
    @Path("/json/{name}")
    @Produces("application/json")
    public String getHelloWorldJson(@PathParam("name") String name) {
        logger.info("name: " + name);
        return "{\"result\":\"" + helloService.createHelloMessage(name) + "\"}";
    }

    @POST
    @Path("/xml/{name}")
    @Produces("application/xml")
    public String getHelloWorldXML(@PathParam("name") String name) {
        logger.info("name: " + name);
        return "<xml><result>" + helloService.createHelloMessage(name) + "</result></xml>";
    }
}
