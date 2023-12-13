package com.employee.management.service.impl;

import com.employee.management.dao.entity.Department;
import com.employee.management.dao.repository.DepartmentRepository;
import com.employee.management.exception.DepartmentAlreadyExistsException;
import com.employee.management.exception.DepartmentNotFoundException;
import com.employee.management.mapper.DepartmentMapper;
import com.employee.management.model.request.DepartmentRequest;
import com.employee.management.model.response.DepartmentResponse;
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
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Optional<Department> existingDepartment = departmentRepository.findByName(departmentRequest.getName());

        if (existingDepartment.isPresent()) {
            logger.error("Department with name: {} already exists", departmentRequest.getName());
            throw new DepartmentAlreadyExistsException(departmentRequest.getName());
        }

        Department createdDepartment = DepartmentMapper.INSTANCE.requestToEntity(departmentRequest);

        try {
            createdDepartment = departmentRepository.save(createdDepartment);
            if (createdDepartment == null) {
                logger.error("Department not saved. Save operation returned null.");
                throw new IllegalStateException("Department not saved");
            }
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

        return DepartmentMapper.INSTANCE.entityToResponse(createdDepartment);
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> {
            logger.error("Department with ID: {} not found", id);
            return new DepartmentNotFoundException(id);
        });
        return DepartmentMapper.INSTANCE.entityToResponse(department);
    }

    @Override
    public DepartmentResponse getDepartmentByName(String name) {
        Department department = departmentRepository.findByName(name).orElseThrow(() -> {
            logger.error("Department with name: {} not found", name);
            return new DepartmentNotFoundException(name);
        });
        return DepartmentMapper.INSTANCE.entityToResponse(department);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return DepartmentMapper.INSTANCE.entityListToResponseList(departments);
    }

    @Override
    public DepartmentResponse editDepartment(Long id, DepartmentRequest departmentRequest) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> {
            logger.error("Department with ID: {} not found", id);
            return new DepartmentNotFoundException(id);
        });

        existingDepartment.setName(departmentRequest.getName());
        Department updatedDepartment = departmentRepository.save(existingDepartment);

        return DepartmentMapper.INSTANCE.entityToResponse(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> {
            logger.error("Department with ID: {} not found", id);
            throw new DepartmentNotFoundException(id);
        });

        departmentRepository.delete(department);
        logger.info("Deleted department with ID: {}", id);
    }
}
