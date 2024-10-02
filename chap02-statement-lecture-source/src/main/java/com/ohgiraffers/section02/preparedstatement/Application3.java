package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // 1명의 회원정보 관리할 EmployeeDTO 사용
        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사번 입력: ");
        String emp_id = sc.nextLine();

        String query = "select * from employee where emp_id=?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,emp_id);

            rset = pstmt.executeQuery();

            if (rset.next()){
                emp = new EmployeeDTO();

                emp.setEmpId(rset.getString("emp_id"));
                emp.setEmpName(rset.getString("EMP_NAME"));
                emp.setEmpNo(rset.getString("EMP_NO"));
                emp.setEmail(rset.getString("EMAIL"));
                emp.setPhone(rset.getString("PHONE"));
                emp.setJobCode(rset.getString("SAL_LEVEL"));
                emp.setSalary(rset.getInt("SALARY"));
                emp.setBonus(rset.getDouble("BONUS"));
                emp.setManagerId(rset.getString("manager_ID"));
                emp.setHireDate(rset.getDate("HIRE_DATE"));
                emp.setEntDate(rset.getDate("ENT_DATE"));
                emp.setEntYn(rset.getString("ENT_YN"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(con);
            close(pstmt);
        }

        System.out.println("emp = " + emp);
    }
}
