package com.test.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import java.io.IOException;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsClient {

    private static final Logger logger = LoggerFactory.getLogger(JmsClient.class);

    private static String brokerUrl;
    private static String queueName;
    private static String amqUserName;
    private static String amqPassword;
    private static boolean transacted = false;

    private static Properties prop = new Properties();

    static {
        try {
            //load a properties file from class path, inside static method
            prop.load(JmsClient.class.getClassLoader().getResourceAsStream("config.properties"));

            //get the property value and print it out
            brokerUrl = prop.getProperty("brokerUrl");
            queueName = prop.getProperty("queueName");
            amqUserName = prop.getProperty("amqUserName");
            amqPassword = prop.getProperty("amqPassword");

        } catch (IOException ex) {
            logger.error("Caught: " + ex);
            ex.printStackTrace();
        }
    }

    public static void sendJms(String jmsMessage){

        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        Destination destination = null;

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(amqUserName);
        connectionFactory.setPassword(amqPassword);

        try {
            connection = connectionFactory.createConnection();

            if (connection == null) {
                throw new RuntimeException("Fail to get Connection Factory");
            }

            connection.start();
            session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);

            // Create the destination
            destination = session.createQueue(queueName);

            if (destination == null) {
                throw new RuntimeException("Fail to get destination queue");
            }

            logger.info("JMS work successfully activated on destination {}", destination);

            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a messages
            TextMessage message = session.createTextMessage(jmsMessage);

            // Tell the producer to send the message
            logger.info("Sent message: "+ message.hashCode());

            producer.send(message);

        } catch (Exception e) {
            logger.error("Caught: " + e);
            e.printStackTrace();
        } finally {
            if (producer != null) {
                try {
                    producer.close();
                } catch (JMSException e) {
                    logger.warn("Error when closing producer", e);
                }
            }

            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    logger.warn("Error when closing queue session", e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.warn("Error when closing queue connection", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        sendJms("test message");
    }
}
