package com.raketeneinhorn.jango.backend.security.preauth.header;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PreauthHeaderAuthenticationDetailsTest {

    @Nested
    class PreauthHeaderAuthenticationDetails_Constructor {

        @Test
        void extractsAuthoritiesFromGroupHeaderWhenSet() {
            HttpServletRequest httpServletRequestMock = mock();
            when(httpServletRequestMock.getHeader("x-forwarded-groups")).thenReturn("a,b");

            PreauthHeaderAuthenticationDetails preauthHeaderAuthenticationDetails = new PreauthHeaderAuthenticationDetails(httpServletRequestMock);

            assertThat(preauthHeaderAuthenticationDetails.getGrantedAuthorities())
                .map(GrantedAuthority::getAuthority)
                    .hasSize(2)
                    .contains("a", "b");
        }

        @Test
        void extractsHeaderInformationWhenSet() {
            String randomUser = UUID.randomUUID().toString();
            String randomEmail = UUID.randomUUID().toString();
            String randomPreferredUsername = UUID.randomUUID().toString();
            String randomForwardedFor = UUID.randomUUID().toString();

            HttpServletRequest httpServletRequestMock = mock();
            when(httpServletRequestMock.getHeader("x-forwarded-user")).thenReturn(randomUser);
            when(httpServletRequestMock.getHeader("x-forwarded-email")).thenReturn(randomEmail);
            when(httpServletRequestMock.getHeader("x-forwarded-preferred-username")).thenReturn(randomPreferredUsername);
            when(httpServletRequestMock.getHeader("x-forwarded-for")).thenReturn(randomForwardedFor);

            PreauthHeaderAuthenticationDetails preauthHeaderAuthenticationDetails = new PreauthHeaderAuthenticationDetails(httpServletRequestMock);

            assertThat(preauthHeaderAuthenticationDetails.getUser()).isEqualTo(randomUser);
            assertThat(preauthHeaderAuthenticationDetails.getEmail()).isEqualTo(randomEmail);
            assertThat(preauthHeaderAuthenticationDetails.getPreferredUsername()).isEqualTo(randomPreferredUsername);
            assertThat(preauthHeaderAuthenticationDetails.getForwardedFor()).isEqualTo(randomForwardedFor);
        }

    }

}