package com.employee.management.service.impl;

import com.employee.management.dao.entity.Employee;
import com.employee.management.dao.repository.EmployeeRepository;
import com.employee.management.dto.EmployeeDto;
import com.employee.management.exception.EmployeeNotFoundedException;
import com.employee.management.mapper.EmployeeMapper;
import com.employee.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee createdEmployee = EmployeeMapper.INSTANCE.dtoToEntity(employeeDto);
        createdEmployee = employeeRepository.save(createdEmployee);

        logger.info("Added new employee with ID: {}", createdEmployee.getId());

        return createdEmployee;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        logger.info("Retrieving employee by email: {}", email);

        Employee employee = employeeRepository.findByEmail(email);

        if (employee != null) {
            logger.info("Retrieved employee with email: {}", email);
        } else {
            logger.info("Employee with email: {} not found", email);
            throw new EmployeeNotFoundedException(email);
        }

        return employee;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            logger.info("Retrieved employee with ID: {}", id);
        } else {
            logger.info("Employee with ID: {} not found", id);
            throw new EmployeeNotFoundedException(id);
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        logger.info("Retrieved a list of {} employees", employees.size());

        return employees;
    }

    @Override
    public Employee editEmployee(Long id, EmployeeDto employeeDto) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(employeeDto.getName());
                    existingEmployee.setSurname(employeeDto.getSurname());
                    existingEmployee.setEmail(employeeDto.getEmail());
                    existingEmployee.setStatus(employeeDto.isStatus());
                    existingEmployee.setDepartment(employeeDto.getDepartment());
                    existingEmployee.setPosition(employeeDto.getPosition());
                    Employee updatedEmployee = employeeRepository.save(existingEmployee);

                    logger.info("Edited employee with ID: {}", updatedEmployee.getId());

                    return updatedEmployee;
                })
                .orElseThrow(() -> {
                    logger.error("Employee with ID: {} not found", id);
                    return new EmployeeNotFoundedException(id);
                });
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            employeeRepository.delete(employee);
            logger.info("Deleted employee with ID: {}", id);
        } else {
            logger.error("Employee with ID: {} not found", id);
            throw new EmployeeNotFoundedException(id);
        }
    }
    }
