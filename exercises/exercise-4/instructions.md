# Exercise 4

In this exercise, you will learn how to use [Google Jib](https://github.com/GoogleContainerTools/jib) to implement a typical developer workflow.

## Building and pushing an image with Jib

You can decide to use Maven or Gradle for this exercise. Pick the tool you are most comfortable with.

1. Add the Jib plugin to the build script. See the [quick start guide](https://github.com/GoogleContainerTools/jib#quickstart) for more information.
2. Build and push the image to Docker Hub by invoking the respective goal/task provided by the plugin. Declare the image name as command line parameter. Use the version 2.0.0.
3. Configure the build script to hard-code the image name so that you don't have to provide the name as command line parameter anymore. Build the image again.
4. Build and push the image with the goal/task that uses the Docker daemon.
5. Verify that the image has been created by the `docker container ls` command.
6. (Optional) Install [dive](https://github.com/wagoodman/dive), a tool for exploring a docker image and layer contents. Inspect the layers of the built image.