package com.kingof0.jwtprojecttemplate.model.entity.payment;

import com.kingof0.jwtprojecttemplate.model.entity.customer.CustomerContact;
import com.kingof0.jwtprojecttemplate.model.entity.trip.Trip;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true  )
    private List<TripRoomCheckout> roomCheckouts = new ArrayList    <>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerContact contact;

    @ManyToOne
    private Trip trip;

    private PaymentState status;

    private LocalDateTime createdAt;

    private Double totalPrice;

    private String token;

}
