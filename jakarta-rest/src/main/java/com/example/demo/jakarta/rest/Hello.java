package com.example.demo.jakarta.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicInteger;

@Path("")
public class Hello {
    private String message;
    private static AtomicInteger ai = new AtomicInteger(0);

    @GET
    @Path("hello")
    public Response hello() {
        return Response.ok("Hello World!").build();
    }

    @GET
    @Path("person")
    @Produces("application/json; charset=UTF-8")
    public Response sayHello(@QueryParam("person") String person) {
        Person person1 = new Person(ai.getAndIncrement(), person);
        return Response.ok(person1).build();
    }

    @GET
    @Path("instance")
    @Produces("application/json; charset=UTF-8")
    public Response seeHelloInstance(@QueryParam("person") String person) {
        Person person1 = new Person(ai.getAndIncrement(), person, this.toString());
        return Response.ok(person1).build();
    }
}
