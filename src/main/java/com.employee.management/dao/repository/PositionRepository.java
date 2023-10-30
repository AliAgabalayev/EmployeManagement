package com.employee.management.dao.repository;

import com.employee.management.dao.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position,Long> {
    Optional<Position> findByName(String name);
}
