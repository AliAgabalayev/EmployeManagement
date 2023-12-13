package com.employee.management.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime update_at;

    @OneToMany(mappedBy = "department")
    private Set<Position> positions;

    @PreRemove
    private void preRemove() {
        for (Position position : positions) {
            position.setDepartment(null);
        }
    }
}

