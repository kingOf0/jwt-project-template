package com.kingof0.jwtprojecttemplate.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {

    private int code;

    public AppException(String message, int code) {
        super(message);
        this.code = code;
    }

}

