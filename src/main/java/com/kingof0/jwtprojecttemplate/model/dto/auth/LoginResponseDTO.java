package com.kingof0.jwtprojecttemplate.model.dto.auth;

import com.kingof0.jwtprojecttemplate.model.entity.user.Authority;
import com.kingof0.jwtprojecttemplate.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private String username;

    private String token;

    private Collection<String> authorities;

    public LoginResponseDTO(User save) {
        this.username = save.getUsername();
        this.token = "";
        this.authorities = save.getAuthoritiesSet().stream().map(Authority::getAuthority).toList();
    }
}
