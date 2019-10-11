package com.dboperation.interection.controller;


import com.dboperation.interection.dao.EmpDAO;
import com.dboperation.interection.model.Employee;
import com.dboperation.interection.model.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @RequestMapping(value = ApiConstant.ADD_URL,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addEmployee(@RequestBody Employee emp) {
        EmpDAO empDAO = new EmpDAO();
        empDAO.setName(emp.getName());
        empDAO.setCompany(emp.getCompany());
        empDAO.setSalary(emp.getSalary());
        empDAO.setId(emp.getId());
        java.sql.Date sqlDate = new java.sql.Date(emp.getDob().getTime());
        empDAO.setDob(sqlDate);
        EmpDAO save = employeeRepo.save(empDAO);
        if (null != save) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to Add Employee");
        }
    }
/*
    expected date format is yyyy-MM-dd
 */
    @RequestMapping(value = ApiConstant.ADD_FORM_URL,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity addEmployee(
            @RequestParam(name = "id", required = true) int id,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "salary", required = true) double salary,
            @RequestParam(name = "company", required = true) String company,
            @RequestParam(name = "dob", required = true) String dob) throws ParseException {
        EmpDAO emp = new EmpDAO();
        emp.setName(name);
        emp.setCompany(company);
        emp.setDob(java.sql.Date.valueOf(dob));
        emp.setId(id);
        emp.setSalary(salary);
        EmpDAO save = employeeRepo.save(emp);
        if (null != save)
            return new ResponseEntity(HttpStatus.OK);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to Add Employee");
    }

    @RequestMapping(value = ApiConstant.GET_BY_ID,
            method = RequestMethod.GET)
    public EmpDAO getEmployee(@PathVariable("id") int id) {
        Optional<EmpDAO> byId = employeeRepo.findById(id);
        EmpDAO empDAO = byId.get();
        return empDAO;
    }

    @RequestMapping(value = ApiConstant.GET_ALL_URL,
            method = RequestMethod.GET)
    public List<EmpDAO> getAll() {
        List<EmpDAO> all = employeeRepo.findAll();
        return  all;
    }

    @RequestMapping(value = ApiConstant.DELETE_URL,
            method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") int id) {
        Storage str = new Storage();
        boolean res = str.deleteEmployee(id);
        if (res)
            return new ResponseEntity(HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

}
