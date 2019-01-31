# Exercise 1

In this exercise, you will practice writing a Dockerfile and building a Docker image.

## Writing a Dockerfile

1. Create a new Dockerfile in the root directory of the project.
2. Define the instructions of the Dockerfile
    - The image should be based on the image `openjdk:jre-alpine`.
    - Add a label that represents your name as maintainer.
    - Copy the application's archive into image.
    - Define the `java` command as entry point.
    - Provide the main class file as command.
3. (Optional) Use [Dockerfile Linter](http://hadolint.lukasmartinelli.ch/) to verfying the syntax of the file.

## Building a Docker image

1. Create an image from the `Dockerfile` by running the appropriate Docker command.
    - Use the image name `todo-web-service`
    - Use the tag `1.0`.
2. List images on your machine and indentify the image you should built.