package com.kingof0.jwtprojecttemplate.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends AppException {

    public BadRequestException(String message) {
        super(message, 400);
    }

}

