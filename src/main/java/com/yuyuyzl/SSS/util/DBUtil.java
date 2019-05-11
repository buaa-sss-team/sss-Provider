package com.yuyuyzl.SSS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String URL="jdbc:mysql://localhost:3306/rg_trial?useSSL=false";
    public static final String USER="root";
    public static final String PASSWORD="wangjiayi";
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