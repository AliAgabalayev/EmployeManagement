package com.employee.management.service.impl;

import com.employee.management.dao.entity.Position;
import com.employee.management.dao.repository.PositionRepository;
import com.employee.management.dto.PositionDto;
import com.employee.management.mapper.PositionMapper;
import com.employee.management.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    @Override
    public Position createPosition(PositionDto positionDto) {
        Position position= PositionMapper.INSTANCE.dtoToEntity(positionDto);
        return positionRepository.save(position);
    }

    @Override
    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Position> getAllPosition() {
        return positionRepository.findAll();
    }

    @Override
    public Position editPosition(Long id, PositionDto positionDto) {
        return positionRepository.findById(id)
                .map(existingPosition -> {
                    existingPosition.setName(positionDto.getName());
                    existingPosition.setSalary(positionDto.getSalary());
                    existingPosition.setDepartment(positionDto.getDepartment());
                    return positionRepository.save(existingPosition);
                })
                .orElse(null);
    }

    @Override
    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }

}
