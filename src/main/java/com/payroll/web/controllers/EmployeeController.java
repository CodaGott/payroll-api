package com.payroll.web.controllers;

import com.payroll.data.model.Employee;
import com.payroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> findAllEmployee(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee findEmployeeById(@PathVariable Integer id){
        return employeeService.findById(id);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        employeeService.deleteById(id);
    }
}
