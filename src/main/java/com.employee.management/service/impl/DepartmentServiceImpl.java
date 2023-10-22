package com.employee.management.service.impl;

import com.employee.management.dao.entity.Department;
import com.employee.management.dao.repository.DepartmentRepository;
import com.employee.management.dto.DepartmentDto;
import com.employee.management.mapper.DepartmentMapper;
import com.employee.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(DepartmentDto departmentDto) {
        Department department= DepartmentMapper.INSTANCE.dtoToEntity(departmentDto);
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department editDepartament(Long id, DepartmentDto departmentDto) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    existingDepartment.setName(departmentDto.getName());
                    return departmentRepository.save(existingDepartment);
                })
                .orElse(null);


}

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
