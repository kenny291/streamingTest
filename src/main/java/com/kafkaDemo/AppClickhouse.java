package com.kafkaDemo;

import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.ClickHouseConnectionImpl;
import ru.yandex.clickhouse.BalancedClickhouseDataSource;


import java.util.concurrent.TimeUnit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppClickhouse {
    public static void main(String[] args) throws Exception {
        BalancedClickhouseDataSource ds = new BalancedClickhouseDataSource(
            "jdbc:clickhouse://10.10.14.159:8123,10.10.14.151:8123");
        ds.scheduleActualization(100, TimeUnit.MICROSECONDS);
        ClickHouseConnectionImpl connection = (ClickHouseConnectionImpl) ds.getConnection();
        System.out.println("ok");
        PreparedStatement statement = connection.prepareStatement("SHOW DATABASES;");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getString(1));

    }
}
