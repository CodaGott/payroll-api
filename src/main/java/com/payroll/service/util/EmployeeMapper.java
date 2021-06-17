package com.payroll.service.util;

import com.payroll.data.dto.EmployeeDTO;
import com.payroll.data.model.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromDto(EmployeeDTO employeeDTO,
                               @MappingTarget Employee employee);

}
