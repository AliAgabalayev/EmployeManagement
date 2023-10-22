package com.employee.management.controller;

import com.employee.management.dao.entity.Department;
import com.employee.management.dto.DepartmentDto;
import com.employee.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/createDepartment")
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDto departmentDto){
        Department department=departmentService.createDepartment(departmentDto);
        return ResponseEntity.ok(department);
    }
}
