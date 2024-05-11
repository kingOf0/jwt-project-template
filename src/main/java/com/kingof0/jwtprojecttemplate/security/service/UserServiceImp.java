package com.kingof0.jwtprojecttemplate.security.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kingof0.jwtprojecttemplate.exception.AppException;
import com.kingof0.jwtprojecttemplate.exception.AuthException;
import com.kingof0.jwtprojecttemplate.model.dto.auth.AuthmeDTO;
import com.kingof0.jwtprojecttemplate.model.dto.auth.LoginRequestDTO;
import com.kingof0.jwtprojecttemplate.model.dto.auth.LoginResponseDTO;
import com.kingof0.jwtprojecttemplate.model.dto.user.UserCreateDTO;
import com.kingof0.jwtprojecttemplate.model.dto.user.UserModifyDTO;
import com.kingof0.jwtprojecttemplate.model.entity.user.Authority;
import com.kingof0.jwtprojecttemplate.model.entity.user.User;
import com.kingof0.jwtprojecttemplate.model.mapper.UserMapper;
import com.kingof0.jwtprojecttemplate.repository.UserRepository;
import com.kingof0.jwtprojecttemplate.security.UserAuthProvider;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    public final UserAuthProvider userAuthProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = loadUserByUsername(loginRequestDTO.getUsername());
        if (user == null) throw new AuthException("User not found");

        if (!passwordEncoder.matches(CharBuffer.wrap(loginRequestDTO.getPassword()), user.getPassword())) {
            throw new AuthException("Wrong password");
        }

        String token = userAuthProvider.generateToken(user.getUsername(), user.getAuthorities());

        LocalDateTime now = LocalDateTime.now();
        user.setLastLogin(now);
        user.setLastActivity(now);
        userRepository.save(user);

        return new LoginResponseDTO(user.getUsername(), token, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }

    @Override
    public LoginResponseDTO register(String username, String password) {
        return register(username, password, new ArrayList<>());
    }

    @Override
    public LoginResponseDTO register(String username, String password, List<Authority> authorities) {
        LocalDateTime now = LocalDateTime.now();

        User save = userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .lastLogin(now)
                .lastActivity(now)
                .authorities(authorities)
                .build());
        return new LoginResponseDTO(save);
    }

    @Override
    public Optional<User> getUserById(Integer author) {
        return userRepository.findById(author);
    }

    public boolean isExists(Integer author) {
        return userRepository.existsById(author);
    }

    @Override
    public LoginResponseDTO authMe(AuthmeDTO authmeDTO) {
        String token = authmeDTO.getToken();
        DecodedJWT decode = userAuthProvider.decodeToken(token);
        if (decode == null) throw new AuthException("Invalid Token");

        UserDetails user = loadUserByUsername(decode.getIssuer());
        if (user == null) throw new AuthException("User not found");

        return new LoginResponseDTO(user.getUsername(), token, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserCreateDTO userCreateDTO) {
        User currentUser = getCurrentUser();
        if (!currentUser.hasAnyAuthority(Authority.CREATE_USER, Authority.ADMINISTRATOR)) {
            throw new AuthException("You don't have permission to create user");
        }


        if (userRepository.getUserByUsername(userCreateDTO.getUsername()) != null) {
            throw new AuthException("User already exists");
        }

        User user = new User();

        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        if (currentUser.hasAnyAuthority(Authority.ADMINISTRATOR)) {
            user.setAuthorities(userCreateDTO.getAuthorities());
        }

        user.setContact(userMapper.userContactDTOToUserContact(userCreateDTO.getContact()));

        return userRepository.save(user);
    }

    @Override
    public void modify(Integer userId, UserModifyDTO userModifyDto) {
        if (!Objects.equals(userId, userModifyDto.getUserId())) throw new AppException("User id not match", 400);

        User currentUser = getCurrentUser();
        if (!currentUser.hasAnyAuthority(Authority.MODIFY_USER, Authority.ADMINISTRATOR)) {
            throw new AuthException("You don't have permission to modify user");
        }

        Optional<User> userById = getUserById(userModifyDto.getUserId());
        if (userById.isEmpty()) throw new AuthException("User not found");
        User user = userById.get();

        if (userModifyDto.getNewPassword() != null && !userModifyDto.getNewPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userModifyDto.getNewPassword()));
        }

        user.setContact(userMapper.userContactDTOToUserContact(userModifyDto.getContact()));

        if (currentUser.hasAnyAuthority(Authority.ADMINISTRATOR)) {
            user.setAuthorities(userModifyDto.getAuthorities());
        }

        userRepository.save(user);
    }

    @Override
    public void delete(Integer userId) {
        User currentUser = getCurrentUser();
        if (!currentUser.hasAnyAuthority(Authority.DELETE_USER, Authority.ADMINISTRATOR)) {
            throw new AuthException("You don't have permission to delete user");
        }

        if (currentUser.getId().equals(userId)) throw new AuthException("You can't delete yourself");

        Optional<User> userById = getUserById(userId);
        if (userById.isEmpty()) throw new AuthException("User not found");
        User user = userById.get();

        userRepository.delete(user);
    }

    @Override
    @NotNull
    public User getCurrentUser() {
        User user = userRepository.getUserByUsername(userAuthProvider.getCurrentUsername());
        if (user == null) throw new AuthException("Current User not found");
        return user;
    }


    public void save(User currentUser) {
        userRepository.save(currentUser);
    }

    public void deleteUserByUsername(String test) {
        userRepository.deleteUserByUsername(test);
    }
}
