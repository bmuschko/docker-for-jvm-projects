# Solution

## Maven

Apply the fabric8io/docker-maven-plugin in the plugins section of the POM.

```xml
<project>
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>io.fabric8</groupId>
                    <artifactId>docker-maven-plugin</artifactId>
                    <version>0.28.0</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```

Provide build and run configuration for the plugin. Use the built artifact of the project as input for building the image.

```xml
<configuration>
    <images>
        <image>
            <name>bmuschko/todo-web-service:${project.version}</name>
            <alias>todo-web-service</alias>
            <build>
                <from>openjdk:jre-alpine</from>
                <assembly>
                    <descriptorRef>artifact</descriptorRef>
                </assembly>
                <entryPoint>java -jar maven/${project.name}-${project.version}.jar</entryPoint>
            </build>
            <run>
                <ports>
                    <port>8080:8080</port>
                </ports>
            </run>
        </image>
    </images>
</configuration>
```

You can run the the container by executing the command `./mvnw package spring-boot:repackage docker:build docker:run`. Ensure to also include the goal `spring-boot:repackage` so that the Spring Boot plugin can add the main class name.

```
./mvnw package spring-boot:repackage docker:build docker:run
[INFO] Scanning for projects...
[WARNING] The POM for org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 is missing, no dependency information available
[WARNING] Failed to retrieve plugin descriptor for org.eclipse.m2e:lifecycle-mapping:1.0.0: Plugin org.eclipse.m2e:lifecycle-mapping:1.0.0 or one of its dependencies could not be resolved: Failure to find org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 in https://repo.maven.apache.org/maven2 was cached in the local repository, resolution will not be reattempted until the update interval of central has elapsed or updates are forced
[WARNING] The POM for org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 is missing, no dependency information available
[WARNING] Failed to retrieve plugin descriptor for org.eclipse.m2e:lifecycle-mapping:1.0.0: Plugin org.eclipse.m2e:lifecycle-mapping:1.0.0 or one of its dependencies could not be resolved: Failure to find org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 in https://repo.maven.apache.org/maven2 was cached in the local repository, resolution will not be reattempted until the update interval of central has elapsed or updates are forced
[INFO]
[INFO] ---------------< com.bmuschko:todo-web-service-exercise >---------------
[INFO] Building todo-web-service-exercise 1.0.0
[INFO] --------------------------------[ jar ]---------------------------------
[WARNING] The POM for org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 is missing, no dependency information available
[WARNING] Failed to retrieve plugin descriptor for org.eclipse.m2e:lifecycle-mapping:1.0.0: Plugin org.eclipse.m2e:lifecycle-mapping:1.0.0 or one of its dependencies could not be resolved: Failure to find org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 in https://repo.maven.apache.org/maven2 was cached in the local repository, resolution will not be reattempted until the update interval of central has elapsed or updates are forced
[WARNING] The POM for org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 is missing, no dependency information available
[WARNING] Failed to retrieve plugin descriptor for org.eclipse.m2e:lifecycle-mapping:1.0.0: Plugin org.eclipse.m2e:lifecycle-mapping:1.0.0 or one of its dependencies could not be resolved: Failure to find org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 in https://repo.maven.apache.org/maven2 was cached in the local repository, resolution will not be reattempted until the update interval of central has elapsed or updates are forced
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ todo-web-service-exercise ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ todo-web-service-exercise ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ todo-web-service-exercise ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/bmuschko/dev/projects/todo-web-service-exercise/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ todo-web-service-exercise ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ todo-web-service-exercise ---
[INFO] No tests to run.
[INFO]
[INFO] --- maven-jar-plugin:3.1.1:jar (default-jar) @ todo-web-service-exercise ---
[INFO] Building jar: /Users/bmuschko/dev/projects/todo-web-service-exercise/target/todo-web-service-exercise-1.0.0.jar
[INFO]
[INFO] --- spring-boot-maven-plugin:2.1.2.RELEASE:repackage (default-cli) @ todo-web-service-exercise ---
[INFO] Replacing main artifact with repackaged archive
[INFO]
[INFO] --- docker-maven-plugin:0.28.0:build (default-cli) @ todo-web-service-exercise ---
[INFO] Copying files to /Users/bmuschko/dev/projects/todo-web-service-exercise/target/docker/bmuschko/todo-web-service/1.0.0/build/maven
[INFO] Building tar: /Users/bmuschko/dev/projects/todo-web-service-exercise/target/docker/bmuschko/todo-web-service/1.0.0/tmp/docker-build.tar
[INFO] DOCKER> [bmuschko/todo-web-service:1.0.0] "todo-web-service": Created docker-build.tar in 1 second
[INFO] DOCKER> [bmuschko/todo-web-service:1.0.0] "todo-web-service": Built image sha256:fd453
[INFO] DOCKER> [bmuschko/todo-web-service:1.0.0] "todo-web-service": Removed old image sha256:f23ed
[INFO]
[INFO] --- docker-maven-plugin:0.28.0:run (default-cli) @ todo-web-service-exercise ---
[INFO] DOCKER> [bmuschko/todo-web-service:1.0.0] "todo-web-service": Start container 81ad22ae5f1e
...
```

## Gradle

First, apply the [Spring Boot application plugin](https://bmuschko.github.io/gradle-docker-plugin/#spring_boot_application_plugin). The plugin already provides basic conventions (e.g. the base image).

```groovy
plugins {
    id 'com.bmuschko.docker-spring-boot-application' version '4.4.1'
}
```

Next, add two tasks with [custom types provided by the plugin](https://bmuschko.github.io/gradle-docker-plugin/#custom_task_types): one for creating the container, one for running the container. The task `dockerBuildImage` already exists as it has been created by the Spring Boot application plugin.

```groovy
import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer

task createContainer(type: DockerCreateContainer) {
    dependsOn dockerBuildImage
    targetImageId dockerBuildImage.imageId
    portBindings = ['8080:8080']
}

task runContainer(type: DockerStartContainer) {
    dependsOn createContainer
    targetContainerId createContainer.containerId
}
```

You can run the the container by executing the task of type `DockerStartContainer`. You will not see the attached console output from the container.

```
./gradlew runContainer --console=verbose
> Task :compileJava
> Task :processResources UP-TO-DATE
> Task :classes
> Task :dockerSyncBuildContext
> Task :dockerCreateDockerfile

> Task :dockerBuildImage
Building image using context '/Users/bmuschko/dev/projects/todo-web-service-exercise/build/docker'.
Using tags 'com.bmuschko/todo-web-service-exercise:1.0.0' for image.

Step 1/7 : FROM openjdk:jre-alpine

---> ccfb0c83b2fe
Step 2/7 : WORKDIR /app

---> Running in 0a7663953f8a
Removing intermediate container 0a7663953f8a
---> 9b7619c48ec4
Step 3/7 : COPY libs libs/

---> 35730a177504
Step 4/7 : COPY resources resources/

---> d510e88eefd4
Step 5/7 : COPY classes classes/

---> 515092690c33
Step 6/7 : ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "com.bmuschko.todo.webservice.Application"]

---> Running in 7ae2f83bbb90
Removing intermediate container 7ae2f83bbb90
---> 75424e4c5283
Step 7/7 : EXPOSE 8080

---> Running in c7f3c278d39b
Removing intermediate container c7f3c278d39b
---> 771b55c0b275
Successfully built 771b55c0b275
Successfully tagged com.bmuschko/todo-web-service-exercise:1.0.0

> Task :dockerBuildImage
Created image with ID '771b55c0b275'.

> Task :createContainer
Created container with ID '88447c42ba7c033d2c114223479af8b29cdbee63d66eadc14c4c770ebe42d683'.

> Task :runContainer
Starting container with ID '88447c42ba7c033d2c114223479af8b29cdbee63d66eadc14c4c770ebe42d683'.

BUILD SUCCESSFUL in 10s
7 actionable tasks: 6 executed, 1 up-to-date
```

