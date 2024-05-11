package com.kingof0.jwtprojecttemplate.controller;

import com.kingof0.jwtprojecttemplate.model.dto.user.UserCreateDTO;
import com.kingof0.jwtprojecttemplate.model.dto.user.UserModifyDTO;
import com.kingof0.jwtprojecttemplate.model.entity.user.User;
import com.kingof0.jwtprojecttemplate.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('VIEW_USERS') or hasAuthority('ADMINISTRATOR')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_USER') or hasAuthority('ADMINISTRATOR')")
    public User create(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.create(userCreateDTO);
    }

    @PutMapping("/modify/{userId}")
    @PreAuthorize("hasAuthority('MODIFY_USER') or hasAuthority('ADMINISTRATOR')")
    public void modify(@PathVariable Integer userId, @RequestBody UserModifyDTO userModifyDto) {
        userService.modify(userId, userModifyDto);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('DELETE_USER') or hasAuthority('ADMINISTRATOR')")
    public void delete(@PathVariable Integer userId) {
        userService.delete(userId);
    }

}
