package com.example.apigetaway;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Testcontainers
public class TestContainersTest {

    private final static String POSTGRES_VERSION = "postgres:13.10";

    @Container
    private final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(POSTGRES_VERSION);

    @Test
    public void dockerTestContainers_Demo(){
        var testResult = postgreSQLContainer.isRunning();
        Assertions.assertThat(testResult).isTrue();
    }
}
