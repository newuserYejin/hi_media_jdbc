package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.close;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        
        // 쿼리문 저장하고 보내는 Statement

        Statement stmt = null;

        // select 의 결과를 받을 수 있는 ResultSet
        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("select emp_id, emp_name from employee");

            while (rset.next()){
                // next() : select 결과 행이 존재한다면 true 반환 아니면 false 반환

                System.out.println(rset.getString("EMP_ID") + "번 "+rset.getString("EMP_NAME"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(stmt);
        }

    }
}
