//package com.employee.management.dao.entity;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Type;
//import java.io.Serializable;
//import java.util.List;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "G-Auth-Action")
//public class GAuthAction implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "username")
//    private String username;
//
//    @Column(name = "secretKey")
//    private String secretKey;
//
//    @Column(name = "validationCode")
//    private int validationCode;
//
//   @Column(name = "scratchCodes")
//    private List<Integer> scratchCodes;
//
//}
