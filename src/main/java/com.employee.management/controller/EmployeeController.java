package com.employee.management.controller;

import com.employee.management.dao.entity.Employee;
import com.employee.management.dto.EmployeeDto;
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

     @PostMapping("/addEmployee")
     public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee=employeeService.addEmployee(employeeDto);
         return ResponseEntity.ok(employee);
     }

     @GetMapping("/getAllEmployees")
     public List<Employee> getAllEmployees(){
         return employeeService.getAllEmployees();
     }

     @GetMapping("/getEmployeeByEmail/email/{email}")
     public Employee getEmployeeByEmail(@PathVariable String email){
         return employeeService.getEmployeeByEmail(email);
     }

    @PutMapping("/editEmployee/id/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
         Employee updatedEmployee=employeeService.editEmployee(id, employeeDto);
         return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteEmployee(@PathVariable Long id){employeeService.deleteEmployee(id);}

}
