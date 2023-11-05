package com.employee.management.dao.repository;

import com.employee.management.dao.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
     Optional<Employee> findByEmail(String email);

}
