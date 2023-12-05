package com.employee.management.controller;

import com.employee.management.model.DepartmentRequest;
import com.employee.management.model.DepartmentResponse;
import com.employee.management.service.DepartmentService;
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

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void createDepartment() throws Exception {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("Test Department");
        DepartmentResponse expectedResponse = new DepartmentResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName("Test Department");

        Mockito.when(departmentService.createDepartment(request)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee-management/departments")
                        .content("{ \"name\": \"Test Department\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedResponse.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getDepartmentById() throws Exception {
        Long departmentId = 1L;
        DepartmentResponse expectedResponse = new DepartmentResponse();
        expectedResponse.setId(departmentId);
        expectedResponse.setName("Test Department");

        Mockito.when(departmentService.getDepartmentById(departmentId)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/departments/{departmentId}", departmentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedResponse.getName()));
    }


    @Test
    @WithMockUser(username = "testuseer", roles = "ADMIN")
    void deleteDepartment() throws Exception {
        Long departmentId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employee-management/departments/{departmentId}", departmentId))
                .andExpect(status().isOk());

        Mockito.verify(departmentService, Mockito.times(1)).deleteDepartment(departmentId);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getDepartmentByName() throws Exception {
        String departmentName = "TestDepartment";
        DepartmentResponse expectedResponse = new DepartmentResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName(departmentName);

        Mockito.when(departmentService.getDepartmentByName(departmentName)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/departments/byName/{departmentName}", departmentName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedResponse.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getAllDepartments() throws Exception {
        // Mock the service response
        DepartmentResponse department1 = new DepartmentResponse(1L, "Department 1");
        DepartmentResponse department2 = new DepartmentResponse(2L, "Department 2");
        Mockito.when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(department1, department2));

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/departments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(department1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(department1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(department2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(department2.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void editDepartment() throws Exception {
        Long departmentId = 1L;
        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setName("UpdatedDepartment");

        DepartmentResponse updatedDepartment = new DepartmentResponse();
        updatedDepartment.setId(departmentId);
        updatedDepartment.setName(departmentRequest.getName());

        Mockito.when(departmentService.editDepartment(departmentId, departmentRequest)).thenReturn(updatedDepartment);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee-management/departments/{departmentId}", departmentId)
                        .content("{ \"name\": \"UpdatedDepartment\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedDepartment.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedDepartment.getName()));
    }


}
