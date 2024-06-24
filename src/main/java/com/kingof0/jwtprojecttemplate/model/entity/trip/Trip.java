package com.kingof0.jwtprojecttemplate.model.entity.trip;

import com.kingof0.jwtprojecttemplate.model.entity.CurrencyType;
import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    private String description;

    private String route;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadedFile> images = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TripLocation location;

    @ManyToOne(cascade = CascadeType.ALL)
    private TripCategory category;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TripDetails details;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripComment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripInformation> informations = new ArrayList<>();

    private Integer quota;

    private Integer duration;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double price;
    private Double strikethroughPrice;
    private CurrencyType currencyType;

}
