package com.kingof0.jwtprojecttemplate.model.entity.customer;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class BasicAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String city;
    private String district;
    private String postalCode;
    private String address;


}
