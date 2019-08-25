package com.kafkaDemo;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.lang.RuntimeException;;

public class IKafkaConstants {
    public static String KAFKA_BROKERS;
    public static Integer MESSAGE_COUNT;
    public static String CLIENT_ID;
    public static String TOPIC_NAME;
    public static String GROUP_ID_CONFIG;
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT;
    public static String OFFSET_RESET_LATEST;
    public static String OFFSET_RESET_EARLIER;
    public static Integer MAX_POLL_RECORDS;

    public static void loadCfg() {
        InputStream input;
        try {
            input = App.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            prop.load(input);
            
            KAFKA_BROKERS = prop.getProperty("KAFKA_BROKERS");
            MESSAGE_COUNT = Integer.parseInt(prop.getProperty("MESSAGE_COUNT"));
            CLIENT_ID = prop.getProperty("CLIENT_ID");
            TOPIC_NAME = prop.getProperty("TOPIC_NAME");
            GROUP_ID_CONFIG = prop.getProperty("GROUP_ID_CONFIG");
            MAX_NO_MESSAGE_FOUND_COUNT = Integer.parseInt(prop.getProperty("MAX_NO_MESSAGE_FOUND_COUNT"));
            OFFSET_RESET_LATEST = prop.getProperty("OFFSET_RESET_LATEST");
            OFFSET_RESET_EARLIER = prop.getProperty("OFFSET_RESET_EARLIER");
            MAX_POLL_RECORDS = Integer.parseInt(prop.getProperty("MAX_POLL_RECORDS"));
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
