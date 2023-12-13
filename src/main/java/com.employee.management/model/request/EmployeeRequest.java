package com.employee.management.model.request;

import com.employee.management.dao.entity.Department;
import com.employee.management.dao.entity.Position;
import com.employee.management.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
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

    @ValidEmail
    @NotBlank
    private String email;

    private boolean status;

    private Long positionId;

}
