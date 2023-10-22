package com.employee.management.dao.repository;

import com.employee.management.dao.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
     Employee findByEmail(String email);

}
