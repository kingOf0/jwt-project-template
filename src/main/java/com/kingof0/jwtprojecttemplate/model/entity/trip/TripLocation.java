package com.kingof0.jwtprojecttemplate.model.entity.trip;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class TripLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;

    private String name;

    private String country;
    private String city;
    private String district;
    private String address;

}
