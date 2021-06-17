package com.payroll.service.employee;

import com.payroll.data.dto.EmployeeDTO;
import com.payroll.data.model.Employee;
import com.payroll.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Sql(scripts = {"classpath:db/insert.sql"})
class EmployeeServiceImplTest {

    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenUpdateEmployeeIsCalledThenUpdateNotNullValues() throws EmployeeNotFoundException{
//        values(99, 'Bob', 'Dan', 'HR'),
        Employee existingEmployee = employeeService.findById(99);
        assertThat(existingEmployee.getFirstName()).isEqualTo("Bob");
        assertThat(existingEmployee.getLastName()).isEqualTo("Dan");
        assertThat(existingEmployee.getRole()).isEqualTo("HR");

        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setRole("Banker");

        Employee updatedEmployee = employeeService.Update(employeeDto, 99);

        assertThat(updatedEmployee.getFirstName()).isEqualTo("Bob");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Dan");
        assertThat(updatedEmployee.getRole()).isEqualTo("Banker");
    }
}