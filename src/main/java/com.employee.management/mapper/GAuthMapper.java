package com.employee.management.mapper;

import com.employee.management.dao.entity.GAuthAction;
import com.employee.management.model.GAuthDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class GAuthMapper {
     public static final GAuthMapper INSTANCE= Mappers.getMapper(GAuthMapper.class);

     public abstract GAuthAction dtoToEntity(GAuthDto gAuthDto);

}
