package com.example.demo.jms;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class HelloWorldJMS {
    private Context getContext() throws NamingException {

        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        env.put(Context.PROVIDER_URL, "remote://localhost:4447");
        env.put(Context.PROVIDER_URL, "remote://135.252.247.65:4447");
        env.put(Context.SECURITY_PRINCIPAL, "jms");
        env.put(Context.SECURITY_CREDENTIALS, "jms");

        return new InitialContext(env);
    }

    public void test() throws Exception {

        System.out.println("JMS HelloWorld start");

        Context ctx = getContext();

        System.out.println("Create Local JNDI Context Successful");

        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        MessageConsumer consumer = null;
        Destination destination = null;
        TextMessage message = null;

        try {
            String connectionFactoryString = "jms/RemoteConnectionFactory";
            System.out.println("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            connectionFactory = (ConnectionFactory) ctx.lookup(connectionFactoryString);
            System.out.println("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

            String destinationString = "jms/queue/test";
            System.out.println("Attempting to acquire destination \"" + destinationString + "\"");
            destination = (Destination) ctx.lookup(destinationString);
            System.out.println("Found destination \"" + destinationString + "\" in JNDI");

            // Create the JMS connection, session, producer, and consumer
            connection = connectionFactory.createConnection();
            connection = connectionFactory.createConnection("jms", "jms");
            System.out.println("create Connection Factory successful");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);
            System.out.println("create producer successful");
            consumer = session.createConsumer(destination);
            System.out.println("create consumer successful");
            connection.start();

            int count = 3;
            String content = "Hello World, JMS!";

            System.out.println("Sending " + count + " messages with content: " + content);

            // Send the specified number of messages
            for (int i = 0; i < count; i++) {
                message = session.createTextMessage(content);
                producer.send(message);
            }

            // Then receive the same number of messaes that were sent
            for (int i = 0; i < count; i++) {
                message = (TextMessage) consumer.receive(5000);
                System.out.println("Received message " + (i + 1) + " with content [" + message.getText() + "]");
            }

        } finally {

            // closing the connection takes care of the session, producer, and consumer
            if (connection != null) {
                connection.close();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new HelloWorldJMS().test();
    }
}
