package com.sss.provider.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    //public static final String URL="jdbc:mysql://localhost:3306/rg_trial?useSSL=false";;
    //public static final String USER="root";
    //public static final String PASSWORD="wangjiayi";

    public static final String URL="jdbc:mysql://132.232.169.70/sss-team?useSSL=false" + "?autoReconnect=true&useUnicode=true&useSSL=false"
            + "&characterEncoding=utf-8&serverTimezone=UTC";
    public static final String USER="root";
    public static final String PASSWORD="8280508";
    private static Connection conn=null;
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}