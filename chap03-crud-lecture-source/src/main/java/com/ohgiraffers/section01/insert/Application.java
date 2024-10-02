package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertMenu");
            System.out.println("query = " + query);

            Scanner sc = new Scanner(System.in);

            System.out.print("신 메뉴의 이름: ");
            String menuName = sc.nextLine();
            System.out.print("신 메뉴의 가격: ");
            int menuPrice = sc.nextInt();
            System.out.print("신 메뉴의 카테고리 코드: ");
            int categoryCode = sc.nextInt();
            System.out.print("판매여부 입력(Y/N): ");
            sc.nextLine();
            String orderableStatus = sc.nextLine().toUpperCase();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuName);
            pstmt.setInt(2,menuPrice);
            pstmt.setInt(3,categoryCode);
            pstmt.setString(4,orderableStatus);

            // 변경의 횟수만큼 정수를 반환하여 result 에 저장
            result = pstmt.executeUpdate();
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        System.out.println("result = " + result);
        
        if (result > 0){
            System.out.println("메뉴등록 성공");
        } else{
            System.out.println("알 수 없는 이유로 메뉴등록 실패");
        }

    }
}
