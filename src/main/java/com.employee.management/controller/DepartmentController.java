package com.employee.management.controller;

import com.employee.management.dao.entity.Department;
import com.employee.management.dto.DepartmentDto;
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
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDto departmentDto){
        Department department=departmentService.createDepartment(departmentDto);
        return ResponseEntity.ok(department);
    }
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long departmentId){
        DepartmentDto departmentDto=departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping("/{departmentName}")
    public  ResponseEntity<DepartmentDto> getDepartmentByName(@PathVariable String departmentName){
        DepartmentDto departmentDto=departmentService.getDepartmetByName(departmentName);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departmentDtoList=departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentDtoList);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> editDepartment(@PathVariable Long departmentId,@RequestBody DepartmentDto departmentDto){
        Department updatedDepartment=departmentService.editDepartament(departmentId,departmentDto);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteDepartment(@PathVariable Long departmentId){
        departmentService.deleteDepartment(departmentId);
    }
}
