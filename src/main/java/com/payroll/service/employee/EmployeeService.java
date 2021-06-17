package com.payroll.service.employee;

import com.payroll.data.dto.EmployeeDTO;
import com.payroll.data.model.Employee;
import com.payroll.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    Employee save(EmployeeDTO employeeDto);
    Employee findById(Integer id);
    List<Employee> findAll();
    void deleteById(Integer id);
    Employee Update(EmployeeDTO employeeDto, Integer id) throws EmployeeNotFoundException;
}
