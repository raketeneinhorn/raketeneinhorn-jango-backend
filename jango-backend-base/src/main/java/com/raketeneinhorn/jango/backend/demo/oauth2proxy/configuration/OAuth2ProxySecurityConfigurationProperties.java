package com.raketeneinhorn.jango.backend.demo.oauth2proxy.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Getter
@Service
@ConfigurationProperties("jango.backend.demo.oauth2proxy")
public class OAuth2ProxySecurityConfigurationProperties {

    private String authPathPrefix = "/demo/oauth2-proxy/auth";
    private String noauthPathPrefix = "/demo/oauth2-proxy/noauth";

}
