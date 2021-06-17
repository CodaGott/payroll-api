package com.payroll.web.controllers;

import com.payroll.data.dto.EmployeeDTO;
import com.payroll.data.model.Employee;
import com.payroll.exception.EmployeeNotFoundException;
import com.payroll.service.employee.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<?> findAllEmployee(){

        List<Employee> employees =
                employeeService.findAll()
                        .stream()
                        .map(employee -> employee.add(
                                linkTo(methodOn(EmployeeController.class)
                        .findAllEmployee()).withSelfRel())).collect(Collectors.toList());

        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Integer id){

        Employee employee = employeeService.findById(id);

        EntityModel<Employee> entityModel =
                EntityModel.of(employee,
                        linkTo(methodOn(
                                EmployeeController.class)
                                .findEmployeeById(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class)
                                .findAllEmployee()).withRel("employees"));

//        return new ResponseEntity<>(entityModel, HttpStatus.OK);
        return ResponseEntity.ok().body(entityModel);
    }

    @PostMapping("/employeeDTO")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.save(employeeDTO);
        EntityModel<Employee> entityModel =
                EntityModel.of(employee,
                        linkTo(methodOn(
                                EmployeeController.class)
                                .findEmployeeById(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class)
                                .findAllEmployee()).withRel("employees"));

        return ResponseEntity.created(
                entityModel.getRequiredLink(IanaLinkRelations.SELF).
                toUri()).body(entityModel);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id){
        employeeService.findById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/employeeDTO/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id){

        EntityModel<Employee> entityModel = null;
        Employee employee = null;

        try{
            employee = employeeService.Update(employeeDTO, id);
            entityModel = EntityModel.of(employee,
                    linkTo(methodOn(
                            EmployeeController.class)
                            .findEmployeeById(employee.getId())).withSelfRel(),
                    linkTo(methodOn(EmployeeController.class)
                            .findAllEmployee()).withRel("employees"));
        }catch (EmployeeNotFoundException ex){
            log.info("Error occurred --> {}", ex.getMessage());
            ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().body(entityModel);
    }
}
