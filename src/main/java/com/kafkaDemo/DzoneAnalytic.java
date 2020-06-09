//https://dzone.com/articles/introduction-to-streaming-etl-with-apache-flink
package com.kafkaDemo;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.pd.streaming.aggregation.simple.IntegerGeneratorSource;


public class DzoneAnalytic {
    public static void main(String[] args) {
        StreamExecutionEnvironment senv =  StreamExecutionEnvironment.getExecutionEnvironment();
        IntegerGeneratorSource source = new IntegerGeneratorSource();
        DataStream<Integer> dataStream = senv.addSource(source);


    }
}