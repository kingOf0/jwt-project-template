package com.kingof0.jwtprojecttemplate.model.entity.trip;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TripComment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;
    private String author;
    private Integer rating;
    private LocalDate date;

}
