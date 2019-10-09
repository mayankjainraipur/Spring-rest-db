package com.dboperation.interection.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {

    private String name;     // first name
    private String email;     // email address
    private int section;      // section number
    private Date dob = null;

    //default constructor
    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getComparingValue() {
        return name;
    }

    @Override
    public String toString() {
        SimpleDateFormat fs = new SimpleDateFormat ("dd-MM-yyyy");
        return "Student{" +
                "first='" + name + '\'' +
                ", email='" + email + '\'' +
                ", section=" + section +
                ", dob=" + fs.format(dob) +
                '}';
    }
}
