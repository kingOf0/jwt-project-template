package com.kingof0.jwtprojecttemplate.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserContactDTO {

    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;

}
