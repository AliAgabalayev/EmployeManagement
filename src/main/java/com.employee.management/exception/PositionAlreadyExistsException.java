package com.employee.management.exception;

public class PositionAlreadyExistsException extends RuntimeException{
    public PositionAlreadyExistsException(String name){super("Department with name" + name + "already exist");}
}
