package com.kingof0.jwtprojecttemplate.config;

import com.kingof0.jwtprojecttemplate.security.AccessDeniedEntryPoint;
import com.kingof0.jwtprojecttemplate.security.JwtAuthFilter;
import com.kingof0.jwtprojecttemplate.security.UserAuthProvider;
import com.kingof0.jwtprojecttemplate.security.UserAuthenticationEntryPoint;
import com.kingof0.jwtprojecttemplate.security.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final AccessDeniedEntryPoint accessDeniedEntryPoint;


    private final UserAuthProvider userAuthProvider;
    private final UserServiceImp userServiceImp;

    @Bean
    SecurityFilterChain securityWebFilterChain(HttpSecurity http) {
        try {
            return http
                    .addFilterBefore(new JwtAuthFilter(userAuthProvider, userServiceImp), UsernamePasswordAuthenticationFilter.class)
                    .csrf(AbstractHttpConfigurer::disable)
                    .exceptionHandling(it -> it.authenticationEntryPoint(userAuthenticationEntryPoint).accessDeniedHandler(accessDeniedEntryPoint))
                    .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                    .authorizeHttpRequests(it -> it

                            .requestMatchers(antMatcher("/api/auth/login")).permitAll() //giriş yapabilir
                            .requestMatchers(antMatcher("/api/survey/submit")).permitAll() //anketi doldurup yanıtlarını gönderebilir
                            .requestMatchers(antMatcher("/api/survey/all")).permitAll() //tüm anketleri görebilir
                            .requestMatchers(antMatcher("/api/survey/get")).permitAll() //id ile anketi görebilir
                            .requestMatchers(antMatcher("/api/survey/get/**")).permitAll() //id ile anketi görebilir

                            .requestMatchers(antMatcher("/api/template/**")).permitAll() //şablonları indirebilir. (Ek yetki kontrolü var)

/*
                            .requestMatchers(antMatcher("/swagger-ui/**")).permitAll() //swagger-ui'ya erişebilir
                            .requestMatchers(antMatcher("/swagger-resources/*")).permitAll() //swagger-ui'ya erişebilir
                            .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll() //swagger-ui'ya erişebilir
 */
                            .anyRequest().permitAll()) //todo: Auth
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}