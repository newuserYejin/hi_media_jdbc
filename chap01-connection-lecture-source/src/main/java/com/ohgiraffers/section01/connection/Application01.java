package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {
    public static void main(String[] args) {

        // 연결
        Connection con = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결위치, user이름, 비밀번호
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "ohgiraffers","ohgiraffers");
            System.out.println("con = " + con);
            
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
