package com.dboperation.interection.dao;

import com.dboperation.interection.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee emp = new Employee();
        emp.setId(resultSet.getInt("id"));
        emp.setName(resultSet.getString("name"));
        emp.setCompany(resultSet.getString("company"));
        emp.setSalary(resultSet.getDouble("salary"));
        emp.setDob(resultSet.getDate("dob"));
        return emp;
    }
}
