package com.employee.management.dao.entity;

import com.employee.management.model.ScratchCodesDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="G-Auth-Action")
public class GAuthAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "secretKey")
    private String secretKey;

    @Column(name = "validationCode")
    private int validationCode;

    @Column(name = "scratchCodes")
    @Transient
    private List<Integer> scratchCodes;

}
