package com.kingof0.jwtprojecttemplate.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kingof0.jwtprojecttemplate.model.entity.user.User;
import com.kingof0.jwtprojecttemplate.security.service.UserServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;
    private final UserServiceImp userServiceImp;

    @Override
    public void doFilterInternal(@NotNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (!userAuthProvider.hasAuthorizationBearer(header)) {
                //Debug removed.
                //Logger.getGlobal().info("JwtAuthFilter: No Authorization Bearer");
                filterChain.doFilter(request, response);
                return;
            }

            String token = userAuthProvider.getAuthorizationBearer(header);
            if (token == null) {
                Logger.getGlobal().info("JwtAuthFilter: No Token");
                filterChain.doFilter(request, response);
                return;
            }

            DecodedJWT decodedJWT = userAuthProvider.decodeToken(token);
            if (decodedJWT == null) {
                Logger.getGlobal().info("JwtAuthFilter: Invalid Token");
                filterChain.doFilter(request, response);
                return;
            }

            String username = userAuthProvider.getUsername(decodedJWT);

            User user = userServiceImp.loadUserByUsername(username);
            user.setLastActivity(LocalDateTime.now());
            userServiceImp.save(user);

            userAuthProvider.setAuthentication(user, request);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            SecurityContextHolder.clearContext();
        }
    }


}