package com.kingof0.jwtprojecttemplate.model.entity.trip;

import com.kingof0.jwtprojecttemplate.model.entity.CurrencyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class TripRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    private String name;

    private Double price;

    private CurrencyType currencyType;

    private Integer guestCount;

}
