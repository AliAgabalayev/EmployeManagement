package com.employee.management.service;

import com.employee.management.model.request.PositionRequest;
import com.employee.management.model.response.PositionResponse;

import java.util.List;

public interface PositionService {
    PositionResponse createPosition(PositionRequest request);

    PositionResponse getPositionById(Long id);

    List<PositionResponse> getAllPositions();

    PositionResponse editPosition(Long id, PositionRequest request);

    void deletePosition(Long id);
}
