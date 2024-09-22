package com.raketeneinhorn.jango.backend.test.run;

import com.raketeneinhorn.jango.backend.JangoBackendApplication;
import com.raketeneinhorn.testcontainers.actuate.configuration.TestcontainersEndpointConfiguration;
import com.raketeneinhorn.testcontainers.container.postgresql.configuration.BitnamiPostgreSQLContainerConfiguration;
import org.springframework.boot.SpringApplication;

public class TestJangoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(JangoBackendApplication::main)
            .with(BitnamiPostgreSQLContainerConfiguration.class)
            .with(TestcontainersEndpointConfiguration.class)
            .run(args);
    }

}
