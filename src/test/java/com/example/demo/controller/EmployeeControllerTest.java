package com.example.demo.controller;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class EmployeeControllerTest {

    public static final long ID = 123L;
    public static final String FIRST_NAME = "First";
    public static final String LAST_NAME = "Last";
    public static final int AGE = 19;
    public static final String DESIGNATION = "Administration";
    public static final String PHONE_NUMBER = "119123451234";
    public static final LocalDate JOINED_ON = LocalDate.now();
    public static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    public static final String ADDRESS = "Rua 123";
    public static final LocalDateTime CREATED_AT = LocalDateTime.now();
    public static final LocalDateTime UPDATED_AT = LocalDateTime.now();

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    private Employee employee;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        setupModels();
    }

    @Test
    @DisplayName("When Get All Employees is called then should return list of employees")
    void whenGetAllEmployeesIsCalledThenShouldReturnListOfEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(Collections.singletonList(employee));
        when(employeeMapper.mapListEntityToListDto(any())).thenReturn(Collections.singletonList(employeeDto));

        ResponseEntity<List<EmployeeDto>> result = employeeController.getAllEmployees();

        assertNotNull(result);
        assertThat("Has 1 item", Objects.requireNonNull(result.getBody()).size() == 1);
        assertEquals(result.getBody().getFirst().getId(), employeeDto.getId());
    }

    @Test
    @DisplayName("When Get Employee By ID Is Called Then Should Return Employee")
    void whenGetEmployeeByIdIsCalledThenShouldReturnEmployee() {
        when(employeeService.getEmployeeById(ID)).thenReturn(employee);
        when(employeeMapper.mapEntityToDto(any())).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> result = employeeController.getEmployeeById(ID);

        assertNotNull(result);
        assertEquals(Objects.requireNonNull(result.getBody()).getId(), employeeDto.getId());
    }

    @Test
    @DisplayName("When Save Employee Is Called Then Should Save Successfully")
    void whenSaveEmployeeIsCalledThenShouldSaveSuccessfully() {
        when(employeeMapper.mapEntityToDto(any())).thenReturn(employeeDto);
        when(employeeService.saveEmployee(any())).thenReturn(employee);

        ResponseEntity<EmployeeDto> result = employeeController.saveEmployee(employeeDto);

        assertNotNull(result);
        assertEquals(Objects.requireNonNull(result.getBody()).getId(), employeeDto.getId());
        assertEquals(Objects.requireNonNull(result.getBody()).getFirstName(), employeeDto.getFirstName());
        assertEquals(Objects.requireNonNull(result.getBody()).getLastName(), employeeDto.getLastName());
        assertEquals(Objects.requireNonNull(result.getBody()).getAddress(), employeeDto.getAddress());
    }

    @Test
    @DisplayName("When Update Employee Is Called Then Should Update Successfully")
    void whenUpdateEmployeeIsCalledThenShouldUpdateSuccessfully() {
        when(employeeMapper.mapEntityToDto(any())).thenReturn(employeeDto);
        when(employeeService.saveEmployee(any())).thenReturn(employee);

        ResponseEntity<EmployeeDto> result = employeeController.updateEmployee(employeeDto);

        assertNotNull(result);
        assertEquals(Objects.requireNonNull(result.getBody()).getId(), employeeDto.getId());
        assertEquals(Objects.requireNonNull(result.getBody()).getFirstName(), employeeDto.getFirstName());
        assertEquals(Objects.requireNonNull(result.getBody()).getLastName(), employeeDto.getLastName());
        assertEquals(Objects.requireNonNull(result.getBody()).getAddress(), employeeDto.getAddress());
    }

    @Test
    @DisplayName("When Delete Employee Is Called Then Should Delete Successfully")
    void whenDeleteEmployeeIsCalledThenShouldDeleteSuccessfully() {
        doNothing().when(employeeService).deleteEmployeeById(1L);

        ResponseEntity<String> result = employeeController.deleteEmployee(1L);

        verify(employeeService, times(1)).deleteEmployeeById(1L);
        assertThat("Deleted employee successfully", is(result.getBody()));
    }


    private void setupModels(){
        employee = new Employee(ID,
                FIRST_NAME,
                LAST_NAME,
                AGE,
                DESIGNATION,
                PHONE_NUMBER,
                JOINED_ON,
                ADDRESS,
                DATE_OF_BIRTH,
                CREATED_AT,
                UPDATED_AT);

        employeeDto = new EmployeeDto(ID,
                FIRST_NAME,
                LAST_NAME,
                AGE,
                DESIGNATION,
                PHONE_NUMBER,
                JOINED_ON,
                ADDRESS,
                DATE_OF_BIRTH,
                CREATED_AT,
                UPDATED_AT);
    }
}