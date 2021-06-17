package com.payroll.service.employee;

import com.payroll.data.dto.EmployeeDTO;
import com.payroll.data.model.Employee;
import com.payroll.data.repository.EmployeeRepository;
import com.payroll.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Employee save(EmployeeDTO employeeDto) {

        Employee employee = new Employee();
        modelMapper.map(employeeDto, employee);
        log.info("Employee after mapping --> {}", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee Update(EmployeeDTO employeeDto, Integer id) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null){
            throw new EmployeeNotFoundException("Employee not found");
        }

        return employeeRepository.save(employee);
    }
}
