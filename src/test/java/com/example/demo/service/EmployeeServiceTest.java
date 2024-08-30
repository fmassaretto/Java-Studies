package com.example.demo.service;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.entity.Employee;
import com.example.demo.model.dto.EmployeeDto;
import com.example.demo.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    public static final int AGE = 22;
    public static final String DESIGNATION = "designation";
    public static final String PHONE_NUMBER = "11912341234";
    public static final LocalDate JOINED_ON = LocalDate.now();
    public static final LocalDate DATE_OF_BIRTHDAY = LocalDate.now();
    public static final String ADDRESS = "address";
    public static final LocalDateTime CREATED_AT = LocalDateTime.now();
    public static final LocalDateTime UPDATED_AT = LocalDateTime.now();

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
        setupEmployee();
    }

    @Test
    @DisplayName("When find all Employees then return list of employee successfully")
    void WhenFindAllEmployeesThenReturnListOfEmployeeSuccessfully() {
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertNotNull(result.getFirst());
        assertEquals(1, result.size());
        assertEquals(employee, result.getFirst());

        assertEquals(ID, result.getFirst().getEmployeeId());
        assertEquals(FIRST_NAME, result.getFirst().getFirstName());
        assertEquals(LAST_NAME, result.getFirst().getLastName());
        assertEquals(AGE, result.getFirst().getAge());
    }

    @Test
    @DisplayName("When get employee by id returns employee successfully")
    void WhenGetEmployeeByIdReturnsEmployeeSuccessfully() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(ID);

        assertNotNull(result);
        assertEquals(ID, result.getEmployeeId());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(AGE, result.getAge());

    }

    @Test
    @DisplayName("When employee cannot be found then throws EntityNotFoundException")
    void WhenEmployeeCannotBeFoundThenThrowsEntityNotFoundException() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.getEmployeeById(ID));
    }

    @Test
    @DisplayName("When Perform Save Then Should Save Employee Successfully")
    void WhenPerformSaveThenShouldSaveEmployeeSuccessfully() {
        when(employeeMapper.mapDtoToEntity(any())).thenReturn(employee);
        when(employeeRepository.save(any())).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employeeDto);

        assertNotNull(result);
        assertEquals(ID, result.getEmployeeId());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(AGE, result.getAge());
    }

    @Test
    @DisplayName("When Perform Update Then Should Update Employee Successfully")
    void WhenPerformUpdateThenShouldUpdateEmployeeSuccessfully() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeMapper.mapDtoToEntity(any())).thenReturn(employee);
        when(employeeRepository.save(any())).thenReturn(employee);

        Employee result = employeeService.updateEmployee(employeeDto);

        assertNotNull(result);
        assertEquals(ID, result.getEmployeeId());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(AGE, result.getAge());
    }

    @Test
    @DisplayName("When Perform Delete Then Should Delete Employee Successfully")
    void WhenPerformDeleteThenShouldDeleteEmployeeSuccessfully() {
        when(employeeRepository.save(any())).thenReturn(employee);
        doNothing().when(employeeRepository).deleteById(employee.getEmployeeId());

        employeeService.deleteEmployeeById(ID);

        verify(employeeRepository, times(1)).deleteById(employee.getEmployeeId());
    }

    private void setupEmployee() {

        employee = new Employee(ID,
                FIRST_NAME,
                LAST_NAME,
                AGE,
                DESIGNATION,
                PHONE_NUMBER,
                JOINED_ON,
                ADDRESS,
                DATE_OF_BIRTHDAY,
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
                DATE_OF_BIRTHDAY,
                CREATED_AT,
                UPDATED_AT);
    }
}