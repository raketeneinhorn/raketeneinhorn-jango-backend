package com.raketeneinhorn.jango.backend.demo.oauth2proxy.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("jango.backend.demo.oauth2proxy")
public class OAuth2ProxySecurityConfigurationProperties {

    public static final String PATH_PREFIX = "/demo/oauth2-proxy";
    public static final String AUTH_PATH_PREFIX_DEFAULT = PATH_PREFIX + "/auth";
    public static final String NOAUTH_PATH_PREFIX_DEFAULT = PATH_PREFIX + "/noauth";

    private String authPathPrefix = AUTH_PATH_PREFIX_DEFAULT;
    private String noauthPathPrefix = NOAUTH_PATH_PREFIX_DEFAULT;

}
