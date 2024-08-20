package com.example.demo.service;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeDto;
import com.example.demo.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        log.info("Employee with id: {} not found", id);
        throw new EntityNotFoundException("Employee not found");
    }

    public Employee saveEmployee (EmployeeDto employeeDto){
        employeeDto.setCreatedAt(LocalDateTime.now());
        employeeDto.setUpdatedAt(LocalDateTime.now());

        Employee employee = employeeMapper.mapDtoToEntity(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee with id: {} saved successfully", savedEmployee.getId());
        return savedEmployee;
    }

    public Employee updateEmployee (EmployeeDto employeeDto) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeDto.getId());

        if(existingEmployee.isPresent()){
            employeeDto.setCreatedAt(existingEmployee.get().getCreatedAt());
            employeeDto.setUpdatedAt(LocalDateTime.now());
        }

        Employee employee = employeeMapper.mapDtoToEntity(employeeDto);

        Employee updatedEmployee = employeeRepository.save(employee);

        log.info("Employee with id: {} updated successfully", updatedEmployee.getId());
        return updatedEmployee;
    }

    public void deleteEmployeeById (long id) {
        employeeRepository.deleteById(id);
    }
}
