package com.example.demo.jms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessageConsumer {

    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String receiveMessage() {
        Message ineExport = jmsTemplate.receive("INEExport");
        if (ineExport instanceof TextMessage) {
            TextMessage message = (TextMessage) ineExport;
            try {
                return message.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return "Not support message type!";
    }
}
