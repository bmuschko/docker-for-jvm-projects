# Exercise 5

In this exercise, you will learn how to use [TestContainers](https://www.testcontainers.org/) to run a Postgres database as test fixture. The setup can be helpful to emulate a real-world environment where code under test connects to an external system. There's no need to stand up an actual instance of the database including seed data just for the purpose of integration testing.

## Test setup and declaring dependencies

You can decide to use Maven or Gradle for this exercise. Pick the tool you are most comfortable with.

1. Copy the Java file `ToDoRepositoryIntegrationTest.java` from the `start` directory into the project under the directory `src/test/java/com/bmuschko/todo/webservice/repository`. The file contains `TODO` comments, you'll fill in with actual code as part of this exercise.
2. Configure the build script to add the relevant JUnit 5 and TestContainer dependencies. Use the version 5.3.2 for JUnit, use version 1.12.0 for TestContainers.
3. Execute the compilation goal/task for the test sources. For Maven the command is `./mvnw test-compile`, for Gradle the command is `./gradlew testClasses`. Try to identify the missing or incorrect if you are experiencing a compilation error.

## Implementing and executing the test

1. In the test class, create a new container instance of type `org.testcontainers.containers.PostgreSQLContainer`. Provide username/password and database name `todo`.
2. Determine the JDBC URL, username and password by calling the appropriate methods of the container instance. Inject them into the database setup so they can be used when starting the database.
3. Run the test and verify its correct behavior. For Maven the command is `./mvnw test`, for Gradle the command is `./gradlew test`. The console output should indicate that the container has been created and destroyed after the test finished.