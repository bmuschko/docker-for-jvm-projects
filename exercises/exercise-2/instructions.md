# Exercise 2

In this exercise, you will practice running a Docker image.

## Running a Docker image

1. Identify the image ID for the image `todo-web-service:1.0`.
2. Start a Docker container for this image. Keep the container running the background and expose the port 8080.
3. Bring up a browser and open the URL `http://localhost:8080/todos`. The browser should render an empty JSON array "[]".
4. Add to new ToDo items by running a `curl` command. See the [API usage examples](https://github.com/bmuschko/todo-web-service-exercise#using-the-api) for more information. Refresh the browser URL. The new ToDo items should be reflected in the rendered JSON.
5. Log into the container and locate the process running the Java application. Exit the container with the `exit` command.
6. Stop and remove the container.