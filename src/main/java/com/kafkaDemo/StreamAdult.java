package com.kafkaDemo;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;

public class StreamAdult {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Person> flintstones = env.fromElements(
            new Person("Test1", 1), new Person("Test2", 2),
            new Person("Test3", 30), new Person("Test4", 20)
        );
        DataStream<Person> adult = flintstones.filter(person -> person.age >= 18);
        adult.print();
        env.execute();
    }

    public static class Person {
        public String name;
        public Integer age;
        public Person() {};

        public Person(String name, Integer age) {
            this.name = name;
            this.age= age;
        }
        public String toString() {
            return this.name.toString() + "age: " + this.age.toString();
        }
    } 
}