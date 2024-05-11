package com.kingof0.jwtprojecttemplate.controller;

import com.kingof0.jwtprojecttemplate.model.dto.auth.AuthmeDTO;
import com.kingof0.jwtprojecttemplate.model.dto.auth.LoginRequestDTO;
import com.kingof0.jwtprojecttemplate.model.dto.auth.LoginResponseDTO;
import com.kingof0.jwtprojecttemplate.security.service.UserServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final UserServiceImp userServiceImp;

    public AuthController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userServiceImp.login(loginRequestDTO);
    }

    @PostMapping("/authme")
    public LoginResponseDTO authMe(@RequestBody AuthmeDTO authmeDTO) {
        return userServiceImp.authMe(authmeDTO);
    }

}
