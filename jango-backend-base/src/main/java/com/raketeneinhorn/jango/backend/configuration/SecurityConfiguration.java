package com.raketeneinhorn.jango.backend.configuration;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, WebEndpointProperties webEndpointProperties) throws Exception {
        String actuatorBasePath = webEndpointProperties.getBasePath();

        return http
            .authorizeHttpRequests(auth -> {
                auth
                    .requestMatchers(HttpMethod.GET, actuatorBasePath + "/**").permitAll()
                    .anyRequest().denyAll();
            })
            .build();
    }

}
