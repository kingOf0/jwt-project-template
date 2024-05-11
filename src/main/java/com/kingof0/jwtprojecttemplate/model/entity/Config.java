package com.kingof0.jwtprojecttemplate.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Config {

    @Id
    @Column(nullable = false, unique = true, name = "_key")
    private String key;

    @Column(name = "_value")
    private String value;

}
