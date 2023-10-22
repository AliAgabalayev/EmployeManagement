package com.employee.management.dao.repository;

import com.employee.management.dao.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
}
