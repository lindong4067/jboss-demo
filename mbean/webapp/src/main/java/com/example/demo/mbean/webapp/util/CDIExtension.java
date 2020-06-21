package com.example.demo.mbean.webapp.util;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.BeforeBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;

public class CDIExtension implements Extension {
    private static BeanManager beanManager;

    private static void setBeanManager(BeanManager beanManager) {
        CDIExtension.beanManager = beanManager;
    }

    void beforeBeanDiscovery(@Observes BeforeBeanDiscovery beforeBeanDiscovery, BeanManager beanManager) {
        setBeanManager(beanManager);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> beanType) {
        final Bean<T> bean = (Bean<T>) beanManager.getBeans(beanType).iterator().next();
        final CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
        return (T) beanManager.getReference(bean, beanType, ctx);
    }
}
