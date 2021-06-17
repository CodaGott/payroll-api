package com.payroll.service.util;

import com.payroll.data.dto.EmployeeDTO;
import com.payroll.data.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class EmployeeMapperTest {

    EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    }
    @Test
    void givenEmployeeDtoSourceWhenMappedThenCorrectlyTest(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Mike");
        employeeDTO.setRole("Lawyer");

        Employee employee = new Employee();

        employeeMapper.updateEmployeeFromDto(employeeDTO, employee);

        assertThat(employee.getFirstName()).isEqualTo(employeeDTO.getFirstName());
        assertThat(employee.getLastName()).isEqualTo(employeeDTO.getLastName());
        assertThat(employee.getRole()).isEqualTo(employeeDTO.getRole());
    }

    @Test
    void givenNullValuesWhenMappedThenMapNotNullValues(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName(null);
        employeeDTO.setRole(null);

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Mike");
        employee.setRole("Lawyer");

        employeeMapper.updateEmployeeFromDto(employeeDTO, employee);

        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Mike");
        assertThat(employee.getRole()).isEqualTo("Lawyer");
    }

}