package com.dboperation.interection.dao;

import com.dboperation.interection.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class EmployeeDaoImp {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveEmployee(Employee e){
        int id = e.getId();
        String name = e.getName();
        String company = e.getCompany();
        double salary = e.getSalary();
        Date dob = e.getDob();
        String query = "INSERT INTO employee(id, name, company, salary, dob) values (?, ?, ?, ?, ?)";
        String.format(query,id, name, company, salary, dob);
        return jdbcTemplate.update(query);
    }

    public int updateEmployee(Employee e){
        String query="UPDATE employee set"+
                " name='"+e.getName()+
                "',salary='"+e.getSalary()+
                " company='"+e.getCompany()+
                " dob='"+e.getDob()+
                "' where id='"+e.getId()+"' ";
        return jdbcTemplate.update(query);
    }

    public int deleteEmployee(Employee e){
        String query="DELETE from employee where id='"+e.getId()+"' ";
        return jdbcTemplate.update(query);
    }
}
