package com.raketeneinhorn.jango.backend.security.preauth.header;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
public class PreauthHeaderAuthenticationDetails implements GrantedAuthoritiesContainer {

    private final List<SimpleGrantedAuthority> grantedAuthorities;
    private final String user;
    private final String email;
    private final String preferredUsername;
    private final String forwardedFor;

    public PreauthHeaderAuthenticationDetails(final HttpServletRequest httpServletRequest) {
        grantedAuthorities = Optional.ofNullable(httpServletRequest.getHeader("x-forwarded-groups"))
            .map(s -> s.split(","))
            .stream()
            .flatMap(Stream::of)
            .map(SimpleGrantedAuthority::new)
            .toList();

        this.user = httpServletRequest.getHeader("x-forwarded-user");
        this.email = httpServletRequest.getHeader("x-forwarded-email");
        this.preferredUsername = httpServletRequest.getHeader("x-forwarded-preferred-username");
        this.forwardedFor = httpServletRequest.getHeader("x-forwarded-for");
    }

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

}
