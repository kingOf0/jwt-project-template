package com.kingof0.jwtprojecttemplate.model.entity.customer;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String turkishIdNumber;
    private Gender gender;

    private LocalDate birthDate;

}
