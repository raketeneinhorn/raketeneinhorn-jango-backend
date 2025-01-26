package com.raketeneinhorn.jango.backend.security.preauth.header.configuration;

import com.raketeneinhorn.jango.backend.security.preauth.header.PreauthHeaderAuthenticationDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
public class PreauthHeaderConfiguration {

    @Bean
    public AuthenticationManager authenticationManager() {
        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(new PreAuthenticatedGrantedAuthoritiesUserDetailsService());

        return new ProviderManager(preAuthenticatedAuthenticationProvider);
    }

    @Bean
    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter(AuthenticationManager authenticationManager) {
        RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("x-forwarded-preferred-username");
        requestHeaderAuthenticationFilter.setAuthenticationDetailsSource(PreauthHeaderAuthenticationDetails::new);
        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return requestHeaderAuthenticationFilter;
    }

}
