package com.raketeneinhorn.jango.backend.demo.oauth2proxy;

import com.raketeneinhorn.jango.backend.demo.oauth2proxy.configuration.OAuth2ProxySecurityConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping({
    OAuth2ProxySecurityConfigurationProperties.NOAUTH_PATH_PREFIX_DEFAULT,
    OAuth2ProxySecurityConfigurationProperties.NOAUTH_PATH_PREFIX_DEFAULT + "/api",
    OAuth2ProxySecurityConfigurationProperties.AUTH_PATH_PREFIX_DEFAULT,
    OAuth2ProxySecurityConfigurationProperties.AUTH_PATH_PREFIX_DEFAULT + "/api"
})
public class OAuth2ProxyAuthInfoController  {

    @GetMapping("/info")
    public Authentication info() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
