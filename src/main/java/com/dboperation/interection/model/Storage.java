package com.dboperation.interection.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Storage {

    HashMap<Integer, Employee> map = null;

    public boolean addEmployee(Employee emp) {
        if(emp!=null && this.checkData(emp)!=false)
        {
            map=readFile();
            if (map == null){
                map = new HashMap<Integer, Employee>();
            }
            map.put(emp.getId(),emp);
            writeFile(map);
            return true;
        }
        return false;
    }

    private boolean checkData(Employee emp) {
        return emp.verify();
    }

    public Employee getEmployee(int id) {
        map=readFile();
        if (map != null){
            Employee emp = map.get(id);
            return emp;
        }
        return new Employee();
    }

    public boolean updateEmployee(Employee emp) {
        map=readFile();
        if (map != null){
            Employee emp1 = map.get(emp.getId());
            if (emp1.getName() != ""){
                emp1.setName(emp.getName());
                emp1.setCompany(emp.getCompany());
                emp1.setDob(emp.getDob());
                emp1.setSalary(emp.getSalary());
                writeFile(map);
                return true;
            }
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        map=readFile();
        if (map !=null){
            map.remove(id);
            writeFile(map);
            return true;
        }
        return false;
    }

    public List<Employee> listAll() {
        List<Employee>emp = new ArrayList<>();
        map=readFile();
        if (map!=null){
            Set<Integer> id = map.keySet();
            for(Integer i:id)
            {
                emp.add(map.get(i));
            }
        }
        return emp;
    }

    private HashMap<Integer,Employee> readFile(){
        try{
            File toRead=new File("output");
            FileInputStream fis=new FileInputStream(toRead);
            ObjectInputStream ois=new ObjectInputStream(fis);
            map= (HashMap<Integer,Employee>)ois.readObject();
            ois.close();
            fis.close();
        }catch(Exception e){
            System.out.println("error");
        }
        return map;
    }

    private void writeFile(HashMap<Integer,Employee> map){
        try{
            File fileOne=new File("output");
            FileOutputStream fos=new FileOutputStream(fileOne);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        }catch(Exception e){
            System.out.println("error");
        }
    }
}
