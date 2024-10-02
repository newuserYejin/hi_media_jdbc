package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        /*PreparedStatement는 최초에 한 번 실행했을 시
        * SQL뭄을 파싱하고 컴파일 하지만
        * 동일한 SQL 구문을 여러번 실행하게 되면
        * 최초에 컴파일한 SQL을 재사용하게 된다.
        * 따라서 성능이 향상된다.
        *
        * ? <- placeholder
        * - ? 의 갯수, 시작값(1)
        *  */

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사번: ");
        String emp_id = sc.nextLine();

        String query = "select emp_id,emp_name from employee WHERE emp_id = ?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,emp_id);

            rset = pstmt.executeQuery();

            while (rset.next()){
                System.out.println(rset.getString("emp_id") + rset.getString("emp_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(con);
            close(pstmt);
        }
    }
}
