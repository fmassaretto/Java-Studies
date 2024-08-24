package com.example.demo.mapper;

import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeMapperTest {
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
    private EmployeeMapperImpl mapper;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        setupEmployee();
    }

    @Test
    @DisplayName("When List Of Employees Then Should Return List Of EmployeesDto")
    void whenListOfEmployeesThenShouldReturnListOfEmployeesDto() {
        List<EmployeeDto> result = mapper.mapListEntityToListDto(Collections.singletonList(employee));

        assertThat(result.size()).isEqualTo(1);
        assertEquals(result.getFirst().getFirstName(), FIRST_NAME);
    }
    @Test
    @DisplayName("When Pass List With A Null Employee Then Should Return Null")
    void whenPassListWithANullEmployeeThenShouldReturnNull() {
        List<EmployeeDto> result = mapper.mapListEntityToListDto(Collections.singletonList(null));

        assertNull(result.getFirst());
    }

    @Test
    @DisplayName("When Null Is Passed As Parameter To MapListEntityToListDto Then Should Return Null")
    void whenNullIsPassedAsParameterToMapListEntityToListDtoThenShouldReturnNull() {
        List<EmployeeDto> result = mapper.mapListEntityToListDto(null);

        assertNull(result);
    }

    @Test
    @DisplayName("When Passed Employee Then Should Return EmployeeDto")
    void whenPassedEmployeeThenShouldReturnEmployeeDto() {
        EmployeeDto result = mapper.mapEntityToDto(employee);

        assertNotNull(result);
        assertEquals(result.getFirstName(), FIRST_NAME);
    }

    @Test
    @DisplayName("When Parameter Employee Is Null Then Should Return Null")
    void whenParameterEmployeeIsNullThenShouldReturnNull() {
        EmployeeDto result = mapper.mapEntityToDto(null);

        assertNull(result);
    }

    @Test
    @DisplayName("When Passed EmployeeDto Then Should Return Employee")
    void whenPassedEmployeeDtoThenShouldReturnEmployee() {
        Employee result = mapper.mapDtoToEntity(employeeDto);

        assertNotNull(result);
        assertEquals(result.getFirstName(), FIRST_NAME);
    }

    @Test
    @DisplayName("When Parameter EmployeeDto Is Null Then Should Return Null")
    void whenParameterEmployeeDtoIsNullThenShouldReturnNull() {
        Employee result = mapper.mapDtoToEntity(null);

        assertNull(result);
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