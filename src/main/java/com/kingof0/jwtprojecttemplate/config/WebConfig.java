package com.kingof0.jwtprojecttemplate.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig {

    private final List<String> allowedOrigins = Arrays.asList(
            "http://92.44.163.3:3000",
            "https://92.44.163.3:3000",
            "http://92.44.163.3:5173",
            "https://92.44.163.3:5173",
            "http://localhost:3000",
            "https://localhost:3000",
            "http://localhost:5173",
            "https://localhost:5173",
            "http://192.168.1.120:3000",
            "https://192.168.1.120:3000",
            "https://anket.citymallavm.com/");

    private final List<String> allowedHeaders = Arrays.asList(
            HttpHeaders.AUTHORIZATION,
            HttpHeaders.CONTENT_TYPE,
            HttpHeaders.ACCEPT
    );

    private final List<String> allowedMethods = Arrays.asList(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name()
    );


    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration config = getCorsConfiguration();
        config.setAllowedHeaders(allowedHeaders);
        config.setAllowedMethods(allowedMethods);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(-102);
        return bean;

    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();

        allowedOrigins.forEach(config::addAllowedOrigin);

        config.addAllowedOriginPattern("*");
        config.addAllowedOriginPattern("/**");
        config.addAllowedOriginPattern("/*");

        return config;
    }


}