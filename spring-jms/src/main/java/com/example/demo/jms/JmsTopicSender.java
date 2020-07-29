package com.example.demo.jms;


import org.springframework.jms.core.JmsTemplate;

import javax.jms.Topic;

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
    }
}
