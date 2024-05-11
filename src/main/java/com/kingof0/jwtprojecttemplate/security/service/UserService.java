package com.kingof0.jwtprojecttemplate.security.service;

import com.kingof0.jwtprojecttemplate.model.dto.auth.AuthmeDTO;
import com.kingof0.jwtprojecttemplate.model.dto.auth.LoginResponseDTO;
import com.kingof0.jwtprojecttemplate.model.dto.user.UserCreateDTO;
import com.kingof0.jwtprojecttemplate.model.dto.user.UserModifyDTO;
import com.kingof0.jwtprojecttemplate.model.entity.user.Authority;
import com.kingof0.jwtprojecttemplate.model.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    LoginResponseDTO register(String username, String password);

    LoginResponseDTO register(String username, String password, List<Authority> authorities);

    Optional<User> getUserById(Integer author);

    LoginResponseDTO authMe(AuthmeDTO authmeDTO);

    void modify(Integer userId, UserModifyDTO userModifyDto);

    User getCurrentUser();

    void delete(Integer userId);

    User create(UserCreateDTO userCreateDTO);

    List<User> getAll();
}
