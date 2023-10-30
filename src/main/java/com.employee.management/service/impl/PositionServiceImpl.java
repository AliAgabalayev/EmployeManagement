package com.employee.management.service.impl;

import com.employee.management.dao.entity.Position;
import com.employee.management.dao.repository.PositionRepository;
import com.employee.management.dto.PositionDto;
import com.employee.management.exception.PositionAlreadyExistsException;
import com.employee.management.exception.PositionNotFoundException;
import com.employee.management.mapper.PositionMapper;
import com.employee.management.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private static final Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Override
    public Position createPosition(PositionDto positionDto) {
        if (positionRepository.findByName(positionDto.getName()).isPresent()) {
            logger.error("Position with name: {} already exists", positionDto.getName());
            throw new PositionAlreadyExistsException(positionDto.getName());
        }

        Position position = PositionMapper.INSTANCE.dtoToEntity(positionDto);
        position = positionRepository.save(position);

        logger.info("Created a new position with ID: {}", position.getId());

        return position;
    }

    @Override
    public Position getPositionById(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(id));

        logger.info("Retrieved position with ID: {}", id);

        return position;
    }

    @Override
    public List<Position> getAllPosition() {
        List<Position> positions = positionRepository.findAll();

        logger.info("Retrieved a list of {} positions", positions.size());

        return positions;
    }

    @Override
    public Position editPosition(Long id, PositionDto positionDto) {
        Position position = positionRepository.findById(id)
                .map(existingPosition -> {
                    existingPosition.setName(positionDto.getName());
                    existingPosition.setSalary(positionDto.getSalary());
                    existingPosition.setDepartment(positionDto.getDepartment());
                    Position updatedPosition = positionRepository.save(existingPosition);

                    logger.info("Edited position with ID: {}", updatedPosition.getId());

                    return updatedPosition;
                })
                .orElseThrow(() -> new PositionNotFoundException(id));

        return position;
    }

    @Override
    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(id));

        positionRepository.delete(position);

        logger.info("Deleted position with ID: {}", id);
    }
}
