package com.employee.management.service.impl;

import com.employee.management.dao.entity.Department;
import com.employee.management.dao.repository.DepartmentRepository;
import com.employee.management.dto.DepartmentDto;
import com.employee.management.exception.DepartmentAlreadyExistsException;
import com.employee.management.exception.DepartmentNotFoundException;
import com.employee.management.mapper.DepartmentMapper;
import com.employee.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private static final Logger logger= LoggerFactory.getLogger(DepartmentService.class);
    @Override
    public Department createDepartment(DepartmentDto departmentDto) {
        Optional<Department> existingDepartment = departmentRepository.findByName(departmentDto.getName());

        if (existingDepartment.isPresent()) {
            logger.error("Department with name: {} already exists", departmentDto.getName());
            throw new DepartmentAlreadyExistsException(departmentDto.getName());
        }

        Department createdDepartment = DepartmentMapper.INSTANCE.dtoToEntity(departmentDto);
        try {
            createdDepartment = departmentRepository.save(createdDepartment);
            logger.info("Created new department with ID: {}", createdDepartment.getId());
        } catch (DataIntegrityViolationException e) {
            logger.error("Error while creating department: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid department data");
        } catch (NullPointerException e) {
            logger.error("Null pointer exception while creating department: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid department data");
        } catch (Exception e) {
            logger.error("Error while creating department: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid department data");
        }

        return createdDepartment;
    }


    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department= departmentRepository.findById(id)
                .orElseThrow(() ->{
                    logger.error("Department with ID: {} not found",id);
                    return new DepartmentNotFoundException(id);
                });
        DepartmentDto departmentDto=DepartmentMapper.INSTANCE.dtoToEntity(department);
        logger.info("Retrieved department: {}", department);
        return departmentDto;
    }

    @Override
    public DepartmentDto getDepartmetByName(String name) {
       Department department= departmentRepository.findByName(name)
                    .orElseThrow(() ->{
                        logger.error("Department with name: {} not found",name);
                        return new DepartmentNotFoundException(name);
                    });
       DepartmentDto departmentDto=DepartmentMapper.INSTANCE.dtoToEntity(department);
        logger.info("Retrieved department: {}", department);
        return departmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> allDepartments=DepartmentMapper.INSTANCE.entityToDto(departments);
        logger.info("Retrieved a list of {} departments", departments.size());
        return allDepartments;
    }

    @Override
    public Department editDepartament(Long id, DepartmentDto departmentDto) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    existingDepartment.setName(departmentDto.getName());
                    return departmentRepository.save(existingDepartment);
                })
                .orElseThrow(() -> {
                    logger.error("Department with ID: {} not found",id);
                    return new DepartmentNotFoundException(id);
                });


}

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Department with ID: {} not found", id);
                    throw new DepartmentNotFoundException(id);
                });

        departmentRepository.delete(department);
        logger.info("Deleted department with ID: {}", id);
    }
}
