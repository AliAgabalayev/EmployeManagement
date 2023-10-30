package com.employee.management.dto;

import com.employee.management.dao.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {
    private String name;

    private double salary;

    private Department department;
}
