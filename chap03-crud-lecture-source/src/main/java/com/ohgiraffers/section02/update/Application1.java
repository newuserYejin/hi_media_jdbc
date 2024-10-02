package com.ohgiraffers.section02.update;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴의 번호를 골라주세요: ");
        int menuNum = sc.nextInt();
        System.out.print("변경하실 이름을 입력해주세요: ");
        sc.nextLine();
        String updateName = sc.nextLine();
        System.out.print("변경되는 가격을 입력해주세요: ");
        int updatePrice = sc.nextInt();

        MenuDTO updateMenu = new MenuDTO();
        updateMenu.setMenuCode(menuNum);
        updateMenu.setMenmuName(updateName);
        updateMenu.setMenuPricce(updatePrice);

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml")
            );

            String query = prop.getProperty("updateMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,updateMenu.getMenmuName());
            pstmt.setInt(2,updateMenu.getMenuPricce());
            pstmt.setInt(3,updateMenu.getMenuCode());

            result = pstmt.executeUpdate();
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
        
        if (result > 0){
            System.out.println(updateMenu.getMenuCode() + "번 메뉴 정보 변경 성공");
        } else {
            System.out.println("메뉴정보 변경 실패");
        }

    }
}
