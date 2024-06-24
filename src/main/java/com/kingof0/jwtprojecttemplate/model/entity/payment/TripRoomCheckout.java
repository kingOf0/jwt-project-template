package com.kingof0.jwtprojecttemplate.model.entity.payment;

import com.kingof0.jwtprojecttemplate.model.entity.customer.Customer;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class TripRoomCheckout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TripRoom tripRoom;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customers = new ArrayList<>();

}
