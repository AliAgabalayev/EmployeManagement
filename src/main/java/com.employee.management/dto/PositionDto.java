package com.employee.management.dto;

import com.employee.management.dao.entity.Department;
import lombok.Data;

@Data
public class PositionDto {
    private String name;

    private double salary;

    private Department department;
}
