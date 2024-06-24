package com.kingof0.jwtprojecttemplate.model.entity.customer;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class CustomerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String turkishIdNumber;

    private String email;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private BasicAddress address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private BillAddress billAddress;

}
