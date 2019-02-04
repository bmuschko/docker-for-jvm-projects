# Solution

## Writing a Dockerfile

* [Dockerfile](./maven/Dockerfile) for an archive built with Maven
* [Dockerfile](./gradle/Dockerfile) for an archive built with Gradle

## Building a Docker image

The paths in the console output might be different depending on whether you built the archive with Maven or Gradle.

```
$ docker build -t todo-web-service:1.0 .
Sending build context to Docker daemon  60.68MB
Step 1/5 : FROM openjdk:jre-alpine
jre-alpine: Pulling from library/openjdk
8e3ba11ec2a2: Pull complete
311ad0da4533: Pull complete
391a6a6b3651: Pull complete
Digest: sha256:1bed44170948277881d88481ecbd07317eb7bae385560a9dd597bbfe02782766
Status: Downloaded newer image for openjdk:jre-alpine
 ---> ccfb0c83b2fe
Step 2/5 : LABEL maintainer="Benjamin Muschko"
 ---> Running in 7579a4849baa
Removing intermediate container 7579a4849baa
 ---> a29d15d26048
Step 3/5 : COPY /build/libs/todo-web-service-exercise-1.0.0.jar /app/todo-web-service.jar
 ---> f332b70749db
Step 4/5 : ENTRYPOINT ["java", "-jar", "/app/todo-web-service.jar"]
 ---> Running in 7543c5889304
Removing intermediate container 7543c5889304
 ---> 13b4a86d98da
Step 5/5 : EXPOSE 8080
 ---> Running in 40729a15c995
Removing intermediate container 40729a15c995
 ---> 111bcbcbccce
Successfully built 111bcbcbccce
Successfully tagged todo-web-service:1.0
```

You should see the built image in the list of available images.

```
$ docker images
REPOSITORY                                 TAG                 IMAGE ID            CREATED             SIZE
todo-web-service                           1.0                 4c6607bbed18        4 seconds ago       121MB
openjdk                                    jre-alpine          ccfb0c83b2fe        6 months ago        83MB
```