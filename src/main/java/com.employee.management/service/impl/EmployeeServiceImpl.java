package com.employee.management.service.impl;

import com.employee.management.dao.entity.Employee;
import com.employee.management.dao.repository.EmployeeRepository;
import com.employee.management.exception.EmployeeNotFoundedException;
import com.employee.management.mapper.EmployeeMapper;
import com.employee.management.model.dto.EmployeeDto;
import com.employee.management.model.request.EmployeeRequest;
import com.employee.management.model.response.EmployeeResponse;
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
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = EmployeeMapper.INSTANCE.requestToEntity(request);
        employee = employeeRepository.save(employee);
        logger.info("Created a new employee with ID: {}", employee.getId());
        return EmployeeMapper.INSTANCE.dtoToResponse(EmployeeMapper.INSTANCE.entityToDto(employee));
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            EmployeeDto employeeDto = EmployeeMapper.INSTANCE.entityToDto(employee);
            logger.info("Retrieved employee with ID: {}", id);
            return EmployeeMapper.INSTANCE.dtoToResponse(employeeDto);
        } else {
            logger.error("Employee with ID: {} not found", id);
            throw new EmployeeNotFoundedException(id);
        }
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = EmployeeMapper.INSTANCE.entitiesToDtos(employees);
        List<EmployeeResponse> employeeResponses = EmployeeMapper.INSTANCE.dtoToResponseList(employeeDtos);
        logger.info("Retrieved a list of {} employees", employees.size());
        return employeeResponses;
    }

    @Override
    public EmployeeResponse editEmployee(Long id, EmployeeRequest request) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(request.getName());
            existingEmployee.setSurname(request.getSurname());
            existingEmployee.setEmail(request.getEmail());
            existingEmployee.setStatus(request.isStatus());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            logger.info("Edited employee with ID: {}", updatedEmployee.getId());
            return EmployeeMapper.INSTANCE.dtoToResponse(EmployeeMapper.INSTANCE.entityToDto(updatedEmployee));
        } else {
            logger.error("Employee with ID: {} not found", id);
            throw new EmployeeNotFoundedException(id);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeRepository.delete(employee);
            logger.info("Deleted employee with ID: {}", id);
        } else {
            logger.error("Employee with ID: {} not found", id);
            throw new EmployeeNotFoundedException(id);
        }
    }
}
