package com.employee.management.model;

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
public class EmployeeRequest {

    private String name;
    private String surname;
    private String email;
    private boolean status;
    private Long departmentID;
    private Long positionID;

}