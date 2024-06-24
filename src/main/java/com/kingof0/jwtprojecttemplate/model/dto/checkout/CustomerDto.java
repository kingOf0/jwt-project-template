package com.kingof0.jwtprojecttemplate.model.dto.checkout;

import com.kingof0.jwtprojecttemplate.model.entity.customer.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto {
    private String firstName;
    private String lastName;

    private String turkishIdNumber;
    private Gender gender;

    private LocalDate birthDate;
}
