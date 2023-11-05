package com.employee.management.controller;

import com.employee.management.dao.entity.Position;
import com.employee.management.model.PositionRequest;
import com.employee.management.model.PositionResponse;
import com.employee.management.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/positions")
public class PositionController {
    private final PositionService positionService;

    @PostMapping()
    public ResponseEntity<PositionResponse> createPosition(@RequestBody PositionRequest positionRequest){
        PositionResponse positionResponse=positionService.createPosition(positionRequest);
        return ResponseEntity.ok(positionResponse);
    }
}
