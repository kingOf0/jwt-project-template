package com.kingof0.jwtprojecttemplate.model.entity.customer;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class BillAddress extends BasicAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taxOffice;
    private String taxNumber;
    private String companyName;
    private String turkishIdNumber;


}
