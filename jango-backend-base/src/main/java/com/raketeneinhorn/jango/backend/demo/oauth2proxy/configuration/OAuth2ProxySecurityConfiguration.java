package com.raketeneinhorn.jango.backend.demo.oauth2proxy.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class OAuth2ProxySecurityConfiguration {

    public static final String AUTH_PATH_PREFIX = "/demo/oauth2-proxy/auth";
    public static final String NOAUTH_PATH_PREFIX = "/demo/oauth2-proxy/noauth";

    private final RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter;

    @Order(1)
    @Bean
    public SecurityFilterChain oAuth2ProxyAuthSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(AUTH_PATH_PREFIX + "/**")
                .addFilterBefore(requestHeaderAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().authenticated();
                })
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Order(1)
    @Bean
    public SecurityFilterChain oAuth2ProxyNoAuthSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(NOAUTH_PATH_PREFIX + "/**")
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
