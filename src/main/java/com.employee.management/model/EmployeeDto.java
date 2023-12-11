package com.employee.management.model;

import com.employee.management.dao.entity.Department;
import com.employee.management.dao.entity.Position;
import com.employee.management.model.DepartmentDto;
import com.employee.management.model.PositionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
