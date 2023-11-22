package com.employee.management.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GAuthDto {
    private String username;
    private String secretKey;
    private int validationCode;
    private List<Integer> scratchCodes;
}
