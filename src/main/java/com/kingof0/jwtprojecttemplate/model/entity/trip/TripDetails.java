package com.kingof0.jwtprojecttemplate.model.entity.trip;

import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TripDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> includedServices = new ArrayList<>();

    @ElementCollection
    private List<String> excludedServices = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UploadedFile mapImage;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripFAQ> faqs = new ArrayList<>();

}
