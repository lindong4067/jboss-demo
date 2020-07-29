package com.example.demo.jms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

public class JmsQueueSender {

    private JmsTemplate jmsTemplate;

    private Queue queue;

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void simpleSend() {
        this.jmsTemplate.send(this.queue, session -> session.createTextMessage("Hello JMS Queue!"));
    }
}
