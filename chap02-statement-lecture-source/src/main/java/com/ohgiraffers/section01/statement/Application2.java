package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME, DEPT_CODE FROM EMPLOYEE");
            while (rset.next()){
                System.out.println("사번: "+rset.getString("EMP_ID") + " 이름: " + rset.getString("EMP_NAME") + " 부서코드: " + rset.getString("DEPT_CODE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }

        Connection con2 = getConnection();

        Statement stmt2 = null;
        ResultSet rset2 = null;

        // Scanner
        try {
            stmt2 = con2.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.print("\n조회하실 사번을 입력하세요: ");
            String empId = sc.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            rset2 = stmt2.executeQuery(query);
            while (rset2.next()){
                System.out.println("사번: "+rset2.getString("EMP_ID") + "번의 이름은 " + rset2.getString("EMP_NAME") + " 입니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con2);
            close(stmt2);
            close(rset2);
        }
    }
}
