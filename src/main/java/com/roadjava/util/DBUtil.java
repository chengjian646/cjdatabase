package com.roadjava.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConn() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl",
                    "cjpro",
                    "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConn(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closePs(PreparedStatement ps){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeRs(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
