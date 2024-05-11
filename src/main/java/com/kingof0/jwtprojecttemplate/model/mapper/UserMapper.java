package com.kingof0.jwtprojecttemplate.model.mapper;

import com.kingof0.jwtprojecttemplate.model.dto.user.UserContactDTO;
import com.kingof0.jwtprojecttemplate.model.entity.user.UserContact;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserContactDTO userContactToUserContactDTO(UserContact userContact) {
        if (userContact == null) return null;

        UserContactDTO userContactDTO = new UserContactDTO();

        userContactDTO.setFirstName(userContact.getFirstName());
        userContactDTO.setLastName(userContact.getLastName());
        userContactDTO.setEmail(userContact.getEmail());
        userContactDTO.setPhoneNumber(userContact.getPhoneNumber());

        return userContactDTO;
    }

    public UserContact userContactDTOToUserContact(UserContactDTO userContactDTO) {
        if (userContactDTO == null) return null;

        UserContact userContact = new UserContact();

        userContact.setFirstName(userContactDTO.getFirstName());
        userContact.setLastName(userContactDTO.getLastName());
        userContact.setEmail(userContactDTO.getEmail());
        userContact.setPhoneNumber(userContactDTO.getPhoneNumber());

        return userContact;
    }

}
