package com.dboperation.interection.dao;

import com.dboperation.interection.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PropertySource("classpath:application.properties")
public class SampleTest {

    @Value("${sql.database.userName}")
    private String userName;

    @Value("${sql.database.password}")
    private String password;

    Connection con;
    Statement stmt;

    public SampleTest() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test",userName,password);
        stmt=con.createStatement();
    }

    public boolean create(Employee e) throws SQLException {
        try {
            String SQL = "insert into Student (id, name, company, salary, dob) values (?, ?, ?, ?, ?)";
            int id = e.getId();
            String name = e.getName();
            String company = e.getCompany();
            double salary = e.getSalary();
            Date dob = e.getDob();
            String.format(SQL,id, name, company, salary, dob);
            stmt.executeUpdate(SQL);
        } catch (Exception ex){
            return false;
        }
        return true;
    }
    public List<Employee> listEmployee() throws SQLException {
        String SQL = "select * from Student";
        ResultSet rs=stmt.executeQuery(SQL);
        List <Employee> e = new ArrayList<Employee>();
        EmployeeMapper map = new EmployeeMapper();
        int i =1;
        while (rs.next()){
            e.add(map.mapRow(rs,i));
            i++;
        }
        return e;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        con.close();
        stmt.close();
    }
}
