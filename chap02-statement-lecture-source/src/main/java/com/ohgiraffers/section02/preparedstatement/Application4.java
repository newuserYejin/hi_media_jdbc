package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 성씨를 입력하세요: ");
        String sung = sc.nextLine();

        String query = "select * from employee where emp_name like concat(?,'%')";
//        String query = "select * from employee where emp_name like ?";

        List<EmployeeDTO> elist = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(query);
//            pstmt.setString(1,sung + "%");
            pstmt.setString(1,sung);

            rset = pstmt.executeQuery();


            while (rset.next()){
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

                elist.add(emp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(con);
            close(pstmt);
        }

        for(EmployeeDTO e: elist){
            System.out.println("e = " + e);
        }


    }
}
