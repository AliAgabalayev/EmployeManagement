package com.employee.management.mapper;

import com.employee.management.dao.entity.User;
import com.employee.management.model.request.UserRequest;
import com.employee.management.model.response.UserResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;



@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "status",expression = "java(getStatus())")
    public abstract User requestToEntity(UserRequest request);

    public abstract UserResponse entityToDto(User user);
    @Named("getStatus")
    protected boolean getStatus(){
        return true;
    }
}

