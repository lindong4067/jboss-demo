package com.example.demo.appclient.acc.client.interceptor;

import org.jboss.ejb.client.EJBClientInterceptor;
import org.jboss.ejb.client.EJBClientInvocationContext;
import org.jboss.logging.Logger;

public class ClientInterceptor implements EJBClientInterceptor {

    private static final Logger LOGGER = Logger.getLogger(ClientInterceptor.class.getName());

    @Override
    public void handleInvocation(EJBClientInvocationContext context) throws Exception {
        String nodeName = System.getProperty("jboss.node.name");
        LOGGER.info("Adding jboss.node.name (" + nodeName + ") to the invocation context");
        context.getContextData().put("Client ", nodeName);
        context.sendRequest();
    }

    @Override
    public Object handleInvocationResult(EJBClientInvocationContext context) throws Exception {
        return context.getResult();
    }
}
