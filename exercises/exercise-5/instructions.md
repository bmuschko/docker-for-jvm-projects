# Exercise 5

In this exercise, you will learn how to use [TestContainers](https://www.testcontainers.org/) to running a Postgres database as test fixture.

## Using TestContainers to run a database in a container

You can decide to use Maven or Gradle for this exercise. Pick the tool you are most comfortable with.

1. Configure the build script to add the relevant JUnit 5 and TestContainer dependencies.
2. Create a new Java test class named `ToDoRepositoryIntegrationTest.java` in the directory `src/test/java` with the package `org.oreilly.training`.
3. In the test class, create a new container instance of type `org.testcontainers.containers.PostgreSQLContainer`. Provide username/password and database name. Annotate the class so that TestContainer recognizes it as fixture.
4. Determine the JDBC URL, username and password by calling the appropriate methods of the container instance. Inject them into the database setup so they can be used when starting the database.
5. Write a test case that creates a new item and saves it to the database by calling the method of the class under test.
6. Run the test and verify its correct behavior. The console output should indicate that the container has been created and destroyed after the test finished.