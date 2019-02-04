# Exercise 1

In this exercise, you will practice writing a Dockerfile and building a Docker image.

## Writing a Dockerfile

1. Build the application's archive with the relevant build tool command.
    - Use `./mvnw package spring-boot:repackage` in Maven
    - Use `./gradlew assemble` in Gradle
2. Create a new Dockerfile in the root directory of the project.
3. Define the instructions of the Dockerfile
    - The image should be based on the image `openjdk:jre-alpine`.
    - Add a label that represents your name as maintainer.
    - Copy the application's archive into the image.
    - Define the `java` command as entry point.
    - Expose port 8080.
3. (Optional) Use a Dockerfile Linter e.g. [FROM:latest](https://www.fromlatest.io) to verifying the syntax of the file.

## Building a Docker image

1. Create an image from the `Dockerfile` by running the appropriate Docker command. Use the tag `todo-web-service:1.0`.
2. List images on your machine and indentify the image you should built.