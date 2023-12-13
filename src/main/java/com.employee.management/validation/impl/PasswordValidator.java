package com.employee.management.validation.impl;

import com.employee.management.validation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // Password validation logic (for example, requiring a minimum length of 8 characters)
        return password.length() >= 8;
    }
}
