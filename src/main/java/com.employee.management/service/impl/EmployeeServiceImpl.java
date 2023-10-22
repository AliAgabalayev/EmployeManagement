package com.employee.management.service.impl;

import com.employee.management.dao.entity.Employee;
import com.employee.management.dao.repository.EmployeeRepository;
import com.employee.management.dto.EmployeeDto;
import com.employee.management.mapper.EmployeeMapper;
import com.employee.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.INSTANCE.dtoToEntity(employeeDto);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
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
                    return employeeRepository.save(existingEmployee);
                })
                .orElse(null);
        }


    @Override
    public void deleteEmployee(Long id) {employeeRepository.deleteById(id);}
}
