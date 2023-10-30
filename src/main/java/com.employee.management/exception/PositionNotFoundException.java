package com.employee.management.exception;

public class PositionNotFoundException extends RuntimeException{
    public PositionNotFoundException(Long id){
        super("Position with ID "+ id + "not founded");
    }

    public PositionNotFoundException(String name){super("Position with name "+ name  +"not founded");}
}
