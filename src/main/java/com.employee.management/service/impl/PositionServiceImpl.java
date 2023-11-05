package com.employee.management.service.impl;

import com.employee.management.dao.entity.Position;
import com.employee.management.dao.repository.PositionRepository;
import com.employee.management.model.PositionDto;
import com.employee.management.model.PositionRequest;
import com.employee.management.model.PositionResponse;
import com.employee.management.service.PositionService;
import com.employee.management.mapper.PositionMapper;
import com.employee.management.exception.PositionAlreadyExistsException;
import com.employee.management.exception.PositionNotFoundException;
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
    public PositionResponse createPosition(PositionRequest request) {
        if (positionRepository.findByName(request.getName()).isPresent()) {
            logger.error("Position with name: " + request.getName() + " already exists");
            throw new PositionAlreadyExistsException(request.getName());
        }

        Position position = PositionMapper.INSTANCE.requestToEntity(request);
        position = positionRepository.save(position);

        logger.info("Created a new position with ID: {}", position.getId());

        return PositionMapper.INSTANCE.dtoToResponse(PositionMapper.INSTANCE.entityToDto(position));
    }

    @Override
    public PositionResponse getPositionById(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Position with ID: " + id + " not found");
                    return new PositionNotFoundException(id);
                });

        PositionDto positionDto = PositionMapper.INSTANCE.entityToDto(position);
        logger.info("Retrieved position with ID: {}", id);

        return PositionMapper.INSTANCE.dtoToResponse(positionDto);
    }

    @Override
    public List<PositionResponse> getAllPositions() {
        List<Position> positions = positionRepository.findAll();
        List<PositionDto> positionDtos = PositionMapper.INSTANCE.entityListToDtoList(positions);
        List<PositionResponse> positionResponses = PositionMapper.INSTANCE.dtoToResponseList(positionDtos);

        logger.info("Retrieved a list of {} positions", positions.size());

        return positionResponses;
    }

    @Override
    public PositionResponse editPosition(Long id, PositionRequest request) {
        Position position = positionRepository.findById(id)
                .map(existingPosition -> {
                    existingPosition.setName(request.getName());
                    existingPosition.setSalary(request.getSalary());
                    Position updatedPosition = positionRepository.save(existingPosition);

                    logger.info("Edited position with ID: {}", updatedPosition.getId());

                    return updatedPosition;
                })
                .orElseThrow(() -> {
                    logger.error("Position with ID: " + id + " not found");
                    return new PositionNotFoundException(id);
                });

        return PositionMapper.INSTANCE.dtoToResponse(PositionMapper.INSTANCE.entityToDto(position));
    }

    @Override
    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Position with ID: " + id + " not found");
                    return new PositionNotFoundException(id);
                });

        positionRepository.delete(position);

        logger.info("Deleted position with ID: {}", id);
    }
}
