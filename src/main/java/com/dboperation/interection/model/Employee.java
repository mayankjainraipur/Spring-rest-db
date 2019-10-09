package com.dboperation.interection.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee implements Serializable {

    private String name, company;
    private double salary;
    private int id;
    private Date dob;

    public Employee(){}

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean verify(){
        return true;
    }
    @Override
    public String toString() {
        SimpleDateFormat fs = new SimpleDateFormat ("dd-MM-yyyy");
        return "Employee{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", id=" + id +
                ", dob=" + fs.format(dob) +
                '}';
    }
}
