package com.employee.management.controller;

import com.employee.management.dao.entity.Position;
import com.employee.management.dto.PositionDto;
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
    public ResponseEntity<Position> createPosition(@RequestBody PositionDto positionDto){
        Position position=positionService.createPosition(positionDto);
        return ResponseEntity.ok(position);
    }
}
