package com.raketeneinhorn.jango.backend.demo.oauth2proxy.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Getter
@Service
@ConfigurationProperties("jango.backend.demo.oauth2proxy")
public class OAuth2ProxySecurityConfigurationProperties {

    public static final String AUTH_PATH_PREFIX_DEFAULT = "/demo/oauth2-proxy/auth";
    public static final String NOAUTH_PATH_PREFIX_DEFAULT = "/demo/oauth2-proxy/noauth";

    private String authPathPrefix = AUTH_PATH_PREFIX_DEFAULT;
    private String noauthPathPrefix = NOAUTH_PATH_PREFIX_DEFAULT;

}
