package com.kafkaDemo;

import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

// prducer
// public class App {
   
//    public static void main(String[] args) throws Exception{
      
//       // Check arguments length value
//       if(args.length == 0){
//          System.out.println("Enter topic name");
//          return;
//       }
      
//       //Assign topicName to string variable
//       String topicName = args[0].toString();
      
//       // create instance for properties to access producer configs   
//       Properties props = new Properties();
      
//       //Assign localhost id
//       props.put("bootstrap.servers", "10.10.14.102:9092/kafka-cluster");
      
//       //Set acknowledgements for producer requests.      
//       props.put("acks", "all");
      
//       //If the request fails, the producer can automatically retry,
//       props.put("retries", 0);
      
//       //Specify buffer size in config
//       props.put("batch.size", 16384);
      
//       //Reduce the no of requests less than 0   
//       props.put("linger.ms", 1);
      
//       //The buffer.memory controls the total amount of memory 
//       // available to the producer for buffering.   
//       props.put("buffer.memory", 33554432);
      
//       props.put("key.serializer", 
//          "org.apache.kafka.common.serialization.StringSerializer");
         
//       props.put("value.serializer", 
//          "org.apache.kafka.common.serialization.StringSerializer");
      
//         Thread.currentThread().setContextClassLoader(null);
//         // Producer<String, String> producer = new KafkaProducer(props);
//         Producer<String, String> producer = new KafkaProducer
//         <String, String>(props);
            
//       for(int i = 0; i < 10; i++) {
//          producer.send(new ProducerRecord<String, String>(topicName, 
//             Integer.toString(i), Integer.toString(i)));
//          System.out.println(i);
//       }
//       System.out.println("Message sent successfully");
//       producer.close();
//    }
// }

import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class App1 {
   public static void main(String[] args) throws Exception {
      if(args.length == 0){
         System.out.println("Enter topic name");
         return;
      }
      //Kafka consumer configuration settings
      String topicName = args[0].toString();
      Properties props = new Properties();
      
      props.put("bootstrap.servers", "10.10.14.102:9092");
      props.put("group.id", "test");
      props.put("enable.auto.commit", "true");
      props.put("auto.commit.interval.ms", "1000");
      props.put("session.timeout.ms", "30000");
      props.put("key.deserializer", 
         "org.apache.kafka.common.serialization.StringDeserializer");
      props.put("value.deserializer", 
         "org.apache.kafka.common.serialization.StringDeserializer");
      KafkaConsumer<String, String> consumer = new KafkaConsumer
         <String, String>(props);
      
      //Kafka Consumer subscribes list of topics here.
      consumer.subscribe(Arrays.asList(topicName));
      
      //print the topic name
      System.out.println("Subscribed to topic " + topicName);
      int i = 0;
      
      while (true) {
         ConsumerRecords<String, String> records = consumer.poll(100);
         for (ConsumerRecord<String, String> record : records)
         
         // print the offset,key and value for the consumer records.
         System.out.printf("offset = %d, key = %s, value = %s\n", 
            record.offset(), record.key(), record.value());
      }
   }
}