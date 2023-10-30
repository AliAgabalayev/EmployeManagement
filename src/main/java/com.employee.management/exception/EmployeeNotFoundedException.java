package com.employee.management.exception;

public class EmployeeNotFoundedException extends RuntimeException{
    public EmployeeNotFoundedException(String email){super("Employee with email " + " not founded");}
    public EmployeeNotFoundedException(Long id){super("Employee with ID " + id + " not founded");}
}
