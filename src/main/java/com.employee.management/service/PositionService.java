package com.employee.management.service;

import com.employee.management.dao.entity.Position;
import com.employee.management.dto.PositionDto;

import java.util.List;

public interface PositionService {
    public Position createPosition(PositionDto positionDto);

    public Position getPositionById(Long Id);

    public List<Position> getAllPosition();

    public Position editPosition(Long id,PositionDto positionDto);

    public  void deletePosition(Long id);


}
