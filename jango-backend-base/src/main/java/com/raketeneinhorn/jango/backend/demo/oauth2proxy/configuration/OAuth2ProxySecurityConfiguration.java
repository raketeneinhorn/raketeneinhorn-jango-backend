package com.raketeneinhorn.jango.backend.demo.oauth2proxy.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(OAuth2ProxySecurityConfigurationProperties.class)
public class OAuth2ProxySecurityConfiguration {

    private final RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter;

    private final OAuth2ProxySecurityConfigurationProperties properties;

    @Order(1)
    @Bean
    public SecurityFilterChain oAuth2ProxyAuthSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(properties.getAuthPathPrefix() + "/**")
                .addFilterBefore(requestHeaderAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .authorizeHttpRequests(auth ->
                    auth.anyRequest().authenticated()
                )
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Order(1)
    @Bean
    public SecurityFilterChain oAuth2ProxyNoAuthSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(properties.getNoauthPathPrefix() + "/**")
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
