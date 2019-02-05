# Solution

## Test setup and declaring dependencies

### Maven

```
<project>
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.3.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.3.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>1.10.6</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.10.6</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

### Gradle

```
dependencies {
    def junitJupiterVersion = '5.3.2'
    testImplementation "org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion"
    testRuntimeOnly "org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion"
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    def testcontainersVersion = '1.10.6'
    testImplementation "org.testcontainers:junit-jupiter:$testcontainersVersion"
    testImplementation "org.testcontainers:postgresql:$testcontainersVersion"
}
```

## Implementing and executing the test

It's very easy to create a new instance of `PostgreSQLContainer`. Simply instantiate the class and optionally provide a specific image. The class implements the builder pattern to provide username, password and database name.

```
private static PostgreSQLContainer createDbContainer() {
    return new PostgreSQLContainer("postgres:9.6.10-alpine")
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("todo");
}
```

The container instance exposes methods to access runtime information like JDBC URL, username and password.

```
static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                "spring.datasource.driver-class-name=org.postgresql.Driver",
                "spring.jpa.generate-ddl=true"
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
```