package com.employee.management.model;

import lombok.Data;

@Data
public class ValidateKeyRequest {
    private String username;
    private int code;
}
