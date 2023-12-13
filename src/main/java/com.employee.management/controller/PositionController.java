package com.employee.management.controller;

import com.employee.management.model.request.PositionRequest;
import com.employee.management.model.response.PositionResponse;
import com.employee.management.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/positions")
public class PositionController {
    private final PositionService positionService;

    @PostMapping()
    public ResponseEntity<PositionResponse> createPosition(@RequestBody PositionRequest positionRequest) {
        PositionResponse positionResponse = positionService.createPosition(positionRequest);
        return ResponseEntity.ok(positionResponse);
    }

    @GetMapping()
    public ResponseEntity<List<PositionResponse>> getAllPositions() {
        List<PositionResponse> responseList = positionService.getAllPositions();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long id) {
        PositionResponse positionResponse = positionService.getPositionById(id);
        return ResponseEntity.ok(positionResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<PositionResponse> editPosition(@PathVariable Long id, @RequestBody PositionRequest positionRequest) {
        PositionResponse positionResponse = positionService.editPosition(id, positionRequest);
        return ResponseEntity.ok(positionResponse);
    }

    @DeleteMapping("{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }
}
