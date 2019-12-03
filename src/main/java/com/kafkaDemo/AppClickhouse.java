// package com.kafkaDemo;

// import java.util.Arrays;
// import org.apache.kafka.clients.consumer.KafkaConsumer;
// import org.apache.kafka.clients.consumer.ConsumerRecords;
// import org.apache.kafka.clients.consumer.ConsumerRecord;
// import ru.yandex.clickhouse.ClickHouseStatement;

// public class AppClickhouse {
//    public static void main(String[] args) throws Exception {
// ClickHouseStatement sth = connection.createStatement();
// sth.write().send("INSERT INTO test.writer", new ClickHouseStreamCallback() {
//     @Override
//     public void writeTo(ClickHouseRowBinaryStream stream) throws IOException {
//         for (int i = 0; i < 10; i++) {
//             stream.writeInt32(i);
//             stream.writeString("Name " + i);
//         }
//     }
// },
// ClickHouseFormat.RowBinary);
//       }
//    }