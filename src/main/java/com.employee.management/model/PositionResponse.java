package com.employee.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionResponse {
    private Long id;
    private String name;
    private double salary;
    private String departmentName;
}
