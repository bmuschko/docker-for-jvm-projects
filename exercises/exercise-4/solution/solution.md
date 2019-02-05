# Solution

## Maven

Add the Jib plugin with the version 1.0.0 to the relevant section of the `pom.xml` file.

```xml
<project>
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>com.google.cloud.tools</groupId>
                    <artifactId>jib-maven-plugin</artifactId>
                    <version>1.0.0</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```

Now that the Jib plugin has been applied, you can build and push an image without the use of the Docker daemon. The goal will not create a `Dockerfile` nor does it build a Docker image.

```
$ ./mvnw compile com.google.cloud.tools:jib-maven-plugin:1.0.0:build -Dimage=bmuschko/todo-web-service:2.0.0
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.bmuschko:todo-web-service-exercise >---------------
[INFO] Building todo-web-service-exercise 1.0.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ todo-web-service-exercise ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ todo-web-service-exercise ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- jib-maven-plugin:1.0.0:build (default-cli) @ todo-web-service-exercise ---
[INFO]
[INFO] Containerizing application to bmuschko/todo-web-service:2.0.0...
[WARNING] Base image 'gcr.io/distroless/java' does not use a specific image digest - build may not be reproducible
[INFO]
[INFO] Container entrypoint set to [java, -cp, /app/resources:/app/classes:/app/libs/*, com.bmuschko.todo.webservice.Application]
[INFO]
[INFO] Built and pushed image as bmuschko/todo-web-service:2.0.0
[INFO] Executing tasks:
[INFO] [==============================] 100.0% complete
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.755 s
[INFO] Finished at: 2019-02-04T15:19:55-07:00
[INFO] ------------------------------------------------------------------------
```

The image name can be hard-coded as part of the plugin configuration.

```xml
<project>
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>com.google.cloud.tools</groupId>
                    <artifactId>jib-maven-plugin</artifactId>
                    <version>1.0.0</version>
                    <configuration>
                        <to>
                            <image>bmuschko/todo-web-service:${project.version}</image>
                        </to>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```

You can build and push the image with the Docker daemon by using the goal `com.google.cloud.tools:jib-maven-plugin:1.0.0:dockerBuild`.

```
$ ./mvnw compile com.google.cloud.tools:jib-maven-plugin:1.0.0:dockerBuild
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.bmuschko:todo-web-service-exercise >---------------
[INFO] Building todo-web-service-exercise 2.0.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ todo-web-service-exercise ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ todo-web-service-exercise ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- jib-maven-plugin:1.0.0:dockerBuild (default-cli) @ todo-web-service-exercise ---
[INFO]
[INFO] Containerizing application to Docker daemon as bmuschko/todo-web-service:2.0.0...
[WARNING] Base image 'gcr.io/distroless/java' does not use a specific image digest - build may not be reproducible
[INFO]
[INFO] Container entrypoint set to [java, -cp, /app/resources:/app/classes:/app/libs/*, com.bmuschko.todo.webservice.Application]
[INFO]
[INFO] Built image to Docker daemon as bmuschko/todo-web-service:2.0.0
[INFO] Executing tasks:
[INFO] [==============================] 100.0% complete
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.789 s
[INFO] Finished at: 2019-02-04T15:30:07-07:00
[INFO] ------------------------------------------------------------------------
```

You should be able to find the image by running `docker images`:

```
$ docker images
REPOSITORY                                 TAG                 IMAGE ID            CREATED             SIZE
bmuschko/todo-web-service                  2.0.0               5289ed839f93        49 years ago        157MB
```

## Gradle

Add the Jib plugin with the version 1.0.0 to the `build.gradle` file.

```groovy
plugins {
    id 'com.google.cloud.tools.jib' version '1.0.0'
}
```

Now that the Jib plugin has been applied, you can build and push an image without the use of the Docker daemon. The task will not create a `Dockerfile` nor does it build a Docker image.

```
$ ./gradlew jib --image=bmuschko/todo-web-service:2.0.0

Containerizing application to bmuschko/todo-web-service:2.0.0...
Base image 'gcr.io/distroless/java' does not use a specific image digest - build may not be reproducible

Container entrypoint set to [java, -cp, /app/resources:/app/classes:/app/libs/*, com.bmuschko.todo.webservice.Application]

Built and pushed image as bmuschko/todo-web-service:2.0.0
Executing tasks:
[==============================] 100.0% complete

BUILD SUCCESSFUL in 2s
3 actionable tasks: 1 executed, 2 up-to-date
```

The image name can be hard-coded as part of the plugin configuration.

```groovy
jib.to.image = "bmuschko/todo-web-service:${project.version}"
```

You can build and push the image with the Docker daemon by using the task `jibDockerBuild`.

```
./gradlew jibDockerBuild

Containerizing application to Docker daemon as bmuschko/todo-web-service:2.0.0...
Base image 'gcr.io/distroless/java' does not use a specific image digest - build may not be reproducible

Container entrypoint set to [java, -cp, /app/resources:/app/classes:/app/libs/*, com.bmuschko.todo.webservice.Application]

Built image to Docker daemon as bmuschko/todo-web-service:2.0.0
Executing tasks:
[==============================] 100.0% complete

BUILD SUCCESSFUL in 4s
3 actionable tasks: 1 executed, 2 up-to-date
```

You should be able to find the image by running `docker images`:

```
$ docker images
REPOSITORY                                 TAG                 IMAGE ID            CREATED             SIZE
bmuschko/todo-web-service                  2.0.0               5289ed839f93        49 years ago        157MB
```