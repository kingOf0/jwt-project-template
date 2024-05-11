package com.kingof0.jwtprojecttemplate.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingof0.jwtprojecttemplate.exception.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final UserAuthProvider userAuthProvider;

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        try {
            Logger.getGlobal().info("UserAuthenticationEntryPoint: Error: '%s' Username: '%s' Method: '%s' Uri: '%s' ".formatted(authException.getMessage(), userAuthProvider.getCurrentUsername(), request.getMethod(), request.getRequestURI()));
            OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDTO("Authentication Entry Point error! Check the path, method and body!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
