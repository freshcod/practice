package com.coral.practice.wheels.achieve;

import java.sql.*;

/**
 * Created by qiuhai on 2016/6/21.
 */
public class JDBCAchieve {

    public static void main(String[] args){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306","test","123456");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select current_timestamp()");
            while(rs.next()){
                System.out.println(rs.getTime(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
