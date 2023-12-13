package com.employee.management.controller;

import com.employee.management.model.request.DepartmentRequest;
import com.employee.management.model.response.DepartmentResponse;
import com.employee.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping()
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse department = departmentService.createDepartment(departmentRequest);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long departmentId) {
        DepartmentResponse department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/byName/{departmentName}")
    public ResponseEntity<DepartmentResponse> getDepartmentByName(@PathVariable String departmentName) {
        DepartmentResponse department = departmentService.getDepartmentByName(departmentName);
        return ResponseEntity.ok(department);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        List<DepartmentResponse> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> editDepartment(@PathVariable Long departmentId, @RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse updatedDepartment = departmentService.editDepartment(departmentId, departmentRequest);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
