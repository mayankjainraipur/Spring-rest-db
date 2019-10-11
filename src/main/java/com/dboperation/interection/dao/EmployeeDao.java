package com.dboperation.interection.dao;

import com.dboperation.interection.model.Employee;

import javax.sql.DataSource;
import java.sql.*;
import java.math.*;
import java.util.List;

public interface EmployeeDao {

    public void setDataSource(DataSource ds);
    public void create(Employee e);
    public Employee getEmployee(Integer id);
    public List<Employee> listEmployee();

}
