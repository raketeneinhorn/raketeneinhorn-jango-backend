package com.raketeneinhorn.jango.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JangoBackendApplicationTest {

    @Test
    void contextLoads() {
    }

    // Test added ONLY to cover main() invocation not covered by application tests.
    @Test
    void coverMain() {
        JangoBackendApplication.main(new String[] {});
    }

}
