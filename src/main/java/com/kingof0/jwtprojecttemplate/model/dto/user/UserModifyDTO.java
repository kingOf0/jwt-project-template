package com.kingof0.jwtprojecttemplate.model.dto.user;

import com.kingof0.jwtprojecttemplate.model.entity.user.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyDTO {

    private Integer userId;

    private String newPassword;

    private List<Authority> authorities;

    private UserContactDTO contact;

}
