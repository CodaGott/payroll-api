package com.payroll.service;

import com.payroll.data.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);
    Employee findById(Integer id);
    List<Employee> findAll();
    void deleteById(Integer id);
}
