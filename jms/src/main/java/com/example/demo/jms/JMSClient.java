package com.example.demo.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.logging.Logger;

public class JMSClient{
    private static final Logger log = Logger.getLogger(JMSClient.class.getName());

    private static final String DEFAULT_MESSAGE = "Hello, World!";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "topic/PonnbiInventoryEvent"; //jms/test/queue
    private static final String DEFAULT_MESSAGE_COUNT = "3";
    private static final String DEFAULT_USERNAME = "system";
    private static final String DEFAULT_PASSWORD = "4x5@system";
    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private static final String PROVIDER_URL = "remote://135.252.247.37:4447";

    public static void main(String[] args) {
        Context namingContext = null;

        try {

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, PROVIDER_URL);
            env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
            env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);

            namingContext = new InitialContext(env);

            // Perform the JNDI lookups
            log.info("JMS start");
            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
            log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");

            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

            String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
            log.info("Attempting to acquire destination \"" + destinationString + "\"");

            Destination destination = (Destination) namingContext.lookup(destinationString);
            log.info("Found destination \"" + destinationString + "\" in JNDI");

            int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
            String content = System.getProperty("message.content", DEFAULT_MESSAGE);

            try (JMSContext context = connectionFactory.createContext(DEFAULT_USERNAME, DEFAULT_PASSWORD)) {
                log.info("Sending " + count + " messages with content: " + content);

                // Send the specified number of messages
                for (int i = 0; i < count; i++) {
                    context.createProducer().send(destination, content);
                }

                // Create the JMS consumer
                JMSConsumer consumer = context.createConsumer(destination);
                // Then receive the same number of messages that were sent
                for (int i = 0; i < count; i++) {
                    String text = consumer.receiveBody(String.class, 5000);
                    log.info("Received message with content " + text);
                }
            }
        } catch (NamingException e) {
            log.severe(e.getMessage());
        } finally {
            if (namingContext != null) {
                try {
                    namingContext.close();
                } catch (NamingException e) {
                    log.severe(e.getMessage());
                }
            }
        }
    }
}
