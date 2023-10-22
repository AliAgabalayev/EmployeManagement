package com.employee.management.dto;

import com.employee.management.dao.entity.Department;
import com.employee.management.dao.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private String name;
    private String surname;
    private String email;
    private boolean status;
    private Department department;
    private Position position;

}
