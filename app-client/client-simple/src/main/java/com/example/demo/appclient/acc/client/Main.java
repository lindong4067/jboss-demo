package com.example.demo.appclient.acc.client;

import com.example.demo.appclient.server.ejb.StatelessSession;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import java.util.Arrays;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    @EJB
    private static StatelessSession slsb;

    /** no instance necessary */
    private Main() {
    }

    public static void main(String[] args) {
        // Show that the client is started with arguments at command line
        LOG.info("Main started " + (args.length != 0 ? "with" : "without") + " arguments");
        if (args.length > 0)
            LOG.info("            " + Arrays.asList(args));

        // add an client side interceptor to provide the client machine name to the server application
        //EJBClientContext.getCurrent().registerInterceptor(0, new ClientInterceptor());
        LOG.info(slsb.getGreeting());
        slsb.invokeWithClientContext();
    }
}
