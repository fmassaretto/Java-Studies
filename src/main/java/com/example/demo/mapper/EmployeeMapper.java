package com.example.demo.mapper;

import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    List<EmployeeDto> mapListEntityToListDto(List<Employee> employees);
    EmployeeDto mapEntityToDto(Employee employee);
    Employee mapDtoToEntity(EmployeeDto employeeDto);
}
