package com.employee.management.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {

    public DepartmentAlreadyExistsException(String name){super("Department with name " + name + " already exist.");}
}
