package com.kingof0.jwtprojecttemplate.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingof0.jwtprojecttemplate.exception.ErrorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AccessDeniedEntryPoint implements AccessDeniedHandler {
    static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final UserAuthProvider userAuthProvider;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        try {
            Logger.getGlobal().info("Access Control Entry Point: Error: '%s' Username: '%s' Method: '%s' Uri: '%s' ".formatted(accessDeniedException.getMessage(), userAuthProvider.getCurrentUsername(), request.getMethod(), request.getRequestURI()));
            OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDTO("Access Control Entry Point error! You don't have permission to access this resource!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
