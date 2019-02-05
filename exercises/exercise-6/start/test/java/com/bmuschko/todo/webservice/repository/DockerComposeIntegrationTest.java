package com.bmuschko.todo.webservice.repository;

import com.bmuschko.todo.webservice.model.ToDoItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(initializers = { DockerComposeIntegrationTest.Initializer.class })
public class DockerComposeIntegrationTest {

    private final static String POSTGRES_SERVICE_NAME = "database_1";
    private final static int POSTGRES_SERVICE_PORT = 5432;

    @Autowired
    private ToDoRepository repository;

    @Container
    public static DockerComposeContainer environment = createComposeContainer();

    private static DockerComposeContainer createComposeContainer() {
        // TODO: Create an instance of `DockerComposeContainer` and configure it
        return null;
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            // TODO: Access the JDBC URL from the database service
            TestPropertyValues.of(
                    "spring.datasource.url=" + null,
                    "spring.datasource.username=postgres",
                    "spring.datasource.password=postgres",
                    "spring.datasource.driver-class-name=org.postgresql.Driver",
                    "spring.jpa.generate-ddl=true"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    private static String getPostgresServiceUrl() {
        // TODO: Access the database host and port from the `DockerComposeContainer` instance
        String postgresHost = null;
        Integer postgresPort = null;
        StringBuilder postgresServiceUrl = new StringBuilder();
        postgresServiceUrl.append("jdbc:postgresql://");
        postgresServiceUrl.append(postgresHost);
        postgresServiceUrl.append(":");
        postgresServiceUrl.append(postgresPort);
        postgresServiceUrl.append("/todo");
        return postgresServiceUrl.toString();
    }

    @Test
    public void testCanSaveNewToDoItem() {
        ToDoItem toDoItem = createToDoItem("Buy milk");
        assertNull(toDoItem.getId());
        repository.save(toDoItem);
        assertNotNull(toDoItem.getId());
    }

    private ToDoItem createToDoItem(String name) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(name);
        return toDoItem;
    }
}
