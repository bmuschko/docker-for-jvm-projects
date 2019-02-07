# Exercise 8

In this exercise, you will learn how to use a third-party build tool integration. You can decide to use Maven or Gradle for this exercise. Pick the tool you are most comfortable with.

## Using a build tool plugin for Docker

1. Modify the existing build script by adding one of the discussed plugins with its latest version. Refer to the documentation of the [bmuschko/gradle-docker-plugin](https://bmuschko.github.io/gradle-docker-plugin/) or the [fabric8io/docker-maven-plugin](http://dmp.fabric8.io/) for more information.
2. Configure the plugin to build an image for the current project. The image should use the base image `openjdk:jre-alpine`, copy the application's JAR file, use the `java` command to start the application and expose the application on port 8080. Execute the corresponding goal/task, locate the generated Dockerfile and image in the registry.
3. Configure the plugin to run the image in a container. Execute the corresponding goal/task, and bring up the URL `http://localhost:8080/todos` in the browser. You should see the application.