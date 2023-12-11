package com.employee.management.controller;

import com.employee.management.model.EmployeeRequest;
import com.employee.management.model.EmployeeResponse;
import com.employee.management.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void addEmployee() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setName("Test Employee");

        EmployeeResponse expectedResponse = new EmployeeResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName("Test Employee");

        Mockito.when(employeeService.createEmployee(request)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee-management/employees")
                        .content("{ \"name\": \"Test Employee\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedResponse.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getAllEmployees() throws Exception {
        EmployeeResponse employee1 = new EmployeeResponse(1L, "Employee1", "testsurname", "test@gmail", true, 1L);
        EmployeeResponse employee2 = new EmployeeResponse(2L, "Employee2", "testsurname", "test@gmail", true, 1L);

        Mockito.when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(employee1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(employee1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(employee2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(employee2.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getEmployeeById() throws Exception {
        Long employeeId = 1L;
        EmployeeResponse expectedResponse = new EmployeeResponse();
        expectedResponse.setId(employeeId);
        expectedResponse.setName("Test Employee");

        Mockito.when(employeeService.getEmployeeById(employeeId)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/employees/{employeeId}", employeeId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedResponse.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void editEmployee() throws Exception {
        Long employeeId = 1L;
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Updated Employee");

        EmployeeResponse updatedEmployee = new EmployeeResponse();
        updatedEmployee.setId(employeeId);
        updatedEmployee.setName(employeeRequest.getName());

        Mockito.when(employeeService.editEmployee(employeeId, employeeRequest)).thenReturn(updatedEmployee);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee-management/employees/{employeeId}", employeeId)
                        .content("{ \"name\": \"Updated Employee\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedEmployee.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedEmployee.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void deleteEmployee() throws Exception {
        Long employeeId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employee-management/employees/{employeeId}", employeeId))
                .andExpect(status().isOk());

        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(employeeId);
    }
}
