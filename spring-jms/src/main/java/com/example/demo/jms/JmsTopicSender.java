package com.example.demo.jms;


import org.springframework.jms.core.JmsTemplate;

import javax.jms.Topic;
import javax.naming.Context;
import java.util.Properties;

public class JmsTopicSender {

    private JmsTemplate jmsTemplate;

    private Topic topic;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void simpleSend() {
        this.jmsTemplate.send(this.topic, session -> session.createTextMessage("Hello JMS Queue!"));
    }

    public void customSend(String message) {
        this.jmsTemplate.send(this.topic, session -> session.createTextMessage(message));

        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "INITIAL_CONTEXT_FACTORY");// 该KEY的值为初始化Context的工厂类,JNDI驱动的类名
        env.put(Context.PROVIDER_URL, "PROVIDER_URL");// 该KEY的值为Context服务提供者的URL.命名服务提供者的URL
        env.put(Context.SECURITY_PRINCIPAL, "DEFAULT_USERNAME");
        env.put(Context.SECURITY_CREDENTIALS, "DEFAULT_PASSWORD");//应用用户的登录名,密码.
    }
}
