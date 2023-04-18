package com.aoeivux.util;

import java.sql.*;

public class DataTools {
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    private static ResultSet resultSet;
    private static String url = "jdbc:mysql://localhost:3306/zhxy_db?useUnicode=true&characterEncoding=UTF-8";
    private static String name = "root";
    private static String password = "Atheonealone37.";


    // 运行一次
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if( connection != null) {
                connection.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void release(Connection connection, PreparedStatement preparedStatement) {
        try {
            if( connection != null) {
                connection.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(DataTools.getConnection());
    }
}
