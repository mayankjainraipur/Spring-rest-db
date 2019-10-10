package com.dboperation.interection.controller;



import com.dboperation.interection.model.Employee;
import com.dboperation.interection.model.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MainController {

    @RequestMapping(value=ApiConstant.ADD_URL,
            method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addEmployee(@RequestBody Employee emp)
    {
        Storage str = new Storage();
        boolean res = str.addEmployee(emp);
        if(res)
            return new ResponseEntity(HttpStatus.OK);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to Add Employee");
    }

    @RequestMapping(value=ApiConstant.ADD_FORM_URL,
            method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity addEmployee(
            @RequestParam(name="id",required = true) int id,
            @RequestParam(name="name",required = true) String name,
            @RequestParam(name="salary",required = true) double salary,
            @RequestParam(name="company",required = true) String company,
            @RequestParam(name="dob",required = true) String dob) throws ParseException {
        Storage str = new Storage();
        Employee emp = new Employee();
        emp.setName(name);
        emp.setCompany(company);
        emp.setSalary(salary);
        SimpleDateFormat fs = new SimpleDateFormat("dd-MM-yyyy");
        Date date = fs.parse(dob);
        emp.setDob(date);
        boolean res = str.addEmployee(emp);
        if(res)
            return new ResponseEntity(HttpStatus.OK);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to Add Employee");
    }

    @RequestMapping(value=ApiConstant.GET_BY_ID,
            method=RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id")int id)
    {
        Storage str = new Storage();
        Employee emp = str.getEmployee(id);
        return emp;
    }

    @RequestMapping(value=ApiConstant.GET_ALL_URL,
            method=RequestMethod.GET)
    public List<Employee> getAll()
    {
        Storage str = new Storage();
        return str.listAll();
    }

    @RequestMapping(value=ApiConstant.DELETE_URL,
            method=RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id")int id)
    {
        Storage str = new Storage();
        boolean res = str.deleteEmployee(id);
        if(res)
            return new ResponseEntity(HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

}
