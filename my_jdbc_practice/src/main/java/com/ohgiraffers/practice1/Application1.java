package com.ohgiraffers.practice1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    static Connection con = getConnection();
    static PreparedStatement pstmt = null;
    static ResultSet rset = null;

    static Properties prop = new Properties();

    public static void main(String[] args) {

        Application1 app = new Application1();

//        app.number1();
//        app.number2();
//        app.number3();
        app.number4();

    }

    private void number1(){
        String query = "";

        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml")
            );
            query = prop.getProperty("allSelect");

            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }
    }

    private void number2(){

        String query = "";

        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml")
            );

            query = prop.getProperty("number2");

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            while (rset.next()){
                System.out.println("사번: "+rset.getString("emp_id")+" 이름: " + rset.getString("emp_name"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }


    }

    private void number3(){
        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml")
            );

            String query = prop.getProperty("number3");
            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            while (rset.next()){
                System.out.println("부서코드: " + rset.getString("dept_code") +
                                    ", 이름: " + rset.getString("emp_name") +
                                    ", 급여: " + rset.getString("salary"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }

    private void number4(){
        String query = "";

        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml")
            );

            query = prop.getProperty("number4");

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            while (rset.next()){
                System.out.println("이름: " + rset.getString("emp_name") + ", 급여: " + rset.getString("salary") + ", 보너스 포함: " + Math.round(rset.getDouble("bonusSalary")));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(con);
            close(pstmt);
        }

    }


}
