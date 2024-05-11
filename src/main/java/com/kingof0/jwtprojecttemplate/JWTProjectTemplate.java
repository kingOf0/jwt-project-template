package com.kingof0.jwtprojecttemplate;

import com.kingof0.jwtprojecttemplate.model.entity.user.Authority;
import com.kingof0.jwtprojecttemplate.security.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class JWTProjectTemplate implements CommandLineRunner {

    private final UserServiceImp userServiceImp;

    public static void main(String[] args) {
        SpringApplication.run(JWTProjectTemplate.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userServiceImp.loadUserByUsername("admin") == null) {
            userServiceImp.register("admin", "admin", List.of(
                    Authority.ADMINISTRATOR
            ));
        }


    }

}
