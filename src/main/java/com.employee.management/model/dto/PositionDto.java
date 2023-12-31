package com.employee.management.model.dto;


import com.employee.management.model.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {
    private Long id;
    private String name;
    private double salary;
    private DepartmentDto department;
}
