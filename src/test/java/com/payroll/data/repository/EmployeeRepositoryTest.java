package com.payroll.data.repository;

import com.payroll.data.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = "classpath:db/insert.sql")
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void updateEmployeeRecordTest(){
//        (99, 'Bob', 'Dan', 'HR'),
        Employee employee = employeeRepository.findById(99).orElse(null);
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Bob");

        log.info("Employee before save --> {}", employee);

        employee.setFirstName("Emma");

        employeeRepository.save(employee);
        assertThat(employee.getFirstName()).isEqualTo("Emma");
        assertThat(employee.getLastName()).isEqualTo("Dan");
        assertThat(employee.getRole()).isEqualTo("HR");
        log.info("Employee after save --> {}", employee);
    }
}