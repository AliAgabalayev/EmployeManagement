package com.employee.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private boolean status;

    private PositionDto position;

}
