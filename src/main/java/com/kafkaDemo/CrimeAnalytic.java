package com.kafkaDemo;

// https://data-flair.training/blogs/apache-flink-use-case/
// data: header: cdatetime,address,district,beat,grid,crimedescr,ucr_ncic_code,latitude,longitude

// problem 1: group count crime type
// import org.apache.flink.api.common.functions.GroupReduceFunction;
// import org.apache.flink.api.java.DataSet;
// import org.apache.flink.api.java.ExecutionEnvironment;
// import org.apache.flink.api.java.tuple.Tuple2;
// import org.apache.flink.api.java.tuple.Tuple3;
// import org.apache.flink.util.Collector;

// public class CrimeAnalytic {
//     public static void main(String[] args) throws Exception {
//         ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//         DataSet<Tuple2<String, String>> rawData = env.readCsvFile("/home/khiemtd/crime.csv").includeFields("0000011")
//                 .ignoreFirstLine().types(String.class, String.class);
//         rawData.groupBy(0, 1).reduceGroup(new CrimeCounter())
//             .writeAsText("/home/khiemtd/abcd");
//         env.execute("test crime data1");
//     }
//     public static class CrimeCounter
//             implements GroupReduceFunction<Tuple2<String, String>, Tuple3<String, String, Integer>> {

//         @Override
//         public void reduce(
//             Iterable<Tuple2<String, String>> records, Collector<Tuple3<String, String, Integer>> out) throws Exception {
//             String crimerecord = null;
//             String ucr_code = null;
//             int cnt = 0;
//             for(Tuple2<String, String> m: records){
//                 crimerecord = m.f0;
//                 ucr_code = m.f1;
//                 cnt++;
//                 }
//             out.collect(new Tuple3<>(crimerecord, ucr_code, cnt));
//         }
//     }
// }


// problem 2: group count by month
// import java.text.SimpleDateFormat;
// import java.util.Calendar;
// import java.util.Date;
// import org.apache.flink.api.common.functions.MapFunction;
// import org.apache.flink.api.common.operators.Order;
// import org.apache.flink.api.java.DataSet;
// import org.apache.flink.api.java.ExecutionEnvironment;
// import org.apache.flink.api.java.tuple.Tuple1;
// import org.apache.flink.api.java.tuple.Tuple3;

// public class CrimeAnalytic {
//     public static void main(String[] args) throws Exception{
//         ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//         DataSet<Tuple1<String>> rawData = env.readCsvFile("/home/khiemtd/crime.csv")
//             .includeFields("100000").ignoreFirstLine().types(String.class);
//         rawData.map(new DateExtractor())
//             .groupBy(0)
//             .sum(2)
//             .groupBy(1)
//             .sortGroup(2, Order.DESCENDING)
//             .first(50)
//             .writeAsCsv("/home/khiemtd/groupday.csv");
//         env.execute();
//     }
//     public static class DateExtractor implements MapFunction
//         <Tuple1<String>, Tuple3<String, String, Integer>> {
//             SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//             SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");

//             @Override
//             public Tuple3<String, String, Integer> map(Tuple1<String> time) throws Exception {
//                 Date date = formatter2.parse(time.f0);
//                 Calendar cal = Calendar.getInstance();
//                 cal.setTime(date);
//                 int month = cal.get(Calendar.MONTH) + 1;
//                 int year = cal.get(Calendar.YEAR);

//                 return new Tuple3<>(formatter.format(date), month + "/" + year, 1);
//             }

//         }
// }



// https://data-flair.training/blogs/flink-real-world-use-case/
// problem 1:  find out the Hour of the day when a maximum crime occurs
// (Highest Crime Hour Analysis) for each day.

// import java.text.SimpleDateFormat;
// import java.util.Date;
// import org.apache.flink.api.java.DataSet;
// import org.apache.flink.api.java.ExecutionEnvironment;
// import org.apache.flink.api.common.functions.MapFunction;
// import org.apache.flink.api.java.tuple.Tuple1;
// import org.apache.flink.api.java.tuple.Tuple3;


// public class CrimeAnalytic {
//     public static void main(String[] args) throws Exception {
//         ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//         DataSet<Tuple1<String>> rawData = env.readCsvFile("/home/khiemtd/crime.csv")
//             .includeFields("100000").ignoreFirstLine().types(String.class);
//         rawData.map(new ExtractTime()) // map: hour - day - 1
//             .groupBy(0, 1)
//             .sum(2)
//             .groupBy(1)
//             .maxBy(1, 2)
//             .writeAsCsv("/home/khiemtd/group_hour.csv");
//         env.execute();
//     }
//     public static class ExtractTime implements MapFunction<Tuple1<String>, Tuple3<String, String, Integer>> {
//         SimpleDateFormat fmtRaw = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//         SimpleDateFormat fmtHour = new SimpleDateFormat("MM/dd/yyyy HH");
//         SimpleDateFormat fmtDate = new SimpleDateFormat("MM/dd/yyyy");

//         @Override
//         public Tuple3<String, String, Integer> map(Tuple1<String> in) throws Exception {
//             Date date = fmtRaw.parse(in.f0);
//             return new Tuple3<> (fmtHour.format(date), fmtDate.format(date), 1);
//         }
//     }
// }


// // problem 2: find Safest District
// import org.apache.flink.api.common.functions.FlatMapFunction;
// import org.apache.flink.api.java.DataSet;
// import org.apache.flink.api.java.ExecutionEnvironment;
// import org.apache.flink.api.java.tuple.Tuple2;
// import org.apache.flink.util.Collector;

// public class CrimeAnalytic {
//     public static void main(String[] args) throws Exception {
//         ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//         DataSet<String> rawdata = env.readTextFile("/home/khiemtd/crime.csv");
//         DataSet<Tuple2<String, Integer>> result = rawdata.flatMap(new Counter())// map the data and as district,1
//                 .groupBy(0) // group the data according to district
//                 .sum(1) // to count no. of crimes in a district
//                 .minBy(1); // to find out the minimum crime
//         result.writeAsCsv("/home/khiemtd/group_hour.csv");
//     }

//     public static class Counter implements FlatMapFunction<String, Tuple2<String, Integer>> {
//         @Override
//         public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
//             String[] tokens = value.split(",");
//             if (tokens[2].contains("district")) {
//                 return;
//             } else {
//                 out.collect(new Tuple2<String, Integer>(tokens[2], 1));
//             }
//         }
//     }
// }