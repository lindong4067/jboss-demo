package com.example.demo.resteasy;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class HelloSpringResource {

    @Autowired
    GreetingBean greetingBean;

    @GET
    @Produces("text/html")
    public Response getDefault(@Context UriInfo uriInfo) {
        String baseUri = uriInfo.getBaseUri().toString();
        if (!baseUri.endsWith("/")) baseUri += '/';
        String msg = "Hello. <br> Please try <a href='"+baseUri+"hello?name=yourname'>spring-resteasy/hello?name=yourname</a>"
                + "<br> Or try <a href='"+baseUri+"basic'>spring-resteasy/basic</a>"
                + "<br> Or try <a href='"+baseUri+"queryParam?param=query'>spring-resteasy/queryParam?param=query</a>"
                + "<br> Or try <a href='"+baseUri+"matrixParam;param=matrix'>spring-resteasy/matrixParam;param=matrix</a>"
                + "<br> Or try <a href='"+baseUri+"uriParam/789'>spring-resteasy/uriParam/789</a>"
                + "<br> Or try <a href='"+baseUri+"locating/hello?name=yourname'>spring-resteasy/locating/hello?name=yourname</a>"
                + "<br> Or try <a href='"+baseUri+"locating/basic'>spring-resteasy/locating/basic</a>"
                + "<br> Or try <a href='"+baseUri+"locating/queryParam?param=query'>spring-resteasy/locating/queryParam?param=query</a>"
                + "<br> Or try <a href='"+baseUri+"locating/matrixParam;param=matrix'>spring-resteasy/locating/matrixParam;param=matrix</a>"
                + "<br> Or try <a href='"+baseUri+"locating/uriParam/789'>spring-resteasy/locating/uriParam/789</a>";
        System.out.println("getDefault()");
        return Response.ok(msg).build();
    }

    @GET
    @Path("hello")
    @Produces("text/plain")
    public Response sayHello(@QueryParam("name") String name) {
        String greet = greetingBean.greet(name);
        System.out.println("Sending greeting: " + greet);
        return Response.ok(greet).build();
    }

    @GET
    @Path("basic")
    @Produces("text/plain")
    public String getBasic() {
        System.out.println("getBasic()");
        return "basic";
    }

    @GET
    @Path("queryParam")
    @Produces("text/plain")
    public String getQueryParam(@QueryParam("param") String param) {
        return param;
    }

    @GET
    @Path("uriParam/{param}")
    @Produces("text/plain")
    public int getUriParam(@PathParam("param") int param) {
        return param;
    }

    @GET
    @Path("matrixParam")
    @Produces("text/plain")
    public String getMatrixParam(@MatrixParam("param") String param) {
        return param;
    }
}
