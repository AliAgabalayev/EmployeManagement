package com.employee.management.controller;


import com.employee.management.model.request.EmployeeRequest;
import com.employee.management.model.response.EmployeeResponse;
import com.employee.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employee = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.ok(employee);
    }

    @GetMapping()
    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> editEmployee(@PathVariable Long employeeId, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse updatedEmployee = employeeService.editEmployee(employeeId, employeeRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
