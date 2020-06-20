package com.example.demo.appclient.server.ejb;

import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class StatelessSessionBean implements StatelessSession{
    private static final Logger LOGGER = Logger.getLogger(StatelessSessionBean.class);

    @Resource
    SessionContext sessionContext;

    @Override
    public void invokeWithClientContext() {
        LOGGER.info("ClientContext is here = " + sessionContext.getContextData());
    }

    @Override
    public String getGreeting() {
        return "Hello from StatelessSessionBean@" + System.getProperty("jboss.node.name");
    }
}
