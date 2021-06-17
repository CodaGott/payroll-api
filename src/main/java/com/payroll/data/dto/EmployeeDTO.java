package com.payroll.data.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeDTO {

    @NotNull(message = "first name can't be empty")
    private String firstName;
    @NotNull(message = "last name can't be empty")
    private String lastName;
    @NotNull(message = "role can't be empty")
    private String role;

}
