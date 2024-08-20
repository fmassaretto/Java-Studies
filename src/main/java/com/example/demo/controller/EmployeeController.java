package com.example.demo.controller;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/v1/")
@RequiredArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeMapper.mapListEntityToListDto(employeeService.getAllEmployees());
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
        EmployeeDto employee = employeeMapper.mapEntityToDto(employeeService.getEmployeeById(id));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeMapper.mapEntityToDto(employeeService.saveEmployee(employeeDto));
        return ResponseEntity.ok().body(employeeDto1);
    }

    @PutMapping("/")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = employeeMapper.mapEntityToDto(employeeService.updateEmployee(employeeDto));
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().body("Deleted employee successfully");
    }
}
