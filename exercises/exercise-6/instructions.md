# Exercise 6

In this exercise, you will learn how to use [TestContainers](https://www.testcontainers.org/) to stand up a multi-container environment. The setup is particular useful if you are working with microservices where each of the services can be run in an invidual container. The integration test consumes a Docker Compose file as a fixture for bringing up a suite of containers.

## Defining a Docker Compose YAML file

1. Create a new Docker Compose file with the name `compose-test.yml` in the directory `src/test/resources`.
2. In the Docker Compose file define a new service named `database`. For the service, define a PostgreSQL database with the image `postgres:9.6.10-alpine`. Provide the username, password and database with the help of environment variables. Use the environment variables `POSTGRES_USER`, `POSTGRES_PASSWORD` and `POSTGRES_DB`.

## Test setup

1. Copy the Java file `DockerComposeIntegrationTest.java` from the `start` directory into the project under the directory `src/test/java/com/bmuschko/todo/webservice/repository`. The file contains `TODO` comments, you'll fill in with actual code as part of this exercise.
2. Execute the compilation goal/task for the test sources. For Maven the command is `./mvnw test-compile`, for Gradle the command is `./gradlew testClasses`. Try to identify the missing or incorrect if you are experiencing a compilation error.

## Implementing and executing the test

1. In the test class, create a new container instance of type `org.testcontainers.containers.DockerComposeContainer`. In the constructor provide the path to the Docker Compose YAML file. Expose the service by name and port.
2. Determine the JDBC URL by calling the appropriate methods of the container instance. Inject the URL into the database setup so it can be used when starting the database.
3. Run the test and verify its correct behavior. For Maven the command is `./mvnw test`, for Gradle the command is `./gradlew test`. The console output should indicate that the container has been created and destroyed after the test finished.
4. (Optional) Create another service of your choice e.g. ElasticSearch as part of the Docker Compose file. Check the TestContainers documentation for more information.
