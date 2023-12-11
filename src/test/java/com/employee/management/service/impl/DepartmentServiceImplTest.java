package com.employee.management.service.impl;

import com.employee.management.dao.entity.Department;
import com.employee.management.dao.repository.DepartmentRepository;
import com.employee.management.exception.DepartmentAlreadyExistsException;
import com.employee.management.exception.DepartmentNotFoundException;
import com.employee.management.model.DepartmentRequest;
import com.employee.management.model.DepartmentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void createDepartment_WithNonExistingDepartment_ShouldReturnCreatedDepartment() {

        DepartmentRequest request = new DepartmentRequest();
        request.setName("TestDepartment");

        when(departmentRepository.findByName(request.getName())).thenReturn(Optional.empty());


        Department savedDepartment = new Department();
        savedDepartment.setId(1L); // Set an ID to simulate a saved department
        when(departmentRepository.save(any(Department.class))).thenReturn(savedDepartment);


        DepartmentResponse response = departmentService.createDepartment(request);


        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    void createDepartment_WithExistingDepartment_ShouldThrowException() {
        // Arrange
        DepartmentRequest request = new DepartmentRequest();
        request.setName("Test Department");

        when(departmentRepository.findByName(anyString())).thenReturn(Optional.of(new Department()));

        // Act and Assert
        assertThrows(DepartmentAlreadyExistsException.class, () -> departmentService.createDepartment(request));
    }

    @Test
    void getDepartmentById_WithExistingDepartment_ShouldReturnDepartmentResponse() {
        // Arrange
        Long departmentId = 1L;
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(new Department()));

        // Act
        DepartmentResponse response = departmentService.getDepartmentById(departmentId);

        // Assert
        assertNotNull(response);
        // Add more assertions based on your specific requirements
    }

    @Test
    void getDepartmentById_WithNonExistingDepartment_ShouldThrowException() {
        // Arrange
        Long departmentId = 1L;
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getDepartmentById(departmentId));
    }

    @Test
    void editDepartment_WithExistingDepartment_ShouldReturnUpdatedDepartment() {
        // Arrange
        Long departmentId = 1L;
        DepartmentRequest request = new DepartmentRequest();
        request.setName("Updated Department");

        Department existingDepartment = new Department();
        existingDepartment.setId(departmentId);
        existingDepartment.setName("Original Department");

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.save(any(Department.class))).thenReturn(existingDepartment);

        // Act
        DepartmentResponse response = departmentService.editDepartment(departmentId, request);

        // Assert
        assertNotNull(response);
        assertEquals(request.getName(), response.getName());
        // Add more assertions based on your specific requirements
    }

    // Add similar tests for other methods

    @Test
    void deleteDepartment_WithExistingDepartment_ShouldDeleteDepartment() {
        // Arrange
        Long departmentId = 1L;
        Department existingDepartment = new Department();
        existingDepartment.setId(departmentId);
        existingDepartment.setName("Test Department");

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(existingDepartment));

        // Act
        departmentService.deleteDepartment(departmentId);

        // Assert
        verify(departmentRepository, times(1)).delete(existingDepartment);
        verifyNoMoreInteractions(departmentRepository);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void deleteDepartment_WithNonExistingDepartment_ShouldThrowException() {
        // Arrange
        Long departmentId = 1L;
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.deleteDepartment(departmentId));
    }
}
